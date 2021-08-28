package com.sdmctech.online.util

import java.security.MessageDigest

import com.sdmctech.online.config.CommonName
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{Put, Result, Scan}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.{TableInputFormat, TableMapReduceUtil, TableOutputFormat}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.mapreduce.Job
import org.apache.spark.ml.linalg
import org.apache.spark.ml.linalg.{SparseVector, Vectors}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

class HBaseUtil(spark:SparkSession) extends Serializable{


	@transient val hbaseConfig = HBaseConfiguration.create()
	//hbaseConfig.set("hbase.rootdir", "hdfs:\\\master:8020\\hbase")
	hbaseConfig.set("hbase.zookeeper.quorum", "worker1,worker2,master") // 设置zookeeper节点
	hbaseConfig.set("hbase.zookeeper.property.clientPort", "2181")
	@transient val sc = spark.sparkContext

	//读取数据
	def getData(tableName:String,
							cf:String,
							column:String):DataFrame={

		hbaseConfig.set(TableInputFormat.INPUT_TABLE,
			tableName)
		val hbaseRDD:RDD[(ImmutableBytesWritable,Result)]
		= sc.newAPIHadoopRDD(hbaseConfig,
			classOf[TableInputFormat],
			classOf[ImmutableBytesWritable],
			classOf[Result])

		import spark.implicits._
		val rs = hbaseRDD.map(_._2)
			.map(r=>{
				r.getValue(
					Bytes.toBytes(cf),
					Bytes.toBytes(column)
				)
			})
			.toDF("value")
		rs
	}
	def getSparseVectorDataOneHot(tableName:String,cf:String,columnName:String,
																pkName:String):DataFrame={
		val hbaseConfig =CommonName.hbaseConfig
		//val sc = spark.sparkContext
		hbaseConfig.set(TableInputFormat.INPUT_TABLE,
			tableName)
		val hbaseRDD:RDD[(ImmutableBytesWritable,Result)]
		= sc.newAPIHadoopRDD(hbaseConfig,
			classOf[TableInputFormat],
			classOf[ImmutableBytesWritable],
			classOf[Result])
		import spark.implicits._
		val rs = hbaseRDD.map(row =>{
			val rs1 = Bytes.toString(row._1.get())
			val rs2 = Bytes.toString(row._2.getValue(Bytes.toBytes(cf),Bytes.toBytes(columnName)))
			(rs1,getSparseVectorFromString(rs2))
		}).toDF(pkName,columnName)
		rs
	}



	def getSparseVectorFromString(input:String):linalg.Vector ={
		val tmp = input.substring(1,input.length-1).split("\\[")
		val tmp0 = tmp(0).substring(0,tmp(0).indexOf(",")).toInt
		var tmp1=Array(1)
		var tmp2=Array(1.0)
		if(tmp(1)!= null && !tmp(1).equals("],")){
			 tmp1 = tmp(1).substring(0,tmp(1).length-2).split(",").map(_.toInt)
		}
		if(tmp(2)!= null && !tmp(2).equals("]")){
			tmp2 = tmp(2).substring(0,tmp(2).length-1).split(",").map(_.toDouble)
		}

		val vs = Vectors.sparse(tmp0, tmp1,tmp2 )
		vs
	}



	//写入数据
	def putResultData(tableName:String,
										data:DataFrame,
										cf:String,
										column:String
									 ):Unit={
		val hbaseConfig = HBaseConfiguration.create()
		//hbaseConfig.set("hbase.rootdir", "hdfs:\\\master:8020\\hbase")
		hbaseConfig.set("hbase.zookeeper.quorum", "worker1,worker2,master") // 设置zookeeper节点
		hbaseConfig.set("hbase.zookeeper.property.clientPort", "2181")
		val jobConf = new JobConf(hbaseConfig, this.getClass)
		jobConf.set(TableOutputFormat.OUTPUT_TABLE,tableName)
		val job =Job.getInstance(jobConf)
		job.setOutputKeyClass(classOf[ImmutableBytesWritable])
		job.setOutputValueClass(classOf[Result])
		job.setOutputFormatClass(classOf[TableOutputFormat[ImmutableBytesWritable]])
		//job.setOutputFormatClass(classOf[TableOutputFormat[ImmutableBytesWritable]])
		val _data = data.rdd.map(x=> {
			val uid = x.get(0)
			val itemList = x.get(1)
			//在视频里没讲到，应该将rowKey散列
			val rowKey = uid.toString
			val put = new Put(Bytes.toBytes(rowKey))
			put.addColumn(Bytes.toBytes(cf),
				Bytes.toBytes(column),
				Bytes.toBytes(itemList.toString))
			(new ImmutableBytesWritable, put)
		})
		_data.saveAsNewAPIHadoopDataset(job.getConfiguration)
	}


	//写入数据(主键按string类型)
	def putData(tableName:String,
							data:DataFrame,
							cf:String,
							column:String,
							inputColumn:String
						 ):Unit={

		val hbaseConfig = HBaseConfiguration.create()
		//hbaseConfig.set("hbase.rootdir", "hdfs:\\\master:8020\\hbase")
		hbaseConfig.set("hbase.zookeeper.quorum", "master,worker1,worker2") // 设置zookeeper节点
		hbaseConfig.set("hbase.zookeeper.property.clientPort", "2181")

		// 使用新API
		val jobConf = new JobConf(hbaseConfig, this.getClass)
		jobConf.set(TableOutputFormat.OUTPUT_TABLE,tableName)
		val job =Job.getInstance(jobConf)
		job.setOutputKeyClass(classOf[ImmutableBytesWritable])
		job.setOutputValueClass(classOf[Result])
		job.setOutputFormatClass(classOf[TableOutputFormat[ImmutableBytesWritable]])

		val _data = data.rdd.map(x=> {
			val uid = x.getAs[String](0)
			val itemList = x.getAs[SparseVector](inputColumn)
			//在视频里没讲到，应该将rowKey散列
			//val rowKey = rowKeyHash(uid.toString)
			val rowKey = uid
			val put = new Put(Bytes.toBytes(rowKey))
			put.addColumn(Bytes.toBytes(cf),
				Bytes.toBytes(column),
				Bytes.toBytes(itemList.toString))
			(new ImmutableBytesWritable, put)
		})
		_data.saveAsNewAPIHadoopDataset(job.getConfiguration)
	}


	//读取特定数据
	def scanData(tableName:String,
							 cf:String,
							 column:String,
							 start:String,
							 end:String):Unit={
		val hbaseConfig = HBaseConfiguration.create()
		//hbaseConfig.set("hbase.rootdir", "hdfs:\\\master:8020\\hbase")
		hbaseConfig.set("hbase.zookeeper.quorum", "worker1,worker2,worker3") // 设置zookeeper节点
		hbaseConfig.set("hbase.zookeeper.property.clientPort", "2181")
		//val sc = spark.sparkContext
		hbaseConfig.set(TableInputFormat.INPUT_TABLE,
			tableName)
		val scan = new Scan(Bytes.toBytes(start),
			Bytes.toBytes(end))
		scan.addFamily(Bytes.toBytes(cf))
		scan.addColumn(Bytes.toBytes(cf),Bytes.toBytes(column))
		val scanStr = TableMapReduceUtil.convertScanToString(scan)
		hbaseConfig.set(TableInputFormat.SCAN,scanStr)
		val hbaseRDD:RDD[(ImmutableBytesWritable,Result)]
		= sc.newAPIHadoopRDD(hbaseConfig,
			classOf[TableInputFormat],
			classOf[ImmutableBytesWritable],
			classOf[Result])

		val rs = hbaseRDD.map(_._2)
			.map(r=>{
				r.getValue(
					Bytes.toBytes(cf),
					Bytes.toBytes(column)
				)
			})
			.collect()
	}

	//读取特定数据(有返回值的)
	def scanData2(tableName:String,
								cf:String,
								column:String,
								start:String,
								end:String):Array[Array[Byte]]={
		val hbaseConfig = HBaseConfiguration.create()
		//hbaseConfig.set("hbase.rootdir", "hdfs:\\\master:8020\\hbase")
		hbaseConfig.set("hbase.zookeeper.quorum", "worker1,worker2,worker3") // 设置zookeeper节点
		hbaseConfig.set("hbase.zookeeper.property.clientPort", "2181")
		// val sc = spark.sparkContext
		hbaseConfig.set(TableInputFormat.INPUT_TABLE,
			tableName)
		val scan = new Scan(Bytes.toBytes(start),
			Bytes.toBytes(end))
		scan.addFamily(Bytes.toBytes(cf))
		scan.addColumn(Bytes.toBytes(cf),Bytes.toBytes(column))
		val scanStr = TableMapReduceUtil.convertScanToString(scan)
		hbaseConfig.set(TableInputFormat.SCAN,scanStr)
		val hbaseRDD:RDD[(ImmutableBytesWritable,Result)]
		= sc.newAPIHadoopRDD(hbaseConfig,
			classOf[TableInputFormat],
			classOf[ImmutableBytesWritable],
			classOf[Result])

		val rs = hbaseRDD.map(_._2)
			.map(r=>{
				r.getValue(
					Bytes.toBytes(cf),
					Bytes.toBytes(column)
				)
			})
			.collect()
		rs
	}



	//rowKey散列
	def rowKeyHash(key:String):String={
		var md5:MessageDigest = null
		try {
			md5 = MessageDigest.getInstance("MD5")
		}catch {
			case e:Exception=>{
				e.printStackTrace()
			}
		}
		//rowKey的组成：时间戳+uid
		val str = System.currentTimeMillis() + ":" + key
		val encode = md5.digest(str.getBytes())
		encode.map("%02x".format(_)).mkString
	}


}

object HBaseUtil extends Serializable {
	def apply(spark:SparkSession): HBaseUtil = new HBaseUtil(spark)
}

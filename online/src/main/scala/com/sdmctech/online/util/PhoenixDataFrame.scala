package com.sdmctech.online.util

import com.sdmctech.online.config.CommonName
import org.apache.hadoop.conf.Configuration
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import org.apache.phoenix.spark._
class PhoenixDataFrame(spark:SparkSession) {

	val conf = new Configuration
	conf.addResource("core-site.xml")
	conf.addResource("hbase-site.xml")
	conf.addResource("hdfs-site.xml")

	def getDataFramefromHBase(tableName:String,column:Seq[String]):DataFrame={
		val df = spark.sqlContext.phoenixTableAsDataFrame(
			tableName,
			column,
			conf = conf,
			zkUrl = Some(CommonName.zkUrl)
		)
		df
	}
	def saveDataFrametoHBase(data:DataFrame,tableName:String): Unit ={
		data.write.mode(SaveMode.Overwrite)
			.options(Map("table" -> tableName, "zkUrl" ->CommonName.zkUrl ))
			.format("org.apache.phoenix.spark").save()
	}
}
object PhoenixDataFrame{

	def apply(spark:SparkSession): PhoenixDataFrame = new PhoenixDataFrame(spark:SparkSession)
}

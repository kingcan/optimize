//package com.sdmctech.online
//
///**
// * @Author: Larkin
// * @Date: 2021/7/2 17:15
// */
//import com.aliyun.odps.TableSchema
//import com.aliyun.odps.data.Record
//import org.apache.spark.sql.functions.col
//import org.apache.spark.sql.{DataFrame, Row, SparkSession}
//import org.apache.spark.ml.feature.{CountVectorizer, CountVectorizerModel, MinHashLSH, MinHashLSHModel}
//import org.apache.spark.ml.linalg.{ SparseVector}
//import org.apache.spark.odps.OdpsOps
//import org.apache.spark.rdd.RDD
//
//
//object minhashJaccardCal {
//  def main(args:Array[String]) = {
//    val spark = SparkSession.builder().enableHiveSupport().appName("minhashJaccardCal").getOrCreate()
//    //输入参数
//    val inputProject = args(0)
//    val inputTable = args(1)
//    val outputProject = args(2)
//    val outputTable = args(3)
//    val usage = s"""
//    Usage： <inputProject> <inputTable> <outputProject> <outputTable>
//  """
//    if(args.length < 4){
//      println("参数错误")
//      sys.error(usage)
//      sys.exit(-1)
//    }
//
//    try{
//      val odpsOps = new OdpsOps(spark.sparkContext)
//      val pair: RDD[(String, String)] = odpsOps.readTable(inputProject,inputTable, (r: Record, _: TableSchema) => (r.getString(0),r.getString(1)) ,100)
//      //-----------------------------------------------------------------------------
//      //计算流入节点的重合度，用MINHASH的方法来近似计算，总共分成两步
//      //1. 数据预处理成bag of word形式的0-1向量，且用sparse向量来表示
//      //2. 调用org.apache.spark.ml.feature.MinHashLSH 来近似计算jaccard距离
//      //下面执行第1步
//      val inputNodeVector: RDD[(String, List[String])] = pair.map(_.swap).combineByKey(
//        (v : String) => List(v),
//        (c : List[String], v : String) => v :: c,
//        (c1 : List[String], c2 : List[String]) => c1 ::: c2
//      ).repartition(100)
//
//      val inputNodeVectorDF = spark.createDataFrame(inputNodeVector).toDF("node","neighbors")
//      val cvModel: CountVectorizerModel = new CountVectorizer().setInputCol("neighbors").setOutputCol("features").setBinary(true).fit(inputNodeVectorDF)
//      val inputNodeVectorDFSparse: DataFrame = cvModel.transform(inputNodeVectorDF).select("node","features")
//
//      val inputNodeVectorDFSparseFilter = spark.createDataFrame(inputNodeVectorDFSparse.rdd.map(row => (row.getAs[String]("node") ,row.getAs[SparseVector]("features"))).map(x => (x._1,x._2,x._2.numNonzeros)).filter(x => x._3 >= 1).map(x => (x._1,x._2))).toDF("node","features")
//
//      //下面执行第2步
//      val mh = new MinHashLSH().setNumHashTables(100).setInputCol("features").setOutputCol("hashes")
//      val model: MinHashLSHModel = mh.fit(inputNodeVectorDFSparseFilter)
//      val inputNodeDistance: DataFrame =  model.approxSimilarityJoin(inputNodeVectorDFSparseFilter,
//        inputNodeVectorDFSparseFilter, 0.7, "JaccardDistance")
//        .select(col("datasetA.node").alias("node1"),col("datasetB.node")
//          .alias("node2"),col("JaccardDistance"))
//      val inputNodeOverlapRatio =  inputNodeDistance.rdd.map(x => {
//        val node1 = x.getString(0)
//        val node2 = x.getString(1)
//        val overlapRatio = 1 - x.getDouble(2)
//        if(node1 < node2) ((node1, node2),overlapRatio) else ((node2, node1),overlapRatio)
//      }).filter(x => x._1._1 != x._1._2)
//
//      //-----------------------------------------------------------------------------
//      //计算流出节点的重合度, 思路与上相同
//      val outputNodeVector: RDD[(String, List[String])] = pair.combineByKey(
//        (v : String) => List(v),
//        (c : List[String], v : String) => v :: c,
//        (c1 : List[String], c2 : List[String]) => c1 ::: c2
//      )
//      val outputNodeVectorDF = spark.createDataFrame(outputNodeVector).toDF("node","neighbors")
//
//      val cvModelOutput: CountVectorizerModel = new CountVectorizer().setInputCol("neighbors").setOutputCol("features").setBinary(true).fit(outputNodeVectorDF)
//      val outputNodeVectorDFSparse: DataFrame = cvModelOutput.transform(outputNodeVectorDF).select("node","features")
//      val outputNodeVectorDFSparseFilter: DataFrame = spark.createDataFrame(outputNodeVectorDFSparse.rdd.map(row => (row.getAs[String]("node") ,row.getAs[SparseVector]("features"))).map(x => (x._1,x._2,x._2.numNonzeros)).filter(x => x._3 >= 1).map(x => (x._1,x._2))).toDF("node","features")
//
//
//      //下面执行第2步
//      val mh2 = new MinHashLSH().setNumHashTables(100).setInputCol("features").setOutputCol("hashes")
//      val outputModel: MinHashLSHModel = mh2.fit(outputNodeVectorDFSparseFilter)
//      val outputNodeOverlapRatio =  outputModel.approxSimilarityJoin(outputNodeVectorDFSparseFilter, outputNodeVectorDFSparseFilter, 0.7, "JaccardDistance").select(col("datasetA.node").alias("node1"),col("datasetB.node").alias("node2"),col("JaccardDistance")).rdd.map(x => {
//        val node1 = x.getString(0)
//        val node2 = x.getString(1)
//        val overlapRatio = 1 - x.getDouble(2)
//        if(node1 < node2) ((node1, node2),overlapRatio) else ((node2, node1),overlapRatio)
//      }).filter(x => x._1._1 != x._1._2)
//
//      //-----------------------------------------------------------------------------
//      //合并到一起
//      val jaccardValuePair: RDD[(String, String, Double, Double)] = inputNodeOverlapRatio.fullOuterJoin(outputNodeOverlapRatio,100).map{case ((x,y),(inValue, outValue)) =>
//        (x,y,inValue.getOrElse(0.0),outValue.getOrElse(0.0))
//      }.filter(x => x._1 != x._2).distinct(100)
//      //      写入结果表
//      val saveTransfer = (v:Tuple4[String, String, Double, Double] , record:Record, schema: TableSchema) => {
//        record.set("srcid", v._1)
//        record.set("tarid", v._2)
//        record.set("invalue", v._3)
//        record.set("outvalue", v._4)
//      }
//      odpsOps.saveToTable(outputProject,outputTable,jaccardValuePair,saveTransfer,isOverWrite = true)
//    }catch {
//      case ex: Exception => {
//        throw ex
//      }
//    } finally {
//      spark.stop()
//    }
//  }
//}

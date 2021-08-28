package com.sdmctech.offline.service

import com.sdmctech.offline.common.TService
import com.sdmctech.offline.config.CommonName
import com.sdmctech.offline.dao.LogisticRegressionDao
import com.sdmctech.offline.mapper.LogisticRegressionMapper
import com.sdmctech.offline.model.LogisticRegressionRank
import com.sdmctech.offline.util.{DataFrameUtil, TimeUtil}
import org.apache.spark.ml.linalg.DenseVector
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.rank
import org.apache.spark.sql.functions._
import org.apache.spark.SparkConf
import org.apache.spark.ml.linalg.DenseVector
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{SparkSession, functions}

class LogisticRegressionService extends TService{
	override def dataAnalysis(): Any ={
		val lr =new LogisticRegressionRank()
		//val tmp =LogisticRegressionDao.getTrainingData
		//println("打印了useritem click联合表")
		//tmp.show()
		val lrModel = lr.getModel( LogisticRegressionDao.getTrainingData, LogisticRegressionDao.spark, CommonName.lrAssembleVector)
		val i2 = lr.getRank(lrModel, LogisticRegressionDao.getTestData, CommonName.lrAssembleVector)
		println("-------打印i2(逻辑回归后的结果)------------")
		i2.show()
		i2.printSchema()
		import LogisticRegressionDao.spark.implicits._
		//val i3 = i2.select("uid","itemid","features","rawPrediction","probability","prediction")
		val getProbability = LogisticRegressionDao.spark.udf.register("getProbability",(probability:DenseVector)=>probability(0))
		val i3 =i2.select("uid", "itemid", "probability", "prediction")
		  .withColumn("noclick",getProbability(col("probability")))
//		val i3 = i2.select("uid", "itemid", "probability", "prediction").rdd.map(row => {
//			val uid = row.getAs[Integer]("uid")
//			val itemid = row.getAs[Integer]("itemid")
//			val probability = row.getAs[DenseVector]("probability")
//			val noclick = probability(0)
//			val prediction = row.getAs[Double]("prediction")
//			(uid, itemid, noclick, prediction)
//		}).toDF("uid", "itemid", "noclick", "prediction")
		println("-------打印------------")
		//i3.show()
		val i4 = i3.withColumn("rank", rank().over(Window.partitionBy("uid").orderBy("noclick"))).select("*")
		println("----------打印排完序后的dataframe------------")
		i4.printSchema() //uid is Long
		//i4.show()
    val saveI4=i4.withColumn("uid",col("uid").cast("string"))
		//DataFrameUtil.groupBy(saveI4,"uid","itemid").printSchema()//先groupBy聚合再转string
		val saveI5 = DataFrameUtil.groupBy(saveI4,"uid","itemid")
			  .withColumn("itemid",col("itemid").cast("string"))
		LogisticRegressionDao.saveData(saveI5,CommonName.lrResult)
		val temp = LogisticRegressionDao.readData(CommonName.itemFeatureHBase)
			.withColumn("itemid",col("itemid").cast("int"))
		val id2assetid = i4.join(temp,"itemid").select("uid","parent_asset_id").distinct()
		val id2assetidSave = DataFrameUtil.groupBy(id2assetid,"uid","parent_asset_id")
			.withColumn("uid",col("uid").cast("string"))
		  .withColumn("parent_asset_id",col("parent_asset_id").cast("string"))
		val timeUtil =new TimeUtil()
		val currentData = timeUtil.getCurrentDate()
		val addTimestamp =LogisticRegressionDao.spark.udf.register("addTimestamp",(uid:String)=>currentData+uid)
		val recommendHistory  = id2assetidSave.withColumn("uid",addTimestamp(col("uid")))
    LogisticRegressionDao.saveData(id2assetidSave,CommonName.lrResult)
		LogisticRegressionDao.saveData(recommendHistory,CommonName.recommend_history)
	}
}

package com.sdmctech.online.service

import com.sdmctech.online.common.TService
import com.sdmctech.online.config.CommonName
import com.sdmctech.online.dao.LogisticRegressionDao
import com.sdmctech.online.model.LogisticRegressionRank
import com.sdmctech.online.util.TimeUtil
import org.apache.spark.ml.linalg.DenseVector
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.rank
import org.apache.spark.sql.functions._
import org.apache.spark.SparkConf
import org.apache.spark.ml.linalg.DenseVector
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{SparkSession, functions}

class LogisticRegressionService extends TService{
	override def dataAnalysis(): Any = {
		val lr =new LogisticRegressionRank()
		val pipelineModel =lr.getPipelineModel(LogisticRegressionDao.training, LogisticRegressionDao.spark, CommonName.lrAssembleVector)
		//lr.savePMMLmodel(pipelineModel,"testLR.pmml")
		lr.savePMMLmodel(pipelineModel)
	}
//	override def dataAnalysis(): Any ={
//		val lr =new LogisticRegressionRank()
//		val lrModel = lr.getModel( LogisticRegressionDao.training, LogisticRegressionDao.spark, CommonName.lrAssembleVector)
//		val i2 = lr.getRank(lrModel, LogisticRegressionDao.test, CommonName.lrAssembleVector)
//		println("-------打印i2(逻辑回归后的结果)------------")
//		//i2.show()
//		import LogisticRegressionDao.spark.implicits._
//		//val i3 = i2.select("uid","itemid","features","rawPrediction","probability","prediction")
//		val i3 = i2.select("uid", "itemid", "probability", "prediction").rdd.map(row => {
//			val uid = row.getAs[Long]("uid")
//			val itemid = row.getAs[Integer]("itemid")
//			val probability = row.getAs[DenseVector]("probability")
//			val noclick = probability(0)
//			val prediction = row.getAs[Double]("prediction")
//			(uid, itemid, noclick, prediction)
//		}).toDF("uid", "itemid", "noclick", "prediction")
//		println("-------打印------------")
//		//i3.show()
//		val i4 = i3.withColumn("rank", rank().over(Window.partitionBy("uid").orderBy("noclick"))).select("*")
//		println("----------打印排完序后的dataframe------------")
//		i4.printSchema() //uid is Long
//		LogisticRegressionDao.modelUtil.saveLRrank(i4,"after_lr")
//		val temp = LogisticRegressionDao.phoenixDataFrame.getDataFramefromHBase(CommonName.itemFeatureHBase, Seq("ITEMID", "0.PARENT_ASSET_ID")).toDF("itemid", "parent_asset_id")
//		val id2assetid = i4.join(temp,"itemid").select("uid","parent_asset_id").distinct()
//		LogisticRegressionDao.modelUtil.saveRecall(CommonName.lrResult,"recall",id2assetid,"after_lr_asset_id","uid","parent_asset_id")
//		val timeUtil =new TimeUtil()
//		val currentData = timeUtil.getCurrentDate()
//		val recommendHistory  = id2assetid.rdd.map(row=>{
//			val rowkey =currentData+row.getAs[String]("uid")
//			val itemid =row.getAs[String]("parent_asset_id")
//			(rowkey,itemid)
//		}).toDF("uid","parent_asset_id").select("uid","parent_asset_id")
//		LogisticRegressionDao.modelUtil.saveRecall(CommonName.recommend_history,"recall",recommendHistory,"after_lr_asset_id","uid","parent_asset_id")
//
//	}
}

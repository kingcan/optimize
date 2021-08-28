package com.sdmctech.offline.dao

import org.apache.spark.sql.functions._
import com.sdmctech.offline.common.TDao
import com.sdmctech.offline.config.{CommonName, SQLforModelUtil}
import com.sdmctech.offline.mapper.{CollaborativeFilteringMapper, LogisticRegressionMapper}
import com.sdmctech.offline.util.{DataFrameUtil, EnvUtil}
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.{DataFrame, SparkSession}

object LogisticRegressionDao extends TDao{
	val spark: SparkSession =EnvUtil.take()
	val trainingUser = sparkHBaseUtil.readDataFromHBase(LogisticRegressionMapper.user_catalog)
		.withColumn("uid",col("uid").cast("int"))
		.withColumn("userOneHotfeatures",DataFrameUtil.parseStringToVector(col("userOneHotfeatures")))
	//trainingUser.show()
	val trainingItem = sparkHBaseUtil.readDataFromHBase(LogisticRegressionMapper.item_catalog)
		.withColumn("itemid",col("itemid").cast("int"))
		.withColumn("itemOneHotfeatures",DataFrameUtil.parseStringToVector(col("itemOneHotfeatures")))
		.withColumn("categoryvector",DataFrameUtil.parseStringToVector(col("categoryvector")))
		.withColumn("tagsvector",DataFrameUtil.parseStringToVector(col("tagsvector")))
		.withColumn("directorvector",DataFrameUtil.parseStringToVector(col("directorvector")))
		.withColumn("writervector",DataFrameUtil.parseStringToVector(col("writervector")))
		.withColumn("actorvector",DataFrameUtil.parseStringToVector(col("actorvector")))
	//trainingItem.show()
	//val modelUtil = ModelUtil(EnvUtil.take())
	//val phoenixDataFrame = PhoenixDataFrame(spark)
	def getTrainingData: DataFrame = {
		val tmp = spark.sql(SQLforModelUtil.getUserItemClick)
			.toDF("uid","itemid","label")
		val tmp1 = tmp.join(trainingUser, Seq("uid"))
		//tmp1.show()
		val tmp2 =tmp1.join(trainingItem, Seq("itemid"))
tmp2
	}
//	val training: DataFrame = modelUtil.getFeatureOneHot(spark.sql(SQLforModelUtil.getUserItemClick),
//		CommonName.userFeatureHBase, CommonName.itemFeatureHBase,
//		CommonName.userFeatureColumnName, CommonName.itemFeatureColumnName, phoenixDataFrame).toDF("itemid", "uid", "label", "user_feature1", "user_feature2", "user_feature3",
//		"AGE", "AGEREGISTERED", "userOneHotFeature",
//		"item_feature1", "EPISODE_TOTAL", "item_feature3", "item_feature4", "item_feature5", "item_feature6",
//		"item_feature7", "item_feature8", "HIT_COUNT", "item_feature10", "STAR",
//		"item_feature12", "DURATION", "itemOneHotFeature", "TAGS", "CATEGORY", "ACTOR", "WRITER", "DIRECTOR")
//	val recommendation: DataFrame = phoenixDataFrame.getDataFramefromHBase(CommonName.rsRecall.toUpperCase, Seq("UID", "0.ITEMID")).toDF("uid", "recommendations")
//	//当前获取特征时数据正确，label也正确
//	val preTest: DataFrame = modelUtil.splitArrayInDataFrame(recommendation, "uid", "recommendations", "itemid")
//	val test: DataFrame = modelUtil.getFeatureOneHot(preTest,
//		CommonName.userFeatureHBase, CommonName.itemFeatureHBase,
//		CommonName.userFeatureColumnName, CommonName.itemFeatureColumnName, phoenixDataFrame).toDF("itemid", "uid", "user_feature1", "user_feature2", "user_feature3",
//		"AGE", "AGEREGISTERED", "userOneHotFeature",
//		"item_feature1", "EPISODE_TOTAL", "item_feature3", "item_feature4", "item_feature5", "item_feature6",
//		"item_feature7", "item_feature8", "HIT_COUNT", "item_feature10", "STAR",
//		"item_feature12", "DURATION", "itemOneHotFeature", "TAGS", "CATEGORY", "ACTOR", "WRITER", "DIRECTOR")

	def getTestData: DataFrame = {
		val recommendations: UserDefinedFunction = spark.udf.register("recommendations", (str: String) => str.substring(1, str.length - 1).split(",").map(_.trim))
		val recommendation: DataFrame = sparkHBaseUtil.readDataFromHBase(CollaborativeFilteringMapper.als_results_catalog("itemid"))
			.withColumn("recommendations", recommendations(col("itemid")))
			.select("uid", "recommendations")
		//.toDF("uid", "recommendations")
		val preTest: DataFrame = DataFrameUtil.splitArrayInDataFrame(recommendation, "uid", "recommendations", "itemid")
			.withColumn("uid", col("uid").cast("int"))
			.withColumn("itemid", col("itemid").cast("int"))
		preTest.show()
		val test: DataFrame = preTest.join(trainingUser, Seq("uid")).join(trainingItem, "itemid")
    test
	}
	def saveData(data:DataFrame,tableName:String): Unit ={
		val catalog =data.columns(1) match{
			case "itemid"=>LogisticRegressionMapper.lr_results_catalog
			case "parent_asset_id"=>LogisticRegressionMapper.lr_result_assetid_catalog(tableName)
		}
		sparkHBaseUtil.insertDataFrameIntoHBase(data,catalog,CommonName.regionsOfHBase)
	}
	def readData(tableName:String): DataFrame ={
		val catalog = tableName match {
			case CommonName.itemFeatureHBase => LogisticRegressionMapper.item_parentassetid_catalog
		}
		sparkHBaseUtil.readDataFromHBase(catalog)
	}
}

package com.sdmctech.online.dao

import com.sdmctech.online.common.TDao
import com.sdmctech.online.config.{CommonName, SQLforModelUtil}
import com.sdmctech.online.util.{EnvUtil, ModelUtil, PhoenixDataFrame}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.storage.StorageLevel

object LogisticRegressionDao extends TDao{
	val spark: SparkSession =EnvUtil.take()
	val modelUtil: ModelUtil = ModelUtil(EnvUtil.take())
	val phoenixDataFrame: PhoenixDataFrame = PhoenixDataFrame(spark)
	spark.sql(SQLforModelUtil.getUserItemClick).printSchema()
	val training: DataFrame = modelUtil.getFeatureOneHot(spark.sql(SQLforModelUtil.getUserItemClick),
		CommonName.userFeatureHBase, CommonName.itemFeatureHBase,
		CommonName.userFeatureColumnName, CommonName.itemFeatureColumnName, phoenixDataFrame).toDF("itemid", "uid", "label", "user_feature1", "user_feature2", "user_feature3",
		"AGE", "AGEREGISTERED", "userOneHotFeature",
		"item_feature1", "EPISODE_TOTAL", "item_feature3", "item_feature4", "item_feature5", "item_feature6",
		"item_feature7", "item_feature8", "HIT_COUNT", "item_feature10", "STAR",
		"item_feature12", "DURATION", "itemOneHotFeature", "TAGS", "CATEGORY", "ACTOR", "WRITER", "DIRECTOR")
	//training.show()
	//training.persist(StorageLevel.MEMORY_ONLY_SER)
	val recommendation: DataFrame = phoenixDataFrame.getDataFramefromHBase(CommonName.rsRecall.toUpperCase, Seq("UID", "0.ITEMID")).toDF("uid", "recommendations")
	//当前获取特征时数据正确，label也正确
	val preTest: DataFrame = modelUtil.splitArrayInDataFrame(recommendation, "uid", "recommendations", "itemid")
	val test: DataFrame = modelUtil.getFeatureOneHot(preTest,
		CommonName.userFeatureHBase, CommonName.itemFeatureHBase,
		CommonName.userFeatureColumnName, CommonName.itemFeatureColumnName, phoenixDataFrame).toDF("itemid", "uid", "user_feature1", "user_feature2", "user_feature3",
		"AGE", "AGEREGISTERED", "userOneHotFeature",
		"item_feature1", "EPISODE_TOTAL", "item_feature3", "item_feature4", "item_feature5", "item_feature6",
		"item_feature7", "item_feature8", "HIT_COUNT", "item_feature10", "STAR",
		"item_feature12", "DURATION", "itemOneHotFeature", "TAGS", "CATEGORY", "ACTOR", "WRITER", "DIRECTOR")

	 def saveData()={

	 }


}

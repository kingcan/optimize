package com.sdmctech.online.util

import com.sdmctech.online.config.CommonName
import org.apache.spark.sql.{DataFrame, SparkSession, functions}
import org.apache.spark.sql.functions.{col, collect_list}

class ModelUtil (spark:SparkSession) extends Serializable {

	def saveOneColumnIntoHBase(tableName:String,columnFamilyName:String,recommend:DataFrame,
														 cellName:String,keyId:String,collectColumn:String):Unit={
		val HBase = new HBaseUtil(spark)
		HBase.putData(tableName,recommend,columnFamilyName,cellName,collectColumn)
	}
	//get feature matrix with user-item-click/noclick
	def getFeatureOneHot(targetData:DataFrame,userTableName:String,itemTableName:String,
											 userTableColumn:Seq[String],itemTableColumn:Seq[String],
											 phoenixDataFrame:PhoenixDataFrame):DataFrame ={

		val allUserFeature =phoenixDataFrame.getDataFramefromHBase(userTableName,userTableColumn)
		allUserFeature.printSchema()
		val hBaseUtil = new HBaseUtil(spark)
		hBaseUtil.getSparseVectorDataOneHot(CommonName.userFeatureHBase,
			CommonName.featureCF,CommonName.userOneHotCode,CommonName.UserColName).show()
		val allUserFeatureWithHotCode =joinHotCodebyColumn(allUserFeature,CommonName.userFeatureHBase,
			CommonName.featureCF,CommonName.userOneHotCode,CommonName.UserColName)
		allUserFeatureWithHotCode.show()
		val allItemFeature = phoenixDataFrame.getDataFramefromHBase(itemTableName,itemTableColumn)
		val allItemFeatureWithHotCode =joinHotCodebyColumn(allItemFeature,CommonName.itemFeatureHBase,
			CommonName.featureCF,CommonName.itemOneHotCode,CommonName.itemColName)
		val allItemFeatureWithMultiHot1 =joinHotCodebyColumn(allItemFeatureWithHotCode,CommonName.itemFeatureHBase,
			CommonName.featureCF,CommonName.itemMultiHotCode(0),CommonName.itemColName)
		val allItemFeatureWithMultiHot2 =joinHotCodebyColumn(allItemFeatureWithMultiHot1,CommonName.itemFeatureHBase,
			CommonName.featureCF,CommonName.itemMultiHotCode(1),CommonName.itemColName)
		val allItemFeatureWithMultiHot3 =joinHotCodebyColumn(allItemFeatureWithMultiHot2,CommonName.itemFeatureHBase,
			CommonName.featureCF,CommonName.itemMultiHotCode(2),CommonName.itemColName)
		val allItemFeatureWithMultiHot4 =joinHotCodebyColumn(allItemFeatureWithMultiHot3,CommonName.itemFeatureHBase,
			CommonName.featureCF,CommonName.itemMultiHotCode(3),CommonName.itemColName)
		val allItemFeatureWithMultiHot5 =joinHotCodebyColumn(allItemFeatureWithMultiHot4,CommonName.itemFeatureHBase,
			CommonName.featureCF,CommonName.itemMultiHotCode(4),CommonName.itemColName)
		println("---------------allItem------------------")
		//allUserFeatureWithHotCode.show()
		println("---------------action------------------")
		//targetData.show()
		val tmp1 = targetData.join(allUserFeatureWithHotCode,Seq("uid"))
		println("---------------tmp1------------------")
		//tmp1.show()
		val tmp2 =tmp1.join(allItemFeatureWithMultiHot5,Seq("itemid"))
		println("---------------tmp2------------------")
		//tmp2.show()
		val rs = tmp1.join(allItemFeatureWithMultiHot5,Seq("itemid"))
		println("---------------rs------------------")
		//rs.show()
		rs
	}

	def joinHotCodebyColumn(dataLeft:DataFrame,tableName:String,cf:String,columnName:String,pkName:String):DataFrame={
		val hBaseUtil = new HBaseUtil(spark)
		dataLeft.join(hBaseUtil.getSparseVectorDataOneHot(tableName,cf,columnName,pkName),Seq(pkName))
	}


	//do not toDF,cause it has,just from user-item-rating table
	def getDataFramefromHive(str: String):DataFrame={
		val actions = spark.sql(str)
		actions
	}
	def getUserProfilefromHive(str: String,indexName:String):DataFrame={
		val result =spark.sql(str).toDF(CommonName.UserColName,indexName,"play_duration")
		result
	}
	def splitArrayInDataFrame(targetDataFrame:DataFrame,pkName:String,explodeName:String,newName:String):DataFrame= {
		val result = targetDataFrame.withColumn(newName, functions.explode(functions.col(explodeName))).select(col(pkName),
			col(newName))
		result
	}

	//把召回策略生成的推荐候选集存储到HBase
	def saveALSRecall(recommend:DataFrame,cell:String):Unit={

		//LR中要反着过来
		val recommList = recommend.groupBy(col("uid"))
			.agg(collect_list("itemid"))
			.withColumnRenamed("collect_list(itemid)",
				"itemid")
			.select(col("uid"),
				col("itemid"))

		val HBase = new HBaseUtil(spark)
		HBase.putResultData(CommonName.rsRecall,recommList,CommonName.recall_cf,cell)
	}
	def saveLRrank(recommend:DataFrame,cell:String):Unit={

		//LR中要反着过来
		val recommList = recommend.groupBy(col("uid"))
			.agg(collect_list("itemid"))
			.withColumnRenamed("collect_list(itemid)",
				"itemid")
			.select(col("uid"),
				col("itemid")).distinct()
		val HBase = new HBaseUtil(spark)
		HBase.putResultData(CommonName.lrResult,recommList,CommonName.recall_cf,cell)
	}

	def saveRecall(recommend:DataFrame,cell:String):Unit={

		//LR中要反着过来
		val recommList = recommend.groupBy(col("uid"))
			.agg(collect_list("itemid"))
			.withColumnRenamed("collect_list(itemid)",
				"itemid")
			.select(col("uid"),
				col("itemid"))

		val hbaseByPhoenix = new PhoenixDataFrame(spark)
		hbaseByPhoenix.saveDataFrametoHBase(recommList,CommonName.rsRecall.toUpperCase)
	}
	def saveRecall(tableName:String,columnFamilyName:String,recommend:DataFrame,
								 cellName:String,keyId:String,collectColumn:String):Unit={

		val existingName = "collect_list("+collectColumn +")"
		val recommList = recommend.groupBy(col(keyId))
			.agg(collect_list(collectColumn))
			.withColumnRenamed(existingName,
				collectColumn)
			.select(col(keyId),
				col(collectColumn))

		val HBase = new HBaseUtil(spark)
		HBase.putResultData(tableName,recommList,columnFamilyName,cellName)
	}

	//把召回策略生成的推荐候选集存储到HBase
	def saveRecall(tableName:String,columnFamilyName:String,recommend:DataFrame,
								 keyId:String,collectColumn:String):Unit={

		val existingName = "collect_list("+collectColumn +")"
		val recommList = recommend.groupBy(col(keyId))
			.agg(collect_list(collectColumn))
			.withColumnRenamed(existingName,
				collectColumn)
			.select(col(keyId),
				col(collectColumn))
		val phoenixUtil = new PhoenixDataFrame(spark)
		phoenixUtil.saveDataFrametoHBase(recommList,tableName)
	}


}
object ModelUtil extends Serializable {
	//var spark:SparkSession = _
	def apply(spark:SparkSession):ModelUtil = new ModelUtil(spark)
}

package com.sdmctech.offline.service

import com.sdmctech.offline.common.TService
import com.sdmctech.offline.config.CommonName
import com.sdmctech.offline.dao.OneHotCodeHandleDao
import com.sdmctech.offline.model.OneHotCode
import com.sdmctech.offline.util.EnvUtil
import org.apache.spark.sql.functions._
class OneHotCodeHandleService extends TService{
	override def dataAnalysis(): Any = {
		val spark =EnvUtil.take()
		val modelUtil = ModelUtil(spark)
		val allUserFeature = OneHotCodeHandleDao.readData(CommonName.userFeatureHBase)
		val allItemFeature =OneHotCodeHandleDao.readData(CommonName.itemFeatureHBase)
		//val phoenixDataFrame = PhoenixDataFrame(spark)

		//val allUserFeature = phoenixDataFrame.getDataFramefromHBase(CommonName.userFeatureHBase,CommonName.userFeatureColumnName)
		//val allItemFeature = phoenixDataFrame.getDataFramefromHBase(CommonName.itemFeatureHBase,CommonName.itemFeatureColumnName)
		allUserFeature.show(truncate = false)
		val categoricalColumns = allUserFeature.drop("TYPE","UID","AGE","AGEREGISTERED").columns
		val userOneHotResult = OneHotCode.oneHotCodeOperation(allUserFeature,categoricalColumns,"userOneHot")
			.withColumn("userOneHotfeatures", col("userOneHotfeatures").cast("string"))
		println("user特征独热编码后")
		userOneHotResult.show(truncate = false)
		//modelUtil.saveOneColumnIntoHBase(CommonName.userFeatureHBase,"0",userOneHotResult,
			//"userOneHotFeature","uid".toUpperCase,"userOneHotfeatures")
     OneHotCodeHandleDao.saveData(userOneHotResult.select("uid","userOneHotfeatures"),
			 CommonName.userFeatureHBase,"userOneHotfeatures")
		val test = allItemFeature.withColumn("splitCols",split(col("CATEGORY_ID"), ","))
		allUserFeature.printSchema()
		allUserFeature.show(truncate = false)
		//test2.show()

		//val multiColumns = Array("tags_id","category_id","actor_id","writer_id","director_id").map(_.toUpperCase)
		//val itemOneColumns = Array("type","area_tag_id","online_year_tag_id","grade").map(_.toUpperCase)
		val multiColumns = Array("tags_id","category_id","actor_id","writer_id","director_id")
		val itemOneColumns = Array("type","area_tag_id","online_year_tag_id","grade")
		val itemOneHotResult = OneHotCode.oneHotCodeOperation(allItemFeature,itemOneColumns,"itemOneHot")
			.withColumn("itemOneHotfeatures", col("itemOneHotfeatures").cast("string"))
		println("item特征独热编码后")
		//modelUtil.saveOneColumnIntoHBase(CommonName.itemFeatureHBase,"0",itemOneHotResult,"itemOneHotFeature","itemid".toUpperCase,"itemOneHotfeatures")
     OneHotCodeHandleDao.saveData(itemOneHotResult.select("itemid","itemOneHotfeatures"),CommonName.itemFeatureHBase,"itemOneHotfeatures")
		for( i <- multiColumns) {
			val currentColumn=i.substring(0,i.indexOf("_"))
			val tmpItem = OneHotCode.multiHotEncoderExample(allItemFeature, i, currentColumn, "itemid")
				  .withColumn(currentColumn +"vector",col(currentColumn +"vector").cast("string"))
			tmpItem.show()
			//modelUtil.saveOneColumnIntoHBase(CommonName.itemFeatureHBase,"0",tmpItem, currentColumn,"itemid".toUpperCase,currentColumn +"vector")
		   OneHotCodeHandleDao.saveData(tmpItem.select("itemid",currentColumn +"vector"),CommonName.itemFeatureHBase,currentColumn +"vector")
		}


	}
}

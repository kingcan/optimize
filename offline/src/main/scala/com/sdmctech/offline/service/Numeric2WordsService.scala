package com.sdmctech.offline.service

import com.sdmctech.offline.common.TService
import com.sdmctech.offline.config.CommonName
import com.sdmctech.offline.dao.OneHotCodeHandleDao
import com.sdmctech.offline.util.EnvUtil
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, explode, split}
class Numeric2WordsService extends TService with Serializable {
	override def dataAnalysis(): Any = {
		val spark=EnvUtil.take()
		//val phoenixDataFrame = PhoenixDataFrame(spark)

		val categoryProperties = spark.sql("select * from "+CommonName.Bo_Category_Properties+" where id is not NULL")
		val starsProperties =spark.sql("select * from "+CommonName.Bo_Stars_Properties+" where id is not NULL")
		val tagProperties =spark.sql("select * from "+CommonName.Bo_Tag_Properties+" where id is not NULL")
		categoryProperties.show(truncate = false)
		starsProperties.show(truncate = false)
		tagProperties.show(truncate = false)
   val movieFeature =OneHotCodeHandleDao.readData(CommonName.itemFeatureHBase)
		//val movieFeature =phoenixDataFrame.getDataFramefromHBase(CommonName.itemFeatureHBase,CommonName.itemFeatureColumnName)
		movieFeature.show(truncate = false)
		NumericArray2String(movieFeature,spark,"relation_id",
			"language_code","en_US","relation_name","itemid",
			"DIRECTOR_ID".toLowerCase(),"ACTOR_ID".toLowerCase(),
			"WRITER_ID".toLowerCase(),"CATEGORY_ID".toLowerCase(),"TAGS_ID".toLowerCase())

	}
	def NumericArray2String(input: DataFrame,spark: SparkSession, joinCol:String,
													filterCol:String, filterColValue:String,
													targetCol:String,keyCol:String,splitCols:String*): Unit = {
		for(splitCol<-splitCols) {
			val Df2 = input.withColumn(splitCol, split(col(splitCol), ",")
			).withColumn(joinCol, explode(col(splitCol)))
			val joinData2 = getProperties(splitCol,spark).filter(row => row.getAs(filterCol) == filterColValue).select(joinCol, targetCol).distinct()
			val Df3 = Df2.join(joinData2, joinCol).select(keyCol, targetCol).toDF("itemid",splitCol+"2word")
			Df3.show(truncate = false)
			//val modelUtil = ModelUtil(spark)
			OneHotCodeHandleDao.saveData(Df3,CommonName.itemFeatureHBase,splitCol+"2word")
			//modelUtil.saveRecall(CommonName.itemFeatureHBase,CommonName.featureCF,Df3,CommonName.itemColName.toUpperCase,splitCol+"2WORD")
		}
	}


	def getProperties(whichProperty: String,spark: SparkSession):DataFrame={

		whichProperty match {
			case "category_id" => spark.sql("select * from "+CommonName.Bo_Category_Properties+" where id is not NULL")
				.toDF("id","relation_id","relation_name","language_code",
					"category_code","unique_code","alias","icon","poster","description")
			case "tags_id" => spark.sql("select * from "+CommonName.Bo_Tag_Properties+" where id is not NULL")
			case _       => spark.sql("select * from "+CommonName.Bo_Stars_Properties+" where id is not NULL")
		}

	}

}

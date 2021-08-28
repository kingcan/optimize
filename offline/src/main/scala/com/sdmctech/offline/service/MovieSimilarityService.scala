package com.sdmctech.offline.service

import com.sdmctech.offline.common.TService
import com.sdmctech.offline.config.CommonName
import com.sdmctech.offline.dao.MovieSimilarityDao
import com.sdmctech.offline.mapper.MovieSimilarityMapper
import com.sdmctech.offline.util.{DataFrameUtil, EnvUtil}
import org.apache.spark.sql.Row
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window
/**
	* @Author: Larkin
	* @Date: 2021/5/21 11:13
	*/
class MovieSimilarityService extends TService with Serializable {
	override def dataAnalysis(): Any = {
		val spark=EnvUtil.take()
	//	val phoenixUtil = PhoenixDataFrame(spark)
//		val movieFeature =phoenixUtil.getDataFramefromHBase(CommonName.itemFeatureHBase,
//			Seq("ITEMID","0.parent_asset_id".toUpperCase,"0.DIRECTOR_ID2WORD","0.ACTOR_ID2WORD"))
//			.select("parent_asset_id".toUpperCase,"DIRECTOR_ID2WORD","ACTOR_ID2WORD")
//			.toDF("parent_asset_id", "actor", "director")
//			.na.fill("Empty")
val movieFeature =MovieSimilarityDao.getSimilarityData
	.select("parent_asset_id","actor_id2word","director_id2word")
	.toDF("parent_asset_id", "actor", "director")
	.na.fill("Empty")
		movieFeature.show(truncate = false)
		//movieFeature.show()
		import spark.implicits._
		val crossJoinResultDF = movieFeature.crossJoin(movieFeature)
		//crossJoinResultDF.show()
		crossJoinResultDF.printSchema()
		val crossJoinResultDF2 = crossJoinResultDF.rdd.map(row => {
			val itemA = row.get(0).toString
			val itemB = row.get(3).toString
			var setA = Array[String]()
			var setB = Array[String]()
			//row.getAs[String](1).split("(").filterNot(_.equals("WrappedArray"))
			val seqTmp = getRowData(row,1) + "," + getRowData(row,2)
			val seqTmp2 = getRowData(row,4) + "," + getRowData(row,5)
			//			val seqTmp = row.get(1).toString + "," + row.get(2).toString
			//			val seqTmp2 = row.get(4).toString + "," + row.get(5).toString
			//println(seqTmp)
			setA = seqTmp.split(",").filterNot(_.equals("Empty"))
			setB = seqTmp2.split(",").filterNot(_.equals("Empty"))
			//	println(setA.mkString(" "))
			val similarity = jaccard(setA, setB)
			(itemA, itemB, similarity)
		}).toDF("AssetId1", "AssetId2", "similarity")
		//crossJoinResultDF2.show()

		val i4 = crossJoinResultDF2.withColumn("rank", rank().over(Window.partitionBy("AssetId1")
			.orderBy("similarity"))).select("*").filter($"rank" < 20)
		println("----------打印排完序后的dataframe------------")
		//i4.show()
		//val modelUtil =new ModelUtil(spark)
		val tmpAssetid =DataFrameUtil.groupBy(i4,"AssetId1","AssetId2")
		val tmpSimilarity =DataFrameUtil.groupBy(i4,"AssetId1","similarity")
		val result =tmpAssetid.join(tmpSimilarity,Seq("AssetId1"))
			  .withColumn("AssetId2",col("AssetId2").cast("string"))
			  .withColumn("similarity",col("similarity").cast("string"))
		MovieSimilarityDao.saveSimilarityData(MovieSimilarityMapper.similarity_result_catalog,result)
		//modelUtil.saveRecall(CommonName.movie_similarity ,"cf",i4,"similarItemList","AssetId1","AssetId2")
		//modelUtil.saveRecall(CommonName.movie_similarity,"cf",i4,"similarValue","AssetId1","similarity")
	}
	def getRowData(row:Row,i:Int):String={
		val data =if (row.get(i) != null) {row.get(i).toString} else {"NULL"}
		data
	}
	def jaccard(p1: Seq[Any], p2: Seq[Any]): Double = {

		val anb = p1.intersect(p2).distinct
		val aub = p1.union(p2).distinct

		val jaccardcoefficient = anb.length.toDouble / aub.length

		1-jaccardcoefficient

	}

}

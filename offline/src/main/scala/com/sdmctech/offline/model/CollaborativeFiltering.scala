package com.sdmctech.offline.model

import java.text.SimpleDateFormat
import java.util.Date

import com.sdmctech.offline.config.CommonName
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.recommendation.{ALS, ALSModel}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, explode}

import scala.collection.mutable.ArrayBuffer

class CollaborativeFiltering(data:DataFrame) extends Serializable {
	def getModel(maxIter:Int,
							 rankArray:Array[Int],
							 regArray:Array[Double],
							 alphaArray:Array[Double]):ALSModel= {

		val Array(training, test) =
			data.randomSplit(Array(0.8, 0.2))

		var mapModel = Map[Double,ALSModel]()
		val listMSE = ArrayBuffer[Double]()

		for(rank <- rankArray;
				reg <- regArray;
				alpha <- alphaArray) {
			val als = new ALS()
				.setMaxIter(maxIter)
				.setUserCol(CommonName.UserColName)
				.setItemCol(CommonName.itemColName)
				.setRatingCol(CommonName.ratingName)
				.setRank(rank)
				.setRegParam(reg)
				.setImplicitPrefs(true) //
				.setAlpha(alpha)

			val model = als.fit(training)
			//cold start
			model.setColdStartStrategy("drop")
			val predict = model.transform(test)
			val rmse = getEvaluate(predict)
			listMSE += rmse
			mapModel += (rmse->model)
		}
		//get the best model
		val minMSE = listMSE.min
		val bestModel = mapModel(minMSE)
		saveModel(bestModel)
		bestModel

	}

	def saveModel(bestModel:ALSModel):Unit={
		val format = new SimpleDateFormat(CommonName.dateFormatStr)
		val date = format.format(new Date())
		val modelPath = CommonName.ALSmodelSavePath +date
		bestModel.write.overwrite().save(modelPath)
	}

	def getEvaluate(predict:DataFrame):Double= {

		val re = new RegressionEvaluator()
			.setMetricName("rmse")
			.setLabelCol(CommonName.ratingName)
			.setPredictionCol("prediction")

		val rmse = re.evaluate(predict)
		rmse
	}

	def getALSRecall(model:ALSModel, spark:SparkSession):DataFrame={
		val list = model.recommendForAllUsers(CommonName.ALSrecommendNumber)
		transformRecommendationFormat(list,spark)
	}

	def getALSRecallForUserSubSet(model:ALSModel,
																spark:SparkSession,subsetData:DataFrame):DataFrame={
		val users = subsetData.select(CommonName.UserColName).distinct()
		val list = model.recommendForUserSubset(users,CommonName.ALSrecommendNumber)
		transformRecommendationFormat(list,spark)
	}

	def transformRecommendationFormat(list:DataFrame,spark:SparkSession):DataFrame={
		import spark.implicits._
		val recallData = list.withColumn("recommend",
			explode(col("recommendations")))
			.drop("recommendations")
			.select(CommonName.UserColName,"recommend")
			.rdd.map(row=>{
			val uid = row.getInt(0)
			val recommend = row.getStruct(1)
			val itemid = recommend.getAs[Int](CommonName.itemColName)
			(uid,itemid)
		}).toDF(CommonName.UserColName,CommonName.itemColName)
		recallData
	}
}
object CollaborativeFiltering{
	def apply(data:DataFrame): CollaborativeFiltering = new CollaborativeFiltering(data)
}

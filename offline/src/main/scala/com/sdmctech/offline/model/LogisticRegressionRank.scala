package com.sdmctech.offline.model

import java.text.SimpleDateFormat
import java.util.Date

import com.sdmctech.offline.config.CommonName
import org.apache.spark.ml.classification.{LogisticRegression, LogisticRegressionModel}
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.{DataFrame, SparkSession}

class LogisticRegressionRank extends Serializable {
	def getModel(training:DataFrame,
							 spark:SparkSession,array: Array[String]):LogisticRegressionModel={

		val vector = getVectorAssemble(array)
		val trainFeature = vector.transform(training)
		val lr =  new LogisticRegression()
			.setLabelCol(CommonName.labelColName)
			.setFeaturesCol("features")
			.setMaxIter(CommonName.maxIter)
		val lrModel = lr.fit(trainFeature)

		lrModel
	}
	def saveModel(lrModel:LogisticRegressionModel): Unit ={
		val format = new SimpleDateFormat(CommonName.dateFormatStr)
		val date = format.format(new Date())
		val modelPath = CommonName.LRmodelSavePath +date
		lrModel.write.overwrite().save(modelPath)
	}



	def getRank(lr:LogisticRegressionModel,
							test:DataFrame,
							array:Array[String]):DataFrame={
		val vector = getVectorAssemble(array)
		val testFeature = vector.transform(test)
		val _recall = lr.transform(testFeature)
		_recall
	}

	def getVectorAssemble(array: Array[String]):VectorAssembler ={
		val vector = new VectorAssembler()
			.setInputCols(array)
			.setOutputCol("features")
		vector
	}
}
object LogisticRegressionRank{
	def apply: LogisticRegressionRank = new LogisticRegressionRank()
}

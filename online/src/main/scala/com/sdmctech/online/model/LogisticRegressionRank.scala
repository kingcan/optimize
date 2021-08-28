package com.sdmctech.online.model

import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date

import org.apache.spark.mllib.classification
import com.sdmctech.online.config.CommonName
import org.apache.spark.ml
import org.apache.spark.ml.Pipeline
import org.apache.spark.mllib.classification.{LogisticRegressionModel, LogisticRegressionWithLBFGS, LogisticRegressionWithSGD}
import org.apache.spark.ml.feature.{Mytransformer, VectorAssembler}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.dmg.pmml.PMML
import org.jpmml.model.MetroJAXBUtil
import org.jpmml.sparkml.PMMLBuilder
import org.apache.spark.sql.functions._
import org.apache.spark.ml._
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.linalg.{DenseVector, SparseVector}
import org.apache.spark.mllib.regression.LabeledPoint

import scala.collection.mutable
class LogisticRegressionRank extends Serializable {

//	def getPipelineModel(training:DataFrame,
//											 spark:SparkSession,array: Array[String]): PMML = {
//		//创建大规模字符串数组版本（内存溢出）
//		val lr = new LogisticRegression()
//			.setLabelCol(CommonName.labelColName)
//			.setFeaturesCol("features")
//			.setMaxIter(CommonName.maxIter)
//
//		val vectorAssem = getVectorAssemble(array)
//		val trainData = vectorAssem.transform(training)
//		val vecToArray = udf((x: linalg.Vector) => x.toArray)
//		val dfArr = trainData.withColumn("featuresArr", vecToArray(col("features")))
//		val tmp = dfArr.select("featuresArr").first().toString()
//		//println(tmp)
//		val featureSize = tmp.substring(13, tmp.length - 2).split(",").size
//		val arr = new Array[String](featureSize)
//		for (i <- 0 until featureSize) {
//			arr(i) = "col" + i
//			//print(greetStrings(i))
//		}
//		val sqlExpr = arr.zipWithIndex.map { case (alias, idx) => col("featuresArr").getItem(idx).as(alias) }
//		val sqlExprWithLabel = sqlExpr.+:(col(CommonName.labelColName))
//		val featuresAndLabelDF = dfArr.select(sqlExprWithLabel: _*)
//		println("featuresAndLabelDF，看是不是被截断了")
//		//featuresAndLabelDF.printSchema()
//		//featuresAndLabelDF.show(truncate = false)
//
//		//		dfArr.printSchema()
//		//		dfArr.show(truncate = false)
//		val pipeline = new Pipeline().setStages(Array(getVectorAssemble(arr), lr))
//		val pipelineModel = pipeline.fit(featuresAndLabelDF)
//		pipelineModel
//		val pmml = new PMMLBuilder(featuresAndLabelDF.schema, pipelineModel).build()
//	pmml
//	}
///////使用spark原生的方法生成LR模型
////		println("trainData的schema，分辨稀疏矩阵和稠密矩阵")
////		trainData.printSchema()
//		// convert features vector-data to string
////		val convertFunction = (x: SparseVector) => {
////			x.toString
////		}
////		val convertUDF = udf(convertFunction)
////		val newdata = trainData.withColumn("featuresString", convertUDF(col("features")))
////		println(newdata.schema)
////		newdata.show(truncate = false)
////		val mytransformer = new Mytransformer().setInputCol(Array("featuresString")).setOutputCol("Finalfeatures")
//		//val vecToArray = udf( (x : linalg.Vector) => x.toArray)
////		val dfArr = trainData.withColumn("featuresArr", vecToArray(col("features")))
////		val sqlExpr = array.zipWithIndex.map{ case (alias, idx) => col("featuresArr").getItem(idx).as(alias) }
////		val sqlExprWithLabel = sqlExpr.+:(col(CommonName.labelColName))
//		//val featuresAndLabelDF = dfArr.select(sqlExpr: _*)
//
////		println("最终输入的dataframe的格式")
////		//featuresAndLabelDF.show(truncate = false)
////		val lr =  new LogisticRegression()
////			.setLabelCol(CommonName.labelColName)
////			.setFeaturesCol("Finalfeatures")
////			.setMaxIter(CommonName.maxIter)
////		val pipeline = new Pipeline().setStages(Array(mytransformer,lr))
//		//val pipeline = new Pipeline().setStages(Array(vectorAssem, lr))
////		println("展示临时的转换后的输入数据矩阵")
////		 val tmpNewdata = mytransformer.transform(newdata)
////		tmpNewdata.printSchema()
////		val pipelineModel=pipeline.fit(newdata)
////		val pmml = new PMMLBuilder(tmpNewdata.schema,pipelineModel).build()
//
//		pmml
//	}
def getPipelineModel(training:DataFrame,
										 spark:SparkSession,array: Array[String]): LogisticRegressionModel ={
	//* @param input RDD of (label, array of features) pairs.
	val vector = getVectorAssemble(array)
	val trainFeature = vector.transform(training)
	val vecToArray = udf( (x : linalg.Vector) => x.toArray)//这里的array是scala.collection.mutable.WrappedArray
	val tmp =trainFeature.withColumn("features",vecToArray(col("features")))
		.withColumn(CommonName.labelColName, col(CommonName.labelColName).cast("double"))
	tmp.printSchema()
	val input =tmp.rdd.map(
		row=>LabeledPoint(row.getAs[Double](CommonName.labelColName)
			,Vectors.dense(row.getAs[mutable.WrappedArray[Double]]("features").toArray)))
	val lrModel =new LogisticRegressionWithLBFGS().run(input)
	lrModel
}
	def savePMMLmodel(lrModel:LogisticRegressionModel): Unit ={
		val modelPath ="lrTest.pmml"
		lrModel.toPMML(modelPath)
	}
//
//	def savePMMLmodel(pmml:PMML,path :String): Unit ={
//		MetroJAXBUtil.marshalPMML(pmml, new FileOutputStream(path))
//	}

	def getModel(training:DataFrame,
							 spark:SparkSession,array: Array[String]):ml.classification.LogisticRegressionModel={

		val vector = getVectorAssemble(array)
		val trainFeature = vector.transform(training)
		val lr =  new LogisticRegression()
			.setLabelCol(CommonName.labelColName)
			.setFeaturesCol("features")
			.setMaxIter(CommonName.maxIter)
		val lrModel = lr.fit(trainFeature)
		lrModel
	}

	def saveModel(lrModel:ml.classification.LogisticRegressionModel): Unit ={
		val format = new SimpleDateFormat(CommonName.dateFormatStr)
		val date = format.format(new Date())
		val modelPath = CommonName.LRmodelSavePath +date
		//lrModel.toPMML(modelPath)
		lrModel.write.overwrite().save(modelPath)
	}



//	def getRank(lr:LogisticRegressionModel,
//							test:DataFrame,
//							array:Array[String]):DataFrame={
//		val vector = getVectorAssemble(array)
//		val testFeature = vector.transform(test)
//		val _recall = lr.transform(testFeature)
//		_recall
//	}

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

package com.sdmctech.offline.model

import org.apache.spark.ml.{Pipeline, PipelineStage}
import org.apache.spark.ml.feature._
import org.apache.spark.sql
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions._

import scala.collection.mutable.ListBuffer

object OneHotCode {
	def oneHotEncoderExample(samples:DataFrame,INTcolumnName:String,toINTcolumnName:String,VecColumnName:String): Unit ={
		val samplesWithIdNumber = samples.withColumn(INTcolumnName, col(toINTcolumnName).cast(sql.types.IntegerType))

		val oneHotEncoder = new OneHotEncoderEstimator()
			.setInputCols(Array(INTcolumnName))
			.setOutputCols(Array(VecColumnName))
			.setDropLast(false)

		val oneHotEncoderSamples = oneHotEncoder.fit(samplesWithIdNumber).transform(samplesWithIdNumber)
		oneHotEncoderSamples.printSchema()
		oneHotEncoderSamples.show(10)
	}

	val array2vec: UserDefinedFunction = udf { (a: Seq[Int], length: Int) => org.apache.spark.ml.linalg.Vectors.sparse(length, a.sortWith(_ < _).toArray, Array.fill[Double](a.length)(1.0)) }

	/**
		* Multi-hot encoding example function
		* @param samples movie samples dataframe
		*/
	def multiHotEncoderExample(samples:DataFrame,explodeColumnName:String,
														 primaryKey:String,separator:String,castType:String): DataFrame ={
		val aliasName =explodeColumnName.substring(0,explodeColumnName.length-3)
		val samplesWithGenre = samples.select(col("*"), explode(split(col(explodeColumnName), separator).cast(castType)).as(aliasName))
		val genreIndexer = new StringIndexer().setInputCol(aliasName).setOutputCol(aliasName+"Index")

		val stringIndexerModel : StringIndexerModel = genreIndexer.fit(samplesWithGenre)

		val genreIndexSamples = stringIndexerModel.transform(samplesWithGenre)
			.withColumn(aliasName+"Index"+"Int", col(aliasName+"Index").cast(sql.types.IntegerType))

		val indexSize = genreIndexSamples.agg(max(col(aliasName+"Index"+"Int"))).head().getAs[Int](0) + 1

		val processedSamples =  genreIndexSamples
			.groupBy(col(primaryKey)).agg(collect_list(aliasName+"Index"+"Int").as(aliasName+"Indexes"))
			.withColumn(aliasName+"indexSize", typedLit(indexSize))

		val finalSample = processedSamples.withColumn( aliasName+"vector", array2vec(col(aliasName + "Indexes"),col(aliasName+"indexSize")))
		finalSample.printSchema()
		//finalSample.show(10)
		finalSample
	}
	def multiHotEncoderExample(samples:DataFrame,explodeColumnName:String,
														 aliasName:String,key:String): DataFrame ={
		val samplesWithGenre = samples.select(col("*"), explode(split(col(explodeColumnName), "\\,").cast("array<string>")).as(aliasName))
		val genreIndexer = new StringIndexer().setInputCol(aliasName).setOutputCol(aliasName+"Index")

		val stringIndexerModel : StringIndexerModel = genreIndexer.fit(samplesWithGenre)

		val genreIndexSamples = stringIndexerModel.transform(samplesWithGenre)
			.withColumn(aliasName+"Index"+"Int", col(aliasName+"Index").cast(sql.types.IntegerType))

		val indexSize = genreIndexSamples.agg(max(col(aliasName+"Index"+"Int"))).head().getAs[Int](0) + 1

		val processedSamples =  genreIndexSamples
			.groupBy(col(key)).agg(collect_list(aliasName+"Index"+"Int").as(aliasName+"Indexes"))
			.withColumn(aliasName+"indexSize", typedLit(indexSize))

		val finalSample = processedSamples.withColumn( aliasName+"vector", array2vec(col(aliasName + "Indexes"),col(aliasName+"indexSize")))
		finalSample.printSchema()
		//finalSample.show(10)
		finalSample
	}

	val double2vec: UserDefinedFunction = udf { (value: Double) => org.apache.spark.ml.linalg.Vectors.dense(value) }

	/**
		* Process rating samples
		* @param samples rating samples
		*/
	def ratingFeatures(samples:DataFrame): Unit ={
		samples.printSchema()
		samples.show(10)

		//calculate average movie rating score and rating count
		val movieFeatures = samples.groupBy(col("movieId"))
			.agg(count(lit(1)).as("ratingCount"),
				avg(col("rating")).as("avgRating"),
				variance(col("rating")).as("ratingVar"))
			.withColumn("avgRatingVec", double2vec(col("avgRating")))

		movieFeatures.show(10)

		//bucketing
		val ratingCountDiscretizer = new QuantileDiscretizer()
			.setInputCol("ratingCount")
			.setOutputCol("ratingCountBucket")
			.setNumBuckets(100)

		//Normalization
		val ratingScaler = new MinMaxScaler()
			.setInputCol("avgRatingVec")
			.setOutputCol("scaleAvgRating")

		val pipelineStage: Array[PipelineStage] = Array(ratingCountDiscretizer, ratingScaler)
		val featurePipeline = new Pipeline().setStages(pipelineStage)

		val movieProcessedFeatures = featurePipeline.fit(movieFeatures).transform(movieFeatures)
		movieProcessedFeatures.show(10)
	}

	def multiHotCodeOperation(inPutData:DataFrame,enCodeColumn:Array[String],whichFeature:String):DataFrame={
		val stagesArray = new ListBuffer[PipelineStage]()
		for (cate <- enCodeColumn){
			val encoder = multiHotEncoderExample(inPutData,cate,"itemid","\\,","array<string>")
			//未完待续
		}
		inPutData
	}
	def oneHotCodeOperation(inPutData:DataFrame,enCodeColumn:Array[String],whichFeature:String): DataFrame = {
		val stagesArray = new ListBuffer[PipelineStage]()
		for (cate <- enCodeColumn) {
			//使用StringIndexer 建立类别索引
			val indexer = new StringIndexer().setInputCol(cate).setOutputCol(s"${cate}Index")
			// 使用OneHotEncoder将分类变量转换为二进制稀疏向量
			val encoder = new OneHotEncoder().setInputCol(indexer.getOutputCol).setOutputCol(s"${cate}classVec")
			stagesArray.append(indexer, encoder)
		}
		val assemblerInputs = enCodeColumn.map(_ + "classVec")
		// 使用VectorAssembler将所有特征转换为一个向量
		val assembler = new VectorAssembler().setInputCols(assemblerInputs).setOutputCol(whichFeature +"features")
		//使用pipeline批处理
		val pipeline = new Pipeline()
		pipeline.setStages(stagesArray.toArray)
		val pipelineModel = pipeline.fit(inPutData)
		val dataset = pipelineModel.transform(inPutData)
		///dataset.show()
		val dataset2 = assembler.transform(dataset).select("*")
		dataset2
	}

}

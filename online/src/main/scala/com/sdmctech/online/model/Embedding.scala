package com.sdmctech.online.model

import java.io.{BufferedWriter, File, FileWriter}

import org.apache.spark.ml.feature.BucketedRandomProjectionLSH
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.mllib.feature.{Word2Vec, Word2VecModel}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
 * @Author: Larkin
 * @Date: 2021/6/30 15:07
 */
class Embedding extends Serializable {
  def trainItem2vec(sparkSession: SparkSession, samples : RDD[Seq[String]], embLength:Int): Word2VecModel = {
    val word2vec = new Word2Vec()
      .setVectorSize(embLength)
      .setWindowSize(5)
      .setNumIterations(10)

    val model = word2vec.fit(samples)


//    val synonyms = model.findSynonyms("158", 20)
//    for ((synonym, cosineSimilarity) <- synonyms) {
//      println(s"$synonym $cosineSimilarity")
//    }

//    val embFolderPath = this.getClass.getResource("/webroot/modeldata/")
//    val file = new File(embFolderPath.getPath + embOutputFilename)
//    val bw = new BufferedWriter(new FileWriter(file))
//    for (movieId <- model.getVectors.keys) {
//      bw.write(movieId + ":" + model.getVectors(movieId).mkString(" ") + "\n")
//    }
//    bw.close()



    //embeddingLSH(sparkSession, model.getVectors)
    model
  }

  def embeddingLSH(spark:SparkSession, movieEmbMap:Map[String, Array[Float]]): Unit ={

    val movieEmbSeq = movieEmbMap.toSeq.map(item => (item._1, Vectors.dense(item._2.map(f => f.toDouble))))
    val movieEmbDF = spark.createDataFrame(movieEmbSeq).toDF("movieId", "emb")

    //LSH bucket model
    val bucketProjectionLSH = new BucketedRandomProjectionLSH()
      .setBucketLength(0.1)
      .setNumHashTables(3)
      .setInputCol("emb")
      .setOutputCol("bucketId")

    val bucketModel = bucketProjectionLSH.fit(movieEmbDF)
    val embBucketResult = bucketModel.transform(movieEmbDF)
    println("movieId, emb, bucketId schema:")
    embBucketResult.printSchema()
    println("movieId, emb, bucketId data result:")
    embBucketResult.show(10, truncate = false)

    println("Approximately searching for 5 nearest neighbors of the sample embedding:")
    val sampleEmb = Vectors.dense(0.795,0.583,1.120,0.850,0.174,-0.839,-0.0633,0.249,0.673,-0.237)
    bucketModel.approxNearestNeighbors(movieEmbDF, sampleEmb, 5).show(truncate = false)
  }

}
object Embedding{
  def apply: Embedding = new Embedding()
}

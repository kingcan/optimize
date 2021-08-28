package com.sdmctech.online.dao

import com.sdmctech.online.common.TDao
import org.apache.spark.mllib.feature.Word2VecModel
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions._
import redis.clients.jedis.Jedis
import redis.clients.jedis.params.SetParams
/**
 * @Author: Larkin
 * @Date: 2021/6/30 15:29
 */
class EmbeddingDao extends TDao{
  val redisKeyPrefix ="i2vEmb"
   def getRecordDataByInterval(sparksql:String):DataFrame ={
     println("输入的hiveSQL："+sparksql)
     createDataFrameBySparkSQL(sparksql)
   }


  def processItemSequence(sparkSession: SparkSession, ratingSamples: DataFrame): RDD[Seq[String]] ={

    //sort by timestamp udf
    val sortUdf: UserDefinedFunction = udf((rows: Seq[Row]) => {
      rows.map { case Row(id: Int, create_time: String) => (id, create_time) }
        .sortBy { case (_, create_time) => create_time }
        .map { case (id, _) => id }
    })

    ratingSamples.printSchema()

    //process rating data then generate rating movie sequence data
    val userSeq = ratingSamples
      .groupBy("member_id")
      .agg(sortUdf(collect_list(struct("id", "create_time"))) as "itemIds")
      .withColumn("itemIdStr", array_join(col("itemIds"), " "))

    userSeq.select("member_id", "itemIdStr").show(10, truncate = false)
    userSeq.select("itemIdStr").rdd.map(r => r.getAs[String]("itemIdStr").split(" ").toSeq)
  }

  def savaEmbeddings(redisEndpoint:String,model: Word2VecModel): Unit ={
      val redisClient = new Jedis(redisEndpoint, 6379)
       redisClient.auth("123456")
      val params = SetParams.setParams()
      //set ttl to 24hs
      params.ex(60 * 60 * 1)
      for (movieId <- model.getVectors.keys) {
        redisClient.set(redisKeyPrefix + ":" + movieId, model.getVectors(movieId).mkString(" "), params)
      }
      redisClient.close()
  }
}

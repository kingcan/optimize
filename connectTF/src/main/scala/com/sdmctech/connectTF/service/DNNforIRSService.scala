package com.sdmctech.connectTF.service
import com.sdmctech.connectTF.common.TService
import com.sdmctech.connectTF.dao.DNNforIRSDao
import com.sdmctech.connectTF.util.EnvUtil
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import scala.collection.mutable.WrappedArray

/**
 * @Author: Larkin
 * @Date: 2021/8/30 15:05
 */
class DNNforIRSService extends TService{
  val spark =EnvUtil.take()
  override def dataAnalysis(): Any = {
    import spark.implicits._
   val tmp =DNNforIRSDao.recentRecords.toDF("user_id","item_id","cv","duration")
       .withColumn("user_id",col("user_id").cast("string"))
   tmp.createOrReplaceTempView("tmp_user_item")
    //平均值在一千左右
    //EnvUtil.take().sql("select avg(duration) from tmp_user_item").show(truncate = false)
    val ratingsDF3=tmp.withColumn("item_id",col("item_id").cast("int"))
      .groupBy("user_id").agg(collect_list("item_id"))
      .toDF("user_id","item_id_list")
      .filter(size($"item_id_list")>2)//过滤那些记录特别短的数据，不然会越界
    val item_id_max = tmp.withColumn("item_id",col("item_id").cast("int"))
      .agg(max("item_id")).collect().apply(0).getAs[Int](0)
    println(item_id_max)
    val item_id_all2=DNNforIRSDao.allMovies.collect().map(_(0).toString.toInt)
    //val item_id_all2 = (1 to item_id_max).toArray
    val item_id_all: Broadcast[Array[Int]] = spark.sparkContext.broadcast(item_id_all2)
    val neg_rating = ratingsDF3.map(row =>{

      val tmp =row.getAs[WrappedArray[Int]]("item_id_list").toArray
      val tmp2 = item_id_all.value.diff(tmp)//取差集
      var arrs =Array[Int]()
      for(i<- 1 to tmp.length+10){
        arrs=arrs.+:(tmp2(util.Random.nextInt(tmp2.length)))
      }
      (row.getAs[String]("user_id"),row.getAs[WrappedArray[Int]](
        "item_id_list"),arrs)
    }).toDF("user_id","item_id_list","neg_list")
    val train_samples =DNNforIRSDao.create_train_samples(neg_rating,"item_id_list",
      "neg_list","user_id",spark)
    println("打印初级处理后的正负样本")
    spark.udf.register("padding",DNNforIRSDao.padding _  )

    val val_samples =DNNforIRSDao.create_val_samples(neg_rating,"item_id_list",
      "neg_list","user_id",spark)
    println("打印正负验证样本")
    val pos_test_samples =DNNforIRSDao.create_pos_test_samples(neg_rating,
      "item_id_list","user_id",spark)
    val neg_test_samples =DNNforIRSDao.create_neg_test_samples(neg_rating,
      "item_id_list","neg_list","user_id",spark)
    println("打印test负样本测试集")
    ///最后输入的肯定是padding后的数据
    val train_samples_with_padding =train_samples.withColumn("padding",callUDF(
      "padding",col("input")))
      .drop("input")
    println("打印padding后的正负训练样本")
    //train_samples_with_padding.show(truncate = false)
    val val_samples_with_padding =val_samples.withColumn("padding",
      callUDF("padding",col("input")))
      .drop("input")
    println("打印padding后的正负验证样本")
    //val_samples_with_padding.show(truncate = false)
    val pos_test_samples_with_padding =pos_test_samples.withColumn("padding",
      callUDF("padding",col("input")))
      .drop("input")
    println("打印padding后的正测试样本")
    // pos_test_samples_with_padding.show(truncate = false)
    val neg_test_samples_with_padding =neg_test_samples.withColumn("padding",
      callUDF("padding",col("input")))
      .drop("input")
    println("打印padding后的负测试样本")
    //neg_test_samples_with_padding.show(truncate = false)
    //将dataframe全部输出至TFRecord文件
    val train_output_dir = "hdfs://nameservice1/tmp/tfrecord-dnn/train"
    val ptest_output_dir = "hdfs://nameservice1/tmp/tfrecord-dnn/ptest"
    val ntest_output_dir = "hdfs://nameservice1/tmp/tfrecord-dnn/ntest"
    val test_output_dir = "hdfs://nameservice1/tmp/tfrecord-dnn/test"
    val val_output_dir = "hdfs://nameservice1/tmp/tfrecord-dnn/val"
    train_samples_with_padding.printSchema()
    val_samples_with_padding.printSchema()
    pos_test_samples_with_padding.printSchema()
    neg_test_samples_with_padding.printSchema()
    train_samples_with_padding.show(truncate = false)
    train_samples_with_padding.write.mode(SaveMode.Overwrite).format("tfrecord")
      .option("recordType", "Example").save(train_output_dir)
    val_samples_with_padding.write.mode(SaveMode.Overwrite).format("tfrecord")
      .option("recordType","Example").save(val_output_dir)
    pos_test_samples_with_padding.write.mode(SaveMode.Overwrite).format("tfrecord")
      .option("recordType", "Example").save(ptest_output_dir)
    neg_test_samples_with_padding.write.mode(SaveMode.Overwrite).format("tfrecord")
      .option("recordType", "Example").save(ntest_output_dir)
    val test_result = pos_test_samples_with_padding.union(neg_test_samples_with_padding)
    test_result.write.mode(SaveMode.Overwrite).format("tfrecord")
      .option("recordType", "Example").save(test_output_dir)

}
}

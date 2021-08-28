package com.sdmctech.connectTF.test

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.IntegerType

import scala.collection.mutable
import scala.collection.mutable.WrappedArray
import scala.collection.mutable.ArrayBuffer
import scala.util.Random
/**
 * @Author: Larkin
 * @Date: 2021/8/12 19:50
 */
object readDat {
  def main(args: Array[String]): Unit = {
    // launch spark-shell with the following command:
    val spark = SparkSession.builder()
      .master("local[*]")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._
    val rdd = spark.sparkContext.textFile("connectTF/data/ml-1m/movies.dat")
    rdd.map(line=>line.split("::")).map(arr=>(arr(0),arr(1),arr(2)))
      .toDF("itemid","name","genres")
      .show(truncate = false)
    //rdd.toDF().show(truncate = false)
    val ratings = spark.sparkContext.textFile("connectTF/data/ml-1m/ratings.dat")
    val ratingsDF =ratings.map(line=>line.split("::")).map(arr=>(arr(0),arr(1),arr(2),arr(3)))
      .toDF("user_id", "item_id", "label", "Timestamp")
      //.show(truncate = false)
    //ratings.toDF().show(truncate = false)
    //注册自定义函数

    //定义自定义函数
    def judge_size(col: String): Int = {

      if (col.toInt>2) 1 else 0
    }
    spark.udf.register("judge_size", judge_size _)
    val ratingsDF2 =ratingsDF.withColumn("label",callUDF("judge_size",col("label")))
      .orderBy("user_id","Timestamp")
      //.show(truncate = false)
    val item_id_max = ratingsDF2.withColumn("item_id",col("item_id").cast("int"))
      .agg(max("item_id")).collect().apply(0).getAs[Int](0)
    println(item_id_max)
    //val item_id_all = (1 to item_id_max).toArray
    //val item_id_all = spark.sparkContext.makeRDD(1 to item_id_max)
    //ratingsDF2.createOrReplaceTempView("df")
    //创建userid,itemid 分组聚合
    val ratingsDF3=ratingsDF2.withColumn("item_id",col("item_id").cast("int"))
      .groupBy("user_id").agg(collect_list("item_id"))
      .toDF("user_id","item_id_list")
      //.printSchema()
    //通过正样本产生负样本
    //定义自定义函数
//    def generate_negSample(col: WrappedArray[Int]): WrappedArray[Int] = {
//      val tmp =item_id_all.diff(col)
//      val random = new Random
//      // 定义一个变长数组
//      val nums =Array[Int]()
//     // nums+= tmp(random.nextInt(tmp.length-1))
//      nums
//    }
  //  spark.udf.register("genarate_neg",generate_negSample _)
    val item_id_all2 = (1 to item_id_max).toArray
    val item_id_all: Broadcast[Array[Int]] = spark.sparkContext.broadcast(item_id_all2)

    ratingsDF3.printSchema()
//   val neg_rating = ratingsDF3.withColumn("neg_list",callUDF("genarate_neg",col("item_id_list")))
//    neg_rating.show(truncate = false)

    val neg_rating = ratingsDF3.map(row =>{

      val tmp =row.getAs[WrappedArray[Int]]("item_id_list").toArray
      val tmp2 = item_id_all.value.diff(tmp)//取差集
      var arrs =Array[Int]()
      for(i<- 1 to tmp.length+100){
        arrs=arrs.+:(tmp2(util.Random.nextInt(tmp2.length)))
      }

      //val tmp3 =util.Random.shuffle(tmp2.toList).take(tmp.length+100).toArray

      (row.getAs[String]("user_id"),row.getAs[WrappedArray[Int]]("item_id_list"),arrs)
    }).toDF("user_id","item_id_list","neg_list")
    println("打印整理好的数据集")
    neg_rating.printSchema()
   // neg_rating.show(truncate = false)
     // .show(truncate = false)
    //将每隔用户的记录生成梯形样本(输入一个序列，输出其子序列)
//    val samples = neg_rating.map(row=>{
//      val tmp =row.getAs[WrappedArray[Int]]("item_id_list").toArray
//      var arrs =Array[String]()
//      for(i <- 2 to tmp.length)
//       arrs=arrs.:+(tmp.take(i).mkString("::"))
//      (row.getAs[String]("user_id"),arrs)
//    }).toDF("user_id","union")
//      .printSchema()
    val train_samples =create_train_samples(neg_rating,"item_id_list","neg_list","user_id",spark)
    //val neg_train_samples = create_train_samples(neg_rating,"neg_list","user_id",spark,0)
    //neg_train_samples.printSchema()
    //neg_train_samples.show(truncate = false)
    println("打印初级处理后的正负样本")
   // train_samples.show(truncate = false)
    spark.udf.register("padding",padding _  )

    val val_samples =create_val_samples(neg_rating,"item_id_list","neg_list","user_id",spark)
   // val neg_val_samples =create_val_samples(neg_rating,"neg_list","user_id",spark,0)
    println("打印正负验证样本")
    //val_samples.show(truncate = false)
    val pos_test_samples =create_pos_test_samples(neg_rating,"item_id_list","user_id",spark)
   val neg_test_samples =create_neg_test_samples(neg_rating,"item_id_list","neg_list","user_id",spark)
   println("打印test负样本测试集")
    //neg_test_samples.show(truncate = false)

    ///最后输入的肯定是padding后的数据

    val train_samples_with_padding =train_samples.withColumn("padding",callUDF("padding",col("input")))
        .drop("input")
    println("打印padding后的正负训练样本")
    //train_samples_with_padding.show(truncate = false)
    val val_samples_with_padding =val_samples.withColumn("padding",callUDF("padding",col("input")))
      .drop("input")
    println("打印padding后的正负验证样本")
    //val_samples_with_padding.show(truncate = false)
    val pos_test_samples_with_padding =pos_test_samples.withColumn("padding",callUDF("padding",col("input")))
      .drop("input")
    println("打印padding后的正测试样本")
   // pos_test_samples_with_padding.show(truncate = false)
    val neg_test_samples_with_padding =neg_test_samples.withColumn("padding",callUDF("padding",col("input")))
      .drop("input")
    println("打印padding后的负测试样本")
    //neg_test_samples_with_padding.show(truncate = false)
    //将dataframe全部输出至TFRecord文件
    val train_output_dir = "/tmp/tfrecord-dnn/train"
    val ptest_output_dir = "/tmp/tfrecord-dnn/ptest"
    val ntest_output_dir = "/tmp/tfrecord-dnn/ntest"
    val test_output_dir = "/tmp/tfrecord-dnn/test"
    val val_output_dir = "/tmp/tfrecord-dnn/val"
    train_samples_with_padding.printSchema()
    val_samples_with_padding.printSchema()
    pos_test_samples_with_padding.printSchema()
    neg_test_samples_with_padding.printSchema()
    train_samples_with_padding.show(truncate = false)
    train_samples_with_padding.write.mode(SaveMode.Overwrite).format("tfrecord").option("recordType", "Example").save(train_output_dir)
    val_samples_with_padding.write.mode(SaveMode.Overwrite).format("tfrecord").option("recordType","Example").save(val_output_dir)
    pos_test_samples_with_padding.write.mode(SaveMode.Overwrite).format("tfrecord").option("recordType", "Example").save(ptest_output_dir)
    neg_test_samples_with_padding.write.mode(SaveMode.Overwrite).format("tfrecord").option("recordType", "Example").save(ntest_output_dir)
    val test_result = pos_test_samples_with_padding.union(neg_test_samples_with_padding)
    test_result.write.mode(SaveMode.Overwrite).format("tfrecord").option("recordType", "Example").save(test_output_dir)

  }
  //padding方法
  def padding(arr:scala.collection.mutable.WrappedArray[Int]): Array[Int] ={
    val length = 100
    var tmp =arr.toArray
    while (tmp.length < length)
       tmp=tmp.+:(0)
    if(tmp.length>length)
      return tmp.drop(tmp.length-length)
    else return tmp
  }


  //产生正样本子序列
  //将每隔用户的记录生成梯形样本(输入一个序列，输出其子序列)
  def create_train_samples(data:DataFrame,column_pos:String,column_neg:String,
                     key:String,spark:SparkSession):DataFrame={
    import spark.implicits._
    val sub_line = data.map(row=>{
      val tmp =row.getAs[WrappedArray[Int]](column_pos).toArray
      var arrs =Array[String]()
      for(i <- 2 to tmp.length-2)
        arrs=arrs.:+(tmp.take(i).mkString("::"))
      (row.getAs[String](key),arrs,row.getAs[WrappedArray[Int]](column_neg))
    }).toDF(key,"union",column_neg)
    //将字符串数组展开成字符串
    val tmp2 =sub_line.withColumn("sub_line",explode(col("union")))//这里没有炸开
      .select(key,"sub_line",column_neg)
    val result =tmp2.map(row=>{
      val sub_line_arr=row.getAs[String]("sub_line").split("::").map(_.toInt)
      val input = sub_line_arr.take(sub_line_arr.length-1)
      val target=sub_line_arr(sub_line_arr.length-1)
      val neg = row.getAs[WrappedArray[Int]](column_neg).toArray
      val neg_target =neg(sub_line_arr.length-1)
      (row.getAs[String](key),input,target,1,neg_target,0)
    }).toDF(key,"input","target","label","neg_target","neg_label")
    result
  }
  //产生正样本验证集
  def create_val_samples(data:DataFrame,column_pos:String,column_neg:String,
                         key:String,spark:SparkSession):DataFrame={
    import spark.implicits._
    val result = data.map(row=>{
      val tmp =row.getAs[WrappedArray[Int]](column_pos).toArray
      val tmp2 =row.getAs[WrappedArray[Int]](column_neg).toArray
      (row.getAs[String](key),tmp.take(tmp.length-2),tmp(tmp.length-2),1,tmp2(tmp.length-2),0)
    }).toDF(key,"input","target","label","neg_target","neg_label")
    result
  }
  //产生正样本测试集
  def create_pos_test_samples(data:DataFrame,column:String,
                         key:String,spark:SparkSession):DataFrame={
    import spark.implicits._
    val result = data.map(row=>{
      val tmp =row.getAs[WrappedArray[Int]](column).toArray
      (row.getAs[String](key),tmp.take(tmp.length-1),tmp(tmp.length-1),1)
    }).toDF(key,"input","target","label")
    result
  }
  //产生负样本验证集
  def create_neg_test_samples(data:DataFrame,column_pos:String,column_neg:String,
                              key:String,spark:SparkSession):DataFrame={
    import spark.implicits._
    val sub_line = data.map(row=>{
      val tmp =row.getAs[WrappedArray[Int]](column_pos).toArray
      val input =tmp.take(tmp.length-1)
      val tmp2 =row.getAs[WrappedArray[Int]](column_neg).toArray
      val neg_tmp =tmp2.drop(tmp.length-1)
      (row.getAs[String](key),input,neg_tmp,0)
    }).toDF(key,"input","neg_tmp","label")
    //将字符串数组展开成字符串
    val result =sub_line.withColumn("target",explode(col("neg_tmp")))
      .select(key,"input","target","label")
    result
  }

}

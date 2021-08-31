package com.sdmctech.connectTF.dao

import com.sdmctech.connectTF.common.TDao
import com.sdmctech.connectTF.util.EnvUtil
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, explode}

import scala.collection.mutable.WrappedArray

/**
 * @Author: Larkin
 * @Date: 2021/8/30 15:11
 */
object DNNforIRSDao extends TDao{
  val spark =EnvUtil.take()

  val allMovies: DataFrame =spark.sql(
    """
      |SELECT
      |id
      |FROM
      |bo_video_content100019570647
      |WHERE
      |parent_asset_id is NULL
      |""".stripMargin
  )
  //写sql
  val recentRecords: DataFrame =spark.sql(
    s"""
      SELECT
      |    c.member_id,
      |    c.id,
      |    count(c.create_time),
      |    sum(c.plytotal)
      | FROM
      |  (SELECT
      |   a.member_id as member_id,
      |   b.id as id,
      |   b.asset_id as asset_id,
      |   a.create_time as create_time,
      |   a.play_duration as plytotal
      |  FROM
      |   ods_dms_collection_vod_play_artificial_union as a
      |   join
      |   bo_video_content100019570647 as b
      |   on
      |   a.parent_asset_id=b.asset_id
      |   WHERE
      |    a.date_ymd > date_format(date_sub(current_date(), 10),'yy-MM-dd')
      |   and
      |    a.date_ymd < date_format(current_date(),'yy-MM-dd')
      |   ) c
      |GROUP BY c.member_id,c.id --筛选出连续登陆天数大于等于3天的记录
      |""".stripMargin)
  //padding方法
  def padding(arr:scala.collection.mutable.WrappedArray[Int]): Array[Int] ={
    val length = 50
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
      (row.getAs[String](key),tmp.take(tmp.length-2),tmp(tmp.length-2),1,tmp2(tmp.length-2),0)//注意数组越界
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

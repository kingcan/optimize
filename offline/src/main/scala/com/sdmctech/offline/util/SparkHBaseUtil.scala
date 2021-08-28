package com.sdmctech.offline.util

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.spark.HBaseContext
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.datasources.hbase.HBaseTableCatalog

/**
 * @Author: Larkin
 * @Date: 2021/7/15 17:06
 */
class SparkHBaseUtil(spark:SparkSession) extends Serializable{

  val hbaseConf = HBaseConfiguration.create()
  new HBaseContext(spark.sparkContext,hbaseConf)


  /**
   * @Param catalog:hbase table describe（you used）按需输入
   *       regions:将数据存储至hbase的regions个分区上
   *       data:(dataFrame格式)
   *
   * */
  def insertDataFrameIntoHBase(dataFrame: DataFrame,catalog:String,regions:Int): Unit ={
    dataFrame.write.options(
      Map(HBaseTableCatalog.tableCatalog -> catalog,
        HBaseTableCatalog.newTable -> regions.toString
      ))
      .format("org.apache.hadoop.hbase.spark")
      .save()
  }

  def readDataFromHBase(catalog:String):DataFrame={
    val hbaseDF = spark.read
      .options(Map(HBaseTableCatalog.tableCatalog -> catalog))
      .format("org.apache.hadoop.hbase.spark")
      .load()
    hbaseDF
  }
}
object SparkHBaseUtil {
  def apply(spark: SparkSession): SparkHBaseUtil = new SparkHBaseUtil(spark)
}

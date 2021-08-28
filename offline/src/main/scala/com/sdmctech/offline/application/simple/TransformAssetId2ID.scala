package com.sdmctech.offline.application.simple

import com.sdmctech.offline.common.TApplication
import com.sdmctech.offline.config.CommonName
import com.sdmctech.offline.multitenant.TenantAdapter
import com.sdmctech.offline.util.{EnvUtil, SparkHBaseUtil}
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.spark.HBaseContext
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.datasources.hbase.HBaseTableCatalog

/**
 * @Author: Larkin
 * @Date: 2021/7/13 15:35
 */
object TransformAssetId2ID extends TApplication{
  def main(args: Array[String]): Unit ={
    TenantAdapter.put(args(0))
    start("TransformAssetId2ID"+args(0)){
      val spark =EnvUtil.take()
      //spark.conf.set("spark.hadoop.validateOutputSpecs", false)
      //val phoenixDataFrame = PhoenixDataFrame(spark)
      val sparkHBaseUtil =SparkHBaseUtil(spark)
      //val allItemFeature = phoenixDataFrame.getDataFramefromHBase(CommonName.itemFeatureHBase,CommonName.parentAssetId2itemId)
      //allItemFeature.printSchema()
      val tableName ="parentAssetId2itemId"
      val columnFamilyName ="cf"
      val cellName = "ITEMID"
      val keyId="PARENT_ASSET_ID"
      val collectColumn ="ITEMID"
      //modelUtil.saveRecall(tableName,columnFamilyName,allItemFeature, cellName,keyId,collectColumn)
      def catalog =
        s"""{
           |"table":{"namespace":"default", "name":"parentAssetId2itemId2"},
           |"rowkey":"PARENT_ASSET_ID",
           |"columns":{
           |"PARENT_ASSET_ID":{"cf":"rowkey", "col":"PARENT_ASSET_ID", "type":"string"},
           |"ITEMID":{"cf":"cf", "col":"ITEMID", "type":"string"}
           |}
           |}""".stripMargin
//      val hbaseConf = HBaseConfiguration.create()
//      new HBaseContext(spark.sparkContext,hbaseConf)
//      allItemFeature.write.options(
//        Map(HBaseTableCatalog.tableCatalog -> catalog, HBaseTableCatalog.newTable -> "3"
//          ))
//        .format("org.apache.hadoop.hbase.spark")
//        .save()
def catalogTest =
  s"""{
     |"table":{"namespace":"default", "name":"employee"},
     |"rowkey":"key",
     |"columns":{
     |"key":{"cf":"rowkey", "col":"key", "type":"string"},
     |"fName":{"cf":"person", "col":"firstName", "type":"string"},
     |"lName":{"cf":"person", "col":"lastName", "type":"string"},
     |"mName":{"cf":"person", "col":"middleName", "type":"string"},
     |"zipCode":{"cf":"address", "col":"zipCode", "type":"string"}
     |}
     |}""".stripMargin
      println(catalogTest)
sparkHBaseUtil.readDataFromHBase(catalogTest).show()
    }
    TenantAdapter.clear()
  }
}

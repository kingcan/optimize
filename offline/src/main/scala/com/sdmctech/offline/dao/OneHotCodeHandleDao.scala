package com.sdmctech.offline.dao

import com.sdmctech.offline.common.TDao
import com.sdmctech.offline.config.CommonName
import com.sdmctech.offline.mapper.OneHotCodeHandleMapper
import com.sdmctech.offline.util.{EnvUtil, SparkHBaseUtil}
import org.apache.spark.sql.DataFrame

object OneHotCodeHandleDao extends TDao{
  def readData(tableName:String): DataFrame ={
    val catalog: String = tableName match {
      case CommonName.userFeatureHBase =>OneHotCodeHandleMapper.user_catalog
      case CommonName.itemFeatureHBase =>OneHotCodeHandleMapper.item_catalog
    }
    sparkHBaseUtil.readDataFromHBase(catalog)
  }
  def saveData(data:DataFrame,tableName:String,columnName:String): Unit ={
    val catalog: String = tableName match{
      case CommonName.userFeatureHBase=>OneHotCodeHandleMapper.user_addColumn_catalog(columnName)
      case CommonName.itemFeatureHBase=>OneHotCodeHandleMapper.item_addColumn_catalog(columnName)
    }
    sparkHBaseUtil.insertDataFrameIntoHBase(data,catalog,CommonName.regionsOfHBase)
  }
}

package com.sdmctech.offline.dao

import com.sdmctech.offline.common.TDao
import com.sdmctech.offline.config.CommonName
import com.sdmctech.offline.mapper.DataFromHiveToHBaseMapper
import com.sdmctech.offline.util.{EnvUtil, SparkHBaseUtil}
import org.apache.spark.sql.DataFrame

object DataFromHiveToHBaseDao extends TDao{
   def saveData(data:DataFrame,tableName:String): Unit ={
     val catalog: String = tableName match {
       case CommonName.userFeatureHBase => DataFromHiveToHBaseMapper.user_catalog
       case CommonName.itemFeatureHBase if data.columns.length==2 =>DataFromHiveToHBaseMapper.item_parentassetid_catalog
       case CommonName.itemFeatureHBase if data.columns.length!=2 =>DataFromHiveToHBaseMapper.item_catalog
     }
     sparkHBaseUtil.insertDataFrameIntoHBase(data,catalog,CommonName.regionsOfHBase)
   }
}

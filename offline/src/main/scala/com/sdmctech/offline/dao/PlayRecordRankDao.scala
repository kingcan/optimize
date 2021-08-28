package com.sdmctech.offline.dao

import com.sdmctech.offline.common.TDao
import com.sdmctech.offline.config.CommonName
import org.apache.spark.sql.DataFrame

object PlayRecordRankDao extends TDao{
  def saveData(data:DataFrame,catalog:String): Unit ={
    sparkHBaseUtil.insertDataFrameIntoHBase(data,catalog,CommonName.regionsOfHBase)
  }
}

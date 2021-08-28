package com.sdmctech.offline.dao

import com.sdmctech.offline.common.TDao
import com.sdmctech.offline.config.CommonName
import com.sdmctech.offline.mapper.MovieSimilarityMapper
import org.apache.spark.sql.DataFrame

/**
 * @Author: Larkin
 * @Date: 2021/7/20 17:32
 */
object MovieSimilarityDao extends TDao{
  def getSimilarityData: DataFrame ={
    sparkHBaseUtil.readDataFromHBase(MovieSimilarityMapper.item_catalog)
  }
  def saveSimilarityData(catalog:String,data:DataFrame): Unit ={
    sparkHBaseUtil.insertDataFrameIntoHBase(data,catalog,CommonName.regionsOfHBase)
  }


}

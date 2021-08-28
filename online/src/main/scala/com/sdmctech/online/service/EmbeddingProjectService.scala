package com.sdmctech.online.service

import java.text.SimpleDateFormat
import java.util.{Calendar, Properties}

import com.sdmctech.common.util.PropertiesUtil
import com.sdmctech.online.common.TService
import com.sdmctech.online.config.CommonName
import com.sdmctech.online.dao.EmbeddingDao
import com.sdmctech.online.model.Embedding
import com.sdmctech.online.multitenant.{MultiTenantDynamicProperties, TenantAdapter}
import com.sdmctech.online.util.EnvUtil
/**
 * @Author: Larkin
 * @Date: 2021/6/30 15:06
 */
class EmbeddingProjectService extends TService{
  private val embeddingDao = new EmbeddingDao()

  override def dataAnalysis(): Any = {
    val sparkSession = EnvUtil.take()
    val calendar1 = Calendar.getInstance
    val calendar2 = Calendar.getInstance
    val commonNameProperties: Properties = MultiTenantDynamicProperties.changePropertiesByArgs(TenantAdapter.take())
    val ratingProperties: Properties = MultiTenantDynamicProperties.changeRatingPropertiesByArgs(
      TenantAdapter.take(),commonNameProperties)
    val sdf1 = new SimpleDateFormat(CommonName.dateFormatStr)
    calendar1.add(Calendar.DATE, -35)
    val N_days_ago = sdf1.format(calendar1.getTime)
    calendar2.add(Calendar.DATE, -35-CommonName.intervalDays)
    val N_days_ago2 = sdf1.format(calendar2.getTime)
    val trainingDate = s" where a.date_ymd='$N_days_ago'"
    val trainingDateNdays =	s" where a.date_ymd > '$N_days_ago2' and a.date_ymd < '$N_days_ago'"
    val trainingData =embeddingDao.getRecordDataByInterval(ratingProperties.getProperty("crudeRecords").
      replaceAll("durationOptions",trainingDateNdays))
    //trainingData.show(truncate = false)
    val itemSequence =embeddingDao.processItemSequence(sparkSession,trainingData)
    val embLength = 10
    val itemEmbeddings =new Embedding().trainItem2vec(sparkSession,itemSequence,embLength)
    embeddingDao.savaEmbeddings("10.10.63.213",itemEmbeddings)
  }
}

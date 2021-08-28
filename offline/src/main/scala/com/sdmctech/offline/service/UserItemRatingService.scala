package com.sdmctech.offline.service

import java.text.SimpleDateFormat
import java.util.{Calendar, Properties}

import com.sdmctech.offline.common.TService
import com.sdmctech.offline.config.CommonName
import com.sdmctech.offline.dao.UserItemRatingDao
import com.sdmctech.offline.multitenant.{MultiTenantDynamicProperties, TenantAdapter}
import com.sdmctech.offline.util.PropertiesUtil

class UserItemRatingService extends TService {
	private val userItemRatingDao =new UserItemRatingDao()


	override def dataAnalysis(): Unit = {
		val calendar1 = Calendar.getInstance
		val calendar2 = Calendar.getInstance
		val propertiesUtil = new PropertiesUtil
		val commonNameProperties: Properties = MultiTenantDynamicProperties.changePropertiesByArgs(TenantAdapter.take())
		val ratingProperties: Properties = MultiTenantDynamicProperties.changeRatingPropertiesByArgs(
			TenantAdapter.take(),commonNameProperties)
		val sdf1 = new SimpleDateFormat(CommonName.dateFormatStr)
		calendar1.add(Calendar.DATE, -2)
		val N_days_ago = sdf1.format(calendar1.getTime)
		calendar2.add(Calendar.DATE, -2-CommonName.intervalDays)
		val N_days_ago2 = sdf1.format(calendar2.getTime)
		val trainingDate = s" where a.date_ymd='$N_days_ago'"
		val trainingDateNdays =	s" where a.date_ymd > '$N_days_ago2' and a.date_ymd < '$N_days_ago'"

		userItemRatingDao.basicHandle(ratingProperties.getProperty("countCVUV").
			replaceAll("durationOptions",trainingDate),
			commonNameProperties.getProperty("CommonName.crudeRating"))
		userItemRatingDao.basicHandle(ratingProperties.getProperty("crudeRating"),
			commonNameProperties.getProperty("CommonName.userRating"))
		userItemRatingDao.basicHandle(ratingProperties.getProperty("userParentAssetid").
			replaceAll("durationOptions",trainingDate),
			commonNameProperties.getProperty("CommonName.userParentAssetid"))
		userItemRatingDao.basicHandle(ratingProperties.getProperty("calculateRating").
			replaceAll("CommonName.userRating",commonNameProperties.getProperty("CommonName.userRating")),
			commonNameProperties.getProperty("CommonName.useritemrating"))

    ///上面是一天的打分情况，下面是N天的打分情况表

		userItemRatingDao.basicHandle(ratingProperties.getProperty("countCVUV").
			replaceAll("durationOptions",trainingDateNdays),
			commonNameProperties.getProperty("CommonName.crudeRatingN"))
		userItemRatingDao.basicHandle(ratingProperties.getProperty("crudeRating"),
			commonNameProperties.getProperty("CommonName.userRatingN"))
		userItemRatingDao.basicHandle(ratingProperties.getProperty("userParentAssetid").
			replaceAll("durationOptions",trainingDateNdays),
			commonNameProperties.getProperty("CommonName.userParentAssetidN"))
		userItemRatingDao.basicHandle(ratingProperties.getProperty("calculateRating").
			replaceAll("CommonName.userRating",commonNameProperties.getProperty("CommonName.userRatingN")),
			commonNameProperties.getProperty("CommonName.useritemratingN"))

	}
}

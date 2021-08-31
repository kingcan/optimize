package com.sdmctech.connectTF.multitenant

import java.util.Properties

import com.sdmctech.connectTF.util.PropertiesUtil

import scala.collection.JavaConversions._

object MultiTenantDynamicProperties {
      val propertiesUtil =new PropertiesUtil

	def changePropertiesByArgs(arg:String):Properties= {
     //将properties的value全部添加租户码后缀
		 val commonNameProperties: Properties = propertiesUtil.initProperties("/common/commonName.properties")
		 val set= commonNameProperties.stringPropertyNames()

		for(str<-set){
      commonNameProperties.setProperty(str,commonNameProperties.getProperty(str)+arg)
		}
		commonNameProperties
	}

	def changeRatingPropertiesByArgs(arg:String,commonName:Properties):Properties={
		val ratingProperties: Properties = propertiesUtil.initProperties("/hiveTables/rating.properties")
		ratingProperties.setProperty("countCVUV",ratingProperties.getProperty("countCVUV")
			.replaceAll("CommonName.DmsVodPlayRecord",commonName.getProperty("CommonName.DmsVodPlayRecord"))
		.replaceAll("CommonName.BoVideoContent",commonName.getProperty("CommonName.BoVideoContent")))

		ratingProperties.setProperty("crudeRecords",ratingProperties.getProperty("crudeRecords")
			.replaceAll("CommonName.DmsVodPlayRecord",commonName.getProperty("CommonName.DmsVodPlayRecord"))
			.replaceAll("CommonName.BoVideoContent",commonName.getProperty("CommonName.BoVideoContent")))

		ratingProperties.setProperty("crudeRating",ratingProperties.getProperty("crudeRating")
			.replaceAll("CommonName.crudeRating",commonName.getProperty("CommonName.crudeRating")))

		ratingProperties.setProperty("userParentAssetid",ratingProperties.getProperty("userParentAssetid")
			.replaceAll("CommonName.DmsVodPlayRecord",commonName.getProperty("CommonName.DmsVodPlayRecord"))
			.replaceAll("CommonName.BoMember",commonName.getProperty("CommonName.BoMember")))

		ratingProperties.setProperty("calculateRating",ratingProperties.getProperty("calculateRating")
			.replaceAll("CommonName.crudeRatingN",commonName.getProperty("CommonName.crudeRatingN")))

		ratingProperties
	}
}

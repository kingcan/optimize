package com.sdmctech.offline.dao

import com.sdmctech.offline.common.TDao
import com.sdmctech.offline.util.EnvUtil

class UserItemRatingDao extends TDao{

	val udfSigmod: Double => Double = (score:Double)=>10*1/(1+math.exp(-score))-4

   def basicHandle(sparksql:String,destinationTable:String): Unit ={
		 EnvUtil.take().udf.register("scaler", udfSigmod )
		val tmpDataFrame = createDataFrameBySparkSQL(sparksql)
		 writeDataFrameIntoHiveBySpark(tmpDataFrame,destinationTable)
	 }

}

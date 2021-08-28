package com.sdmctech.offline.dao

import com.sdmctech.offline.common.TDao
import com.sdmctech.offline.config.{CommonName, SQLforModelUtil}
import com.sdmctech.offline.mapper.CollaborativeFilteringMapper
import com.sdmctech.offline.util.{DataFrameUtil, EnvUtil, SparkHBaseUtil}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col

object CollaborativeFilteringDao extends TDao{

	val traingData: DataFrame = createDataFrameBySparkSQL(SQLforModelUtil.getUserItemRating)
	def saveData(alsRecallData:DataFrame): Unit ={
		alsRecallData.show()
		val tmpResults =DataFrameUtil.groupBy(alsRecallData,"uid","itemid")
			.withColumn("uid", col("uid").cast("string"))
			.withColumn("itemid", col("itemid").cast("string"))
   println("显示最终的ALS结果的类型"+tmpResults.printSchema());

		sparkHBaseUtil.insertDataFrameIntoHBase(tmpResults,
			CollaborativeFilteringMapper.als_results_catalog("itemid"),CommonName.regionsOfHBase)
		//modelUtil.saveRecall(alsRecallData, CommonName.colALSresult)
		//modelUtil.saveALSRecall(alsRecallData, CommonName.colALSresult)
	}

}

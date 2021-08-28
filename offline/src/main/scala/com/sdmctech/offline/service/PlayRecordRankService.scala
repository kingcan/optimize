package com.sdmctech.offline.service

import java.text.SimpleDateFormat
import java.util.Calendar

import org.apache.spark.sql.functions._
import com.sdmctech.offline.common.TService
import com.sdmctech.offline.config.CommonName
import com.sdmctech.offline.dao.PlayRecordRankDao
import com.sdmctech.offline.mapper.PlayRecordRankMapper
import com.sdmctech.offline.util.{DataFrameUtil, EnvUtil}

class PlayRecordRankService extends TService{
	override def dataAnalysis(): Any = {
		val spark =EnvUtil.take()
		val calendar1 = Calendar.getInstance
		val calendar2 = Calendar.getInstance
		val calendar3 = Calendar.getInstance
		val sdf1 = new SimpleDateFormat("yy-MM-dd")
		val sdf2 = new SimpleDateFormat("yy-MM")
		calendar1.add(Calendar.DATE, -60)
		calendar2.add(Calendar.DATE, -60 - 8)
		calendar3.add(Calendar.MONTH, -2)
		val N_days_ago = sdf1.format(calendar1.getTime)
		val N_days_ago2 = sdf1.format(calendar2.getTime)
		val N_days_ago3 = sdf2.format(calendar3.getTime)
		val date = "day" + N_days_ago
		val week = "week" + N_days_ago
		val month = "month" + N_days_ago3

		val actionsDay = spark.sql(
			"select '"+ date+"' ,parent_asset_id,name,count(1) cv from "+CommonName.DmsVodPlayRecord +" where date_ymd='"+ N_days_ago+"' " +
				" group by parent_asset_id,name order by cv desc limit 1000"//自己模拟的数据里不需要type类型
			//"and type='vod' group by parent_asset_id,name order by cv desc limit 1000"
			//"select uid,pv as clicked from user_action、"///这是从用户行为记录标准获取数据
		).toDF("date","parent_asset_id","name","cv")
		val actionsWeek = spark.sql(
			"select '"+ week+"' ,parent_asset_id,name,count(1) cv from "+CommonName.DmsVodPlayRecord +" where date_ymd<'"+ N_days_ago+"'" +
				" and date_ymd>'"+ N_days_ago2+"'"+
				" group by parent_asset_id,name order by cv desc limit 1000"//自己模拟的数据里不需要type类型
			//"and type='vod' group by parent_asset_id,name order by cv desc limit 1000"
			//"select uid,pv as clicked from user_action、"///这是从用户行为记录标准获取数据
		).toDF("date","parent_asset_id","name","cv")
		//		val actionsMonth = spark.sql(
		//			"select '"+month+"' ,parent_asset_id,name,count(1) cv from "+CommonName.DmsVodPlayRecord +" where substr(date_ymd,1,7)='"+ N_days_ago3+"'" +
		//				"and type='vod' group by parent_asset_id,name order by cv desc limit 1000"
		//			//"select uid,pv as clicked from user_action、"///这是从用户行为记录标准获取数据
		//		).toDF("date","parent_asset_id","name","cv")

		val actionsDayCategory = spark.sql("select '" + date + "',b.*" +
			"from  (select category_name,parent_asset_id, name, cnt,  row_number() over (partition by category_name order by cnt desc) rank " + //自己模拟的数据里面category_name
			"from    (select category_name,  parent_asset_id,    name,   count(1) as cnt   from " +
			" "+CommonName.DmsVodPlayRecord +"    where date_ymd='" + N_days_ago + "' " +
			//" "+CommonName.DmsVodPlayRecord +"    where date_ymd='" + N_days_ago + "' and type='vod'" + //自己模拟的数据里不需要type类型
			"    group by category_name,parent_asset_id,name    ) a" +
			" ) b " +
			"where b.rank<=1000"
		).toDF("date", "category", "parent_asset_id", "name", "cv", "rank")
		val actionsWeekCategory = spark.sql("select '" + week + "',b.*" +
			"from  (select category_name,parent_asset_id, name, cnt,  row_number() over (partition by category_name order by cnt desc) rank " +
			"from    (select category_name,  parent_asset_id,    name,   count(1) as cnt   from " +
			" "+CommonName.DmsVodPlayRecord +"    where date_ymd<'" + N_days_ago + "'" +
			" and date_ymd>'" + N_days_ago2 + "'" +
			//" and type='vod'" +//自己模拟的数据里不需要type类型
			"    group by category_name,parent_asset_id,name ) a" +
			" ) b " +
			"where b.rank<=1000"
		).toDF("date", "category", "parent_asset_id", "name", "cv", "rank")
		//		val actionsMonthCategory = spark.sql("select '" + month + "',b.*" +
		//			"from  (select category,parent_asset_id, name, cnt,  row_number() over (partition by category order by cnt desc) rank " +
		//			"from    (select category,  parent_asset_id,    name,   count(1) as cnt   from " +
		//			" "+CommonName.DmsVodPlayRecord +"    where substr(date_ymd,1,7)='" + N_days_ago3 + "'" +
		//			" and type='vod'" +
		//			"    group by category,parent_asset_id,name    ) a" +
		//			" ) b " +
		//			"where b.rank<=1000"
		//		).toDF("date", "category", "parent_asset_id", "name", "cv", "rank")
//		val phoenixUtil = PhoenixDataFrame(spark)
//		phoenixUtil.saveDataFrametoHBase(actionsDay, CommonName.WatchRank)
//		phoenixUtil.saveDataFrametoHBase(actionsWeek, CommonName.WatchRank)
//		//phoenixUtil.saveDataFrametoHBase(actionsMonth, CommonName.WatchRank)
//		phoenixUtil.saveDataFrametoHBase(actionsDayCategory, CommonName.WatchRankCategory)
//		phoenixUtil.saveDataFrametoHBase(actionsWeekCategory, CommonName.WatchRankCategory)
		//phoenixUtil.saveDataFrametoHBase(actionsMonthCategory, CommonName.WatchRankCategory)
		val tableName =CommonName.watchRank
		val cf="cf"
		val column="parent_asset_id"
		val column2="cv"
		val keyId ="date"
		PlayRecordRankDao.saveData(PlayRecordRankDao.aggData(actionsDay,keyId,column),PlayRecordRankMapper.watchRank_catalog(column))
		PlayRecordRankDao.saveData(PlayRecordRankDao.aggData(actionsDay,keyId,column2),PlayRecordRankMapper.watchRank_catalog(column2))
			PlayRecordRankDao.saveData(PlayRecordRankDao.aggData(actionsWeek,keyId,column),PlayRecordRankMapper.watchRank_catalog(column))
			PlayRecordRankDao.saveData(PlayRecordRankDao.aggData(actionsWeek,keyId,column2),PlayRecordRankMapper.watchRank_catalog(column2))
//		modelUtil.saveRecall(tableName,cf,actionsDay,column,keyId,column)
//		modelUtil.saveRecall(tableName,cf,actionsDay,column2,keyId,column2)
//		modelUtil.saveRecall(tableName,cf,actionsWeek,column,keyId,column)
//		modelUtil.saveRecall(tableName,cf,actionsWeek,column2,keyId,column2)
		//modelUtil.saveRecall(tableName,cf,actionsMonth,column,keyId,column)
		//modelUtil.saveRecall(tableName,cf,actionsMonth,column2,keyId,column2)

		val tableName2 =CommonName.watchRankCategory
		val column3="category"
//		modelUtil.saveRecall(tableName2,cf,actionsDayCategory,column,keyId,column)
//		modelUtil.saveRecall(tableName2,cf,actionsDayCategory,column2,keyId,column2)
//		modelUtil.saveRecall(tableName2,cf,actionsDayCategory,column3,keyId,column3)
//		modelUtil.saveRecall(tableName2,cf,actionsWeekCategory,column,keyId,column)
//		modelUtil.saveRecall(tableName2,cf,actionsWeekCategory,column2,keyId,column2)
//		modelUtil.saveRecall(tableName2,cf,actionsWeekCategory,column3,keyId,column3)
		PlayRecordRankDao.saveData(PlayRecordRankDao.aggData(actionsDayCategory,keyId,column),PlayRecordRankMapper.watchCategoryRank_catalog(column))
		PlayRecordRankDao.saveData(PlayRecordRankDao.aggData(actionsDayCategory,keyId,column2),PlayRecordRankMapper.watchCategoryRank_catalog(column2))
		PlayRecordRankDao.saveData(PlayRecordRankDao.aggData(actionsDayCategory,keyId,column3),PlayRecordRankMapper.watchCategoryRank_catalog(column3))
		PlayRecordRankDao.saveData(PlayRecordRankDao.aggData(actionsWeekCategory,keyId,column),PlayRecordRankMapper.watchCategoryRank_catalog(column))
		PlayRecordRankDao.saveData(PlayRecordRankDao.aggData(actionsWeekCategory,keyId,column2),PlayRecordRankMapper.watchCategoryRank_catalog(column2))
		PlayRecordRankDao.saveData(PlayRecordRankDao.aggData(actionsWeekCategory,keyId,column3),PlayRecordRankMapper.watchCategoryRank_catalog(column3))
	}
}

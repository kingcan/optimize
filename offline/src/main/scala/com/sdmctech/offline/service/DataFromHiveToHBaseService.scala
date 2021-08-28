package com.sdmctech.offline.service

import com.sdmctech.offline.common.TService
import com.sdmctech.offline.config.CommonName
import com.sdmctech.offline.dao.DataFromHiveToHBaseDao
import com.sdmctech.offline.util.{EnvUtil, HBaseUtil}
import org.apache.spark.sql.functions._
class DataFromHiveToHBaseService extends TService{

	override def dataAnalysis(): Any = {
		val spark=EnvUtil.take()
		val hbaseUtil = HBaseUtil(spark)
		print(CommonName.BoMember)
		val user_bo_member = spark.sql(" select d.id,typeEncoder(d.type),removeNull(d.area_id),removeNull(d.sex),ageCalculate(d.birth)," +
			"daysRegistered(d.first_login) from "+CommonName.BoMember +" d").toDF("uid","type","area_id","sex","age","ageRegistered")
			.withColumn("uid", col("uid").cast("string"))
			.withColumn("sex", col("sex").cast("int"))
			.toDF("uid","type","area_id","sex","age","ageRegistered")
		user_bo_member.printSchema()

		val item_bo_video_content = spark.sql(" select id,TypeEncoder2(type),episode_total,DuplicateRemove(removeNull2(director_id))," +
			"DuplicateRemove(removeNull2(actor_id)),DuplicateRemove(removeNull2(writer_id)),removeNull2(category_id)," +
			"removeNull(online_year_tag_id),removeNull(area_tag_id),removeNull(hit_count)," +
			"removeNull2(grade),removeNull2(star),DuplicateRemove(removeNull2(tags_id)),removeNull(duration)" +
			" from " +
			" "+CommonName.BoVideoContent +" where parent_asset_id is NULL")
			.toDF("itemid","type","episode_total","director_id","actor_id","writer_id","category_id","online_year_tag_id",
				"area_tag_id","hit_count","grade","star","tags_id","duration")
			.withColumn("itemid", col("itemid").cast("string"))
			.withColumn("grade", col("grade").cast("int"))
			.withColumn("star", col("star").cast("float"))
			.toDF("itemid","type","episode_total","director_id","actor_id","writer_id","category_id","online_year_tag_id",
				"area_tag_id","hit_count","grade","star","tags_id","duration")
		item_bo_video_content.printSchema()
		item_bo_video_content.show(truncate = false)
		//val hbaseByPhoenix = new PhoenixDataFrame(spark)
		//hbaseByPhoenix.saveDataFrametoHBase(user_bo_member,CommonName.userFeatureHBase)
		//hbaseByPhoenix.saveDataFrametoHBase(item_bo_video_content,CommonName.itemFeatureHBase)
		val video_content = spark.sql("select * from "+CommonName.BoVideoContent +" where parent_asset_id is NULL")
			.withColumn("id", col("id").cast("string"))
			.select("id","asset_id")
			  .toDF("itemid","parent_asset_id")
			//.toDF("ITEMID","parent_asset_id".toUpperCase)
		video_content.printSchema()
		//hbaseUtil.putResultData(CommonName.itemFeatureHBase,video_content,"0","parent_asset_id")
		//hbaseByPhoenix.saveDataFrametoHBase(video_content,CommonName.itemFeatureHBase)
    DataFromHiveToHBaseDao.saveData(user_bo_member,CommonName.userFeatureHBase)
		DataFromHiveToHBaseDao.saveData(item_bo_video_content,CommonName.itemFeatureHBase)
		DataFromHiveToHBaseDao.saveData(video_content,CommonName.itemFeatureHBase)
	}
}

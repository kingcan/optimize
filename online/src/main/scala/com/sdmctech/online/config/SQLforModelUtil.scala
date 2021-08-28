package com.sdmctech.online.config

object SQLforModelUtil {

	val getUserItemRating: String = "select * from "+CommonName.useritemrating+" where uid != -1"//dataframe with column name

	val getUserItemClick: String ="select a.member_id as uid,b.id as itemid,judgeSize(a.play_duration,10) as click from " +
		CommonName.userParentAssetidN +" as a join  "+CommonName.BoVideoContent +" as b on a.parent_asset_id=b.asset_id" +
		" where a.member_id!=-1 "+
		"limit 10"//测试专用小规模数据

  val getUserContentType: String = "select t.member_id,t.category_id,sum(t.play_duration) from ( " +
		"select a.member_id,b.category_id,a.play_duration from " +
		"(select * from dws_tmp_user_action3 where parent_asset_id != 'null') as a " +
		"join "+CommonName.BoVideoContent +" b on a.parent_asset_id = b.asset_id" +
		") as t group by t.member_id,t.category_id "

  val dws_dms_user_behavior_crude: String ="create table "+CommonName.crudeRating +" (member_id int," +
		"item_id int,play_times int,plytotal double)" +
		" ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\t'"

	val dws_dms_user_behavior_crude2: String ="create table "+CommonName.crudeRatingN +" (member_id int," +
		"item_id int,play_times int,plytotal double)" +
		" ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\t'"

	val dws_tmp_user_action: String ="CREATE TABLE "+CommonName.userParentAssetid+" (member_id int,parent_asset_id string," +
		"play_duration int ) " +
		" ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\t'"

	val dws_tmp_user_action2: String ="CREATE TABLE "+CommonName.userParentAssetidN+" (member_id int,parent_asset_id string," +
		"play_duration int ) " +
		" ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\t'"

	val dws_dms_user_behavior: String ="create table "+CommonName.userRating+" (member_id int," +
		"item_id int,score double )" +
		" ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\t'"
	val dws_dms_user_behavior2: String ="create table "+CommonName.userRatingN+" (member_id int," +
		"item_id int,score double )" +
		" ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\t'"

	val dws_dms_user_item_rating: String ="create table "+CommonName.useritemrating+" (uid int,itemid int,rating float)" +
		" ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\t'"
	val dws_dms_user_item_rating2: String ="create table "+CommonName.useritemratingN+" (uid int,itemid int,rating float)" +
		" ROW FORMAT DELIMITED FIELDS TERMINATED BY '\\t'"

	def getParam(name:String):String={
		name
	}


}

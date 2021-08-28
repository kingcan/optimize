package com.sdmctech.offline.service

import com.sdmctech.offline.common.TService
import com.sdmctech.offline.config.SQLforModelUtil
import com.sdmctech.offline.util.EnvUtil

/**
	* @Author: Larkin
	* @Date: 2021/5/21 9:43
	*/
class CreateTempHiveTableService extends TService{

	override def dataAnalysis(): Any = {
		val spark=EnvUtil.take()
		spark.sql(SQLforModelUtil.dws_dms_user_behavior_crude)
		spark.sql(SQLforModelUtil.dws_dms_user_behavior_crude2)
		spark.sql(SQLforModelUtil.dws_tmp_user_action)
		spark.sql(SQLforModelUtil.dws_tmp_user_action2)
		spark.sql(SQLforModelUtil.dws_dms_user_behavior)
		spark.sql(SQLforModelUtil.dws_dms_user_behavior2)
		spark.sql(SQLforModelUtil.dws_dms_user_item_rating)
		spark.sql(SQLforModelUtil.dws_dms_user_item_rating2)

	}
}

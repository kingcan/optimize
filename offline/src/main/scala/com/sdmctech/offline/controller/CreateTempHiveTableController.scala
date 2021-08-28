package com.sdmctech.offline.controller

import com.sdmctech.offline.common.TController
import com.sdmctech.offline.service.CreateTempHiveTableService

/**
	* @Author: Larkin
	* @Date: 2021/5/21 9:44
	*/
class CreateTempHiveTableController extends TController{
	private val createTempHiveTable=new CreateTempHiveTableService()
	override def dispatch(): Unit = {
		createTempHiveTable.dataAnalysis()
	}
}

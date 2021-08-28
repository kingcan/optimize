package com.sdmctech.offline.controller

import com.sdmctech.offline.common.TController
import com.sdmctech.offline.service.DataFromHiveToHBaseService

class DataFromHiveToHBaseController extends TController{
  private val dataFromHiveToHBaseService=new DataFromHiveToHBaseService()

	override def dispatch(): Unit = {
		dataFromHiveToHBaseService.dataAnalysis()
	}
}

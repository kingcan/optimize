package com.sdmctech.offline.controller

import com.sdmctech.offline.common.TController
import com.sdmctech.offline.service.OneHotCodeHandleService

class OneHotCodeHandleController extends TController{
	private val oneHotCodeHandleService=new OneHotCodeHandleService()
	override def dispatch(): Unit = {
		oneHotCodeHandleService.dataAnalysis()
	}
}

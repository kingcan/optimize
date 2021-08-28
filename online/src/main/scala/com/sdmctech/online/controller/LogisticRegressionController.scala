package com.sdmctech.online.controller

import com.sdmctech.online.common.TController
import com.sdmctech.online.service.LogisticRegressionService

class LogisticRegressionController extends TController{
private val logisticRegressionService =new LogisticRegressionService()

	override def dispatch(): Unit = {
		logisticRegressionService.dataAnalysis()
	}
}

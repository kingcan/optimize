package com.sdmctech.offline.controller

import com.sdmctech.offline.common.TController
import com.sdmctech.offline.service.LogisticRegressionService

class LogisticRegressionController extends TController{
private val logisticRegressionService =new LogisticRegressionService()

	override def dispatch(): Unit = {
		logisticRegressionService.dataAnalysis()
	}
}

package com.sdmctech.offline.controller

import com.sdmctech.offline.common.TController
import com.sdmctech.offline.service.CollaborativeFilteringService

class CollaborativeFilteringController extends TController{
	private val collaborativeFilteringService=new CollaborativeFilteringService()
	override def dispatch(): Unit = {
		collaborativeFilteringService.dataAnalysis()
	}
}

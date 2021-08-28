package com.sdmctech.offline.controller

import com.sdmctech.offline.common.TController
import com.sdmctech.offline.service.PlayRecordRankService

class PlayRecordRankController extends TController{
	private val playRecordRankService =new PlayRecordRankService()
	override def dispatch(): Unit = {
		playRecordRankService.dataAnalysis()
	}
}

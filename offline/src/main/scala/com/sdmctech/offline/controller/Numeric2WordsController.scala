package com.sdmctech.offline.controller

import com.sdmctech.offline.common.TController
import com.sdmctech.offline.service.Numeric2WordsService

class Numeric2WordsController extends TController{
  private val numeric2WordsService =new Numeric2WordsService()

	override def dispatch(): Unit = {
		numeric2WordsService.dataAnalysis()
	}
}

package com.sdmctech.offline.controller

import com.sdmctech.offline.common.TController
import com.sdmctech.offline.service.UserItemRatingService

class UserItemRatingController extends TController{

	private val userItemRatingService = new UserItemRatingService()


	override def dispatch(): Unit = {
		//TODO 执行业务操作
		userItemRatingService.dataAnalysis()
	}

}

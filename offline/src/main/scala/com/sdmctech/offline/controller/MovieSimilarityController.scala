package com.sdmctech.offline.controller

import com.sdmctech.offline.common.TController
import com.sdmctech.offline.service.MovieSimilarityService

/**
	* @Author: Larkin
	* @Date: 2021/5/21 11:13
	*/
class MovieSimilarityController extends TController{
	private val movieSimilarityService=new MovieSimilarityService()
	override def dispatch(): Unit = {
movieSimilarityService.dataAnalysis()
	}
}

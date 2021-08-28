package com.sdmctech.offline.application

import com.sdmctech.offline.common.TApplication
import com.sdmctech.offline.controller.MovieSimilarityController
import com.sdmctech.offline.multitenant.TenantAdapter

/**
	* @Author: Larkin
	* @Date: 2021/5/21 11:12
	*/
object MovieSimilarityApplication extends TApplication{
	def main(args: Array[String]): Unit = {
		TenantAdapter.put(args(0))
		start("MovieSimilarityApplication"+args(0)) {
			val controller = new MovieSimilarityController()
			controller.dispatch()
		}
		TenantAdapter.clear()
	}
}

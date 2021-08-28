package com.sdmctech.offline.application

import com.sdmctech.offline.common.TApplication
import com.sdmctech.offline.controller.Numeric2WordsController
import com.sdmctech.offline.multitenant.TenantAdapter

/**
	* @Author: Larkin
	* @Date: 2021/5/21 9:39
	*/
object Numeric2WordsApplication extends TApplication{
	def main(args: Array[String]): Unit = {
		TenantAdapter.put(args(0))
		start("Numeric2WordsApplication"+args(0)) {
			val controller = new Numeric2WordsController()
			controller.dispatch()
		}
		TenantAdapter.clear()
	}
}

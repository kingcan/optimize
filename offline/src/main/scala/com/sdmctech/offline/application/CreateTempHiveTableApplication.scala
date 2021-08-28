package com.sdmctech.offline.application

import com.sdmctech.offline.common.TApplication
import com.sdmctech.offline.controller.CreateTempHiveTableController
import com.sdmctech.offline.multitenant.TenantAdapter

/**
	* @Author: Larkin
	* @Date: 2021/5/21 9:39
	*/
object CreateTempHiveTableApplication extends TApplication{
	def main(args: Array[String]): Unit = {
		TenantAdapter.put(args(0))
		start("CreateTempHiveTableApplication"+args(0)) {

			val controller = new CreateTempHiveTableController()
			controller.dispatch()
		}
		TenantAdapter.clear()
	}
}

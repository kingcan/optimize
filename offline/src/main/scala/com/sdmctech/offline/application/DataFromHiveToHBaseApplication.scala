package com.sdmctech.offline.application
/**
	* @Author: Larkin
	* @Date: 2021/5/21 9:39
	*/
import com.sdmctech.offline.common.TApplication
import com.sdmctech.offline.controller.DataFromHiveToHBaseController
import com.sdmctech.offline.multitenant.TenantAdapter

object DataFromHiveToHBaseApplication extends TApplication{
	def main(args: Array[String]): Unit = {
		TenantAdapter.put(args(0))
		start("DataFromHiveToHBaseApplication"+args(0)) {

			val controller = new DataFromHiveToHBaseController()
			controller.dispatch()
		}
		TenantAdapter.clear()
	}
}

package com.sdmctech.offline.application

import com.sdmctech.offline.common.TApplication
import com.sdmctech.offline.controller.PlayRecordRankController
import com.sdmctech.offline.multitenant.TenantAdapter

object PlayRecordRankApplication extends TApplication{
	def main(args: Array[String]): Unit = {
		TenantAdapter.put(args(0))
		start("PlayRecordRankApplication"+args(0)) {
			val controller = new PlayRecordRankController()
			controller.dispatch()
		}
		TenantAdapter.clear()
	}
}

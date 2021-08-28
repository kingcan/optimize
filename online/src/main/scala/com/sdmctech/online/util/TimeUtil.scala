package com.sdmctech.online.util

import java.text.SimpleDateFormat
import java.util.Date

class TimeUtil extends Serializable {


	def getCurrentDate():String={//获取当前日期
	var now:Date = new Date()
		var  dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
		var hehe = dateFormat.format( now )
		hehe.reverse
	}
}
object TimeUtil{
	def apply(): TimeUtil = new TimeUtil()
}

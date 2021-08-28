package com.sdmctech.offline.multitenant

object TenantAdapter {

	private val tenantLocal = new ThreadLocal[String]()

	def put(tenant: String): Unit ={
		tenantLocal.set(tenant)
	}

	def take():String={
		tenantLocal.get()
	}
	def clear(): Unit ={
		tenantLocal.remove()
	}

}

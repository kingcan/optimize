package com.sdmctech.connectTF.application

import com.sdmctech.connectTF.common.TApplication
import com.sdmctech.connectTF.controller.DNNforIRSController
import com.sdmctech.connectTF.multitenant.TenantAdapter

/**
 * @Author: Larkin
 * @Date: 2021/8/30 15:00
 */
object DNNforIRSApp extends TApplication{
  def main(args: Array[String]): Unit = {
    TenantAdapter.put(args(0))
    start("DNNforIRSApp"+args(0)) {

      val controller = new DNNforIRSController()
      controller.dispatch()
    }
    TenantAdapter.clear()
  }
}

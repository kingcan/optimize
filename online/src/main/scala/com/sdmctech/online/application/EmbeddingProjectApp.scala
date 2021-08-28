package com.sdmctech.online.application

import com.sdmctech.online.application.LogisticRegressionApplication.start
import com.sdmctech.online.common.TApplication
import com.sdmctech.online.controller.{EmbeddingProjectController, LogisticRegressionController}
import com.sdmctech.online.multitenant.TenantAdapter

/**
 * @Author: Larkin
 * @Date: 2021/6/30 15:04
 */
object EmbeddingProjectApp extends TApplication{
  def main(args: Array[String]): Unit = {
    TenantAdapter.put(args(0))
    start("EmbeddingProjectApp"+args(0)) {

      val controller = new EmbeddingProjectController()
      controller.dispatch()
    }
    TenantAdapter.clear()
  }
}

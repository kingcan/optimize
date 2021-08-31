package com.sdmctech.connectTF.controller

import com.sdmctech.connectTF.common.TController
import com.sdmctech.connectTF.service.DNNforIRSService

/**
 * @Author: Larkin
 * @Date: 2021/8/30 15:03
 */
class DNNforIRSController extends TController{
  private val  dNNforIRSService= new DNNforIRSService()
  override def dispatch(): Unit = {
    dNNforIRSService.dataAnalysis()
  }
}

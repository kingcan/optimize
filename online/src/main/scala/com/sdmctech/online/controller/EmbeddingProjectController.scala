package com.sdmctech.online.controller

import com.sdmctech.online.common.TController
import com.sdmctech.online.service.EmbeddingProjectService

/**
 * @Author: Larkin
 * @Date: 2021/6/30 15:05
 */
class EmbeddingProjectController extends TController{
  private val EmbeddingProjectService =new EmbeddingProjectService()
  override def dispatch(): Unit = {
    EmbeddingProjectService.dataAnalysis()
  }
}

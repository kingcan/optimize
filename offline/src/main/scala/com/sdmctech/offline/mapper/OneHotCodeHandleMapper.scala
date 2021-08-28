package com.sdmctech.offline.mapper

import com.sdmctech.offline.config.CommonName

/**
 * @Author: Larkin
 * @Date: 2021/7/16 16:12
 */
object OneHotCodeHandleMapper {
  val user_catalog =DataFromHiveToHBaseMapper.user_catalog
  val item_catalog =DataFromHiveToHBaseMapper.item_catalog
  def item_addColumn_catalog(columnName:String) : String =
   s"""{
       |"table":{"namespace":"default", "name":"${CommonName.itemFeatureHBase}"},
       |"rowkey":"itemid",
       |"columns":{
       |"itemid":{"cf":"rowkey", "col":"itemid", "type":"string"},
       |"$columnName":{"cf":"${CommonName.featureCF}", "col":"$columnName", "type":"string"}
       |}
       |}""".stripMargin

  def user_addColumn_catalog(columnName:String) : String =
    s"""{
       |"table":{"namespace":"default", "name":"${CommonName.userFeatureHBase}"},
       |"rowkey":"uid",
       |"columns":{
       |"uid":{"cf":"rowkey", "col":"uid", "type":"string"},
       |"$columnName":{"cf":"${CommonName.featureCF}","col":"$columnName","type":"string"}
       |}
       |}""".stripMargin
}

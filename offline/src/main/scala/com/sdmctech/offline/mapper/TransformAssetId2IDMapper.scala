package com.sdmctech.offline.mapper

/**
 * @Author: Larkin
 * @Date: 2021/7/16 13:58
 */
object TransformAssetId2IDMapper {
  val catalog =
    s"""{
       |"table":{"namespace":"default", "name":"parentAssetId2itemId2"},
       |"rowkey":"PARENT_ASSET_ID",
       |"columns":{
       |"PARENT_ASSET_ID":{"cf":"rowkey", "col":"PARENT_ASSET_ID", "type":"string"},
       |"ITEMID":{"cf":"cf", "col":"ITEMID", "type":"string"}
       |}
       |}""".stripMargin
}

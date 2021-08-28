package com.sdmctech.offline.mapper

import com.sdmctech.offline.config.CommonName

/**
 * @Author: Larkin
 * @Date: 2021/7/20 19:17
 */
object PlayRecordRankMapper {
  def watchRank_catalog(columnName:String) : String =
    s"""{
       |"table":{"namespace":"default", "name":"${CommonName.WatchRank}"},
       |"rowkey":"date",
       |"columns":{
       |"date":{"cf":"rowkey", "col":"date", "type":"string"},
       |"$columnName":{"cf":"${CommonName.WatchRankCF}", "col":"$columnName", "type":"string"}
       |}
       |}""".stripMargin
  def watchCategoryRank_catalog(columnName:String) : String =
    s"""{
       |"table":{"namespace":"default", "name":"${CommonName.WatchRank}"},
       |"rowkey":"date",
       |"columns":{
       |"date":{"cf":"rowkey", "col":"date", "type":"string"},
       |"$columnName":{"cf":"${CommonName.WatchRankCategoryCF}", "col":"$columnName", "type":"string"}
       |}
       |}""".stripMargin
}

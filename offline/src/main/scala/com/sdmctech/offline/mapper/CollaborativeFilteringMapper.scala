package com.sdmctech.offline.mapper

import com.sdmctech.offline.config.CommonName
import org.apache.spark.sql.types._
import spire.syntax.std.array
/**
 * @Author: Larkin
 * @Date: 2021/7/16 17:59
 */
object CollaborativeFilteringMapper {
  def als_results_catalog(columnName:String) : String =
    s"""{
       |"table":{"namespace":"default", "name":"${CommonName.rsRecall}"},
       |"rowkey":"uid",
       |"columns":{
       |"uid":{"cf":"rowkey", "col":"uid", "type":"string"},
       |"$columnName":{"cf":"${CommonName.recall_cf}","col":"${CommonName.colALSresult}","type":"string"}
       |}
       |}""".stripMargin
}

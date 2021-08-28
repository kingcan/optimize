package com.sdmctech.offline.mapper

import com.sdmctech.offline.config.CommonName

/**
 * @Author: Larkin
 * @Date: 2021/7/19 15:43
 */
object LogisticRegressionMapper {
/**
 * val lrAssembleVector =Array( "userOneHotFeature","TAGS","CATEGORY","ACTOR","WRITER","DIRECTOR","itemOneHotFeature",
 * "AGE","AGEREGISTERED","STAR","DURATION","EPISODE_TOTAL","HIT_COUNT")
 * new version("userOneHotfeatures","age","ageRegistered","itemOneHotfeatures",
 * "tagsvector","categoryvector","actorvector","writervector","directorvector",
 * "star","duration","episode_total","hit_count")
 * */
def user_catalog =
  s"""{
     |"table":{"namespace":"default", "name":"${CommonName.userFeatureHBase}"},
     |"rowkey":"uid",
     |"columns":{
     |"uid":{"cf":"rowkey", "col":"uid", "type":"string"},
     |"userOneHotfeatures":{"cf":"${CommonName.featureCF}", "col":"userOneHotfeatures", "type":"string"},
     |"age":{"cf":"${CommonName.featureCF}", "col":"age", "type":"int"},
     |"ageRegistered":{"cf":"${CommonName.featureCF}", "col":"ageRegistered","type":"long"}
     |}
     |}""".stripMargin

  def item_catalog: String =
    s"""{
       |"table":{"namespace":"default", "name":"${CommonName.itemFeatureHBase}"},
       |"rowkey":"itemid",
       |"columns":{
       |"itemid":{"cf":"rowkey", "col":"itemid", "type":"string"},
       |"itemOneHotfeatures":{"cf":"${CommonName.featureCF}", "col":"itemOneHotfeatures", "type":"string"},
       |"directorvector":{"cf":"${CommonName.featureCF}", "col":"directorvector", "type":"string"},
       |"actorvector":{"cf":"${CommonName.featureCF}", "col":"actorvector", "type":"string"},
       |"writervector":{"cf":"${CommonName.featureCF}", "col":"writervector", "type":"string"},
       |"categoryvector":{"cf":"${CommonName.featureCF}", "col":"categoryvector", "type":"string"},
       |"tagsvector":{"cf":"${CommonName.featureCF}", "col":"tagsvector", "type":"string"},
       |"star":{"cf":"${CommonName.featureCF}", "col":"star", "type":"float"},
       |"duration":{"cf":"${CommonName.featureCF}", "col":"duration", "type":"int"},
       |"episode_total":{"cf":"${CommonName.featureCF}", "col":"episode_total", "type":"int"},
       |"hit_count":{"cf":"${CommonName.featureCF}", "col":"hit_count", "type":"int"}
       |}
       |}""".stripMargin

  def item_parentassetid_catalog : String =
    s"""{
       |"table":{"namespace":"default", "name":"${CommonName.itemFeatureHBase}"},
       |"rowkey":"itemid",
       |"columns":{
       |"itemid":{"cf":"rowkey", "col":"itemid", "type":"string"},
       |"parent_asset_id":{"cf":"${CommonName.featureCF}", "col":"parent_asset_id", "type":"string"}
       |}
       |}""".stripMargin
/**
 * "uid", "itemid", "noclick", "prediction"，"rank"
 * */
def lr_results_tmp_catalog : String =
  s"""{
     |"table":{"namespace":"default", "name":"${CommonName.lrResult}"},
     |"rowkey":"uid",
     |"columns":{
     |"uid":{"cf":"rowkey", "col":"uid", "type":"string"},
     |"itemid":{"cf":"${CommonName.recall_cf}","col":"itemid","type":"string"},
     |"noclick":{"cf":"${CommonName.recall_cf}", "col":"noclick", "type":"double"},
     |"prediction":{"cf":"${CommonName.recall_cf}","col":"prediction","type":"double"},
     |"rank":{"cf":"${CommonName.recall_cf}","col":"rank","type":"int"}
     |}
     |}""".stripMargin
  def lr_results_catalog : String =
    s"""{
       |"table":{"namespace":"default", "name":"${CommonName.lrResult}"},
       |"rowkey":"uid",
       |"columns":{
       |"uid":{"cf":"rowkey", "col":"uid", "type":"string"},
       |"itemid":{"cf":"${CommonName.recall_cf}","col":"itemid","type":"string"}
       |}
       |}""".stripMargin
  def lr_result_assetid_catalog(tableName:String) : String =
    s"""{
       |"table":{"namespace":"default", "name":"$tableName"},
       |"rowkey":"uid",
       |"columns":{
       |"uid":{"cf":"rowkey", "col":"uid", "type":"string"},
       |"parent_asset_id":{"cf":"${CommonName.recall_cf}","col":"after_lr_asset_id","type":"string"}
       |}
       |}""".stripMargin

}

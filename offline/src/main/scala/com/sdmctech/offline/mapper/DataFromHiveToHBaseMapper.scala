package com.sdmctech.offline.mapper

import com.sdmctech.offline.config.CommonName

/**
 * @Author: Larkin
 * @Date: 2021/7/15 19:27
 */
object DataFromHiveToHBaseMapper {

  /**
   *输入的dataframe .toDF("uid","type","area_id","sex","age","ageRegistered")
   */

  def user_catalog =
    s"""{
       |"table":{"namespace":"default", "name":"${CommonName.userFeatureHBase}"},
       |"rowkey":"uid",
       |"columns":{
       |"uid":{"cf":"rowkey", "col":"uid", "type":"string"},
       |"type":{"cf":"${CommonName.featureCF}", "col":"type", "type":"int"},
       |"area_id":{"cf":"${CommonName.featureCF}", "col":"area_id", "type":"int"},
       |"sex":{"cf":"${CommonName.featureCF}", "col":"sex", "type":"int"},
       |"age":{"cf":"${CommonName.featureCF}", "col":"age", "type":"int"},
       |"ageRegistered":{"cf":"${CommonName.featureCF}", "col":"ageRegistered","type":"long"}
       |}
       |}""".stripMargin

  /**
   * 输入的dataframe .toDF("itemid","type","episode_total","director_id",
   * "actor_id","writer_id","category_id","online_year_tag_id",
   * "area_tag_id","hit_count","grade","star","tags_id","duration")
   * */
    def item_catalog: String =
      s"""{
         |"table":{"namespace":"default", "name":"${CommonName.itemFeatureHBase}"},
         |"rowkey":"itemid",
         |"columns":{
         |"itemid":{"cf":"rowkey", "col":"itemid", "type":"string"},
         |"type":{"cf":"${CommonName.featureCF}", "col":"type", "type":"int"},
         |"episode_total":{"cf":"${CommonName.featureCF}", "col":"episode_total", "type":"int"},
         |"director_id":{"cf":"${CommonName.featureCF}", "col":"director_id", "type":"string"},
         |"actor_id":{"cf":"${CommonName.featureCF}", "col":"actor_id", "type":"string"},
         |"writer_id":{"cf":"${CommonName.featureCF}", "col":"writer_id", "type":"string"},
         |"category_id":{"cf":"${CommonName.featureCF}", "col":"category_id", "type":"string"},
         |"online_year_tag_id":{"cf":"${CommonName.featureCF}", "col":"online_year_tag_id", "type":"int"},
         |"area_tag_id":{"cf":"${CommonName.featureCF}", "col":"area_tag_id", "type":"int"},
         |"hit_count":{"cf":"${CommonName.featureCF}", "col":"hit_count", "type":"int"},
         |"grade":{"cf":"${CommonName.featureCF}", "col":"grade", "type":"int"},
         |"star":{"cf":"${CommonName.featureCF}", "col":"star", "type":"float"},
         |"tags_id":{"cf":"${CommonName.featureCF}", "col":"tags_id", "type":"string"},
         |"duration":{"cf":"${CommonName.featureCF}", "col":"duration", "type":"int"}
         |}
         |}""".stripMargin

  /**
   * 输入的dataframe .toDF("itemid","type","episode_total","director_id",
   * "actor_id","writer_id","category_id","online_year_tag_id",
   * "area_tag_id","hit_count","grade","star","tags_id","duration")
   * */
  def item_parentassetid_catalog: String =
    s"""{
       |"table":{"namespace":"default", "name":"${CommonName.itemFeatureHBase}"},
       |"rowkey":"itemid",
       |"columns":{
       |"itemid":{"cf":"rowkey", "col":"itemid", "type":"string"},
       |"parent_asset_id":{"cf":"${CommonName.featureCF}", "col":"parent_asset_id", "type":"string"}
       |}
       |}""".stripMargin
}

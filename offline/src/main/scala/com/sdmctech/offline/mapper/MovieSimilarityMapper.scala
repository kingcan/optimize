package com.sdmctech.offline.mapper

import com.sdmctech.offline.config.CommonName

/**
 * @Author: Larkin
 * @Date: 2021/7/20 17:20
 */
object MovieSimilarityMapper {
  def item_catalog : String =
    s"""{
       |"table":{"namespace":"default", "name":"${CommonName.itemFeatureHBase}"},
       |"rowkey":"itemid",
       |"columns":{
       |"itemid":{"cf":"rowkey", "col":"itemid", "type":"string"},
       |"parent_asset_id":{"cf":"${CommonName.featureCF}", "col":"parent_asset_id", "type":"string"},
       |"director_id2word":{"cf":"${CommonName.featureCF}", "col":"director_id2word", "type":"string"},
       |"actor_id2word":{"cf":"${CommonName.featureCF}", "col":"actor_id2word", "type":"string"}
       |}
       |}""".stripMargin

  def similarity_result_catalog : String =
    s"""{
       |"table":{"namespace":"default", "name":"${CommonName.movie_similarity}"},
       |"rowkey":"AssetId1",
       |"columns":{
       |"AssetId1":{"cf":"rowkey", "col":"AssetId1", "type":"string"},
       |"AssetId2":{"cf":"${CommonName.commonCF}", "col":"AssetId2", "type":"string"},
       |"similarity":{"cf":"${CommonName.commonCF}", "col":"similarity", "type":"string"}
       |}
       |}""".stripMargin
}

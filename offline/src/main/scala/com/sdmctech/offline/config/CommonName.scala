package com.sdmctech.offline.config

import java.io.File
import java.util.Properties

import com.sdmctech.offline.multitenant.{MultiTenantDynamicProperties, TenantAdapter}
import com.sdmctech.offline.util.PropertiesUtil
import org.apache.hadoop.hbase.{HBaseConfiguration, HConstants}

object CommonName {
	val propertiesUtil =new PropertiesUtil()
	//val properties =propertiesUtil.initProperties("/common/stableName.properties")

	val stableProperties: Properties =propertiesUtil.initProperties("/common/stableName.properties")
	val commonNameProperties: Properties = MultiTenantDynamicProperties.changePropertiesByArgs(TenantAdapter.take())
	  //媒资库表名
	//(版本1：自己模拟的数据)
	  val DmsVodPlayRecord: String =commonNameProperties.getProperty("CommonName.DmsVodPlayRecord")
    val BoVideoContent: String = commonNameProperties.getProperty("CommonName.BoVideoContent")
 	  val BoMember: String = commonNameProperties.getProperty("CommonName.BoMember")
	  val Bo_Category_Properties: String =commonNameProperties.getProperty("CommonName.Bo_Category_Properties")
	  val Bo_Stars_Properties: String =commonNameProperties.getProperty("CommonName.Bo_Stars_Properties")
	  val Bo_Tag_Properties: String =commonNameProperties.getProperty("CommonName.Bo_Label_Properties")
//(版本2)

    val UserColName = "uid"//name of all uid column
	  val itemColName = "itemid"//name of all itemid column
	  val ratingName ="rating"//name of rating for ALS model
	  val dateFormatStr: String =stableProperties.getProperty("dateFormatStr")//common use in much situation
	 //rating for how many days
	  val intervalDays: Int = stableProperties.getProperty("intervalDays").toInt
	  //ALS relative
	  val ALSrecommendNumber = 100//same in LR
	  val colALSresult = "als"//ALS recall result columnName
	  //LR relative
	  val labelColName = "label"
	  val maxIter = 1
	  //path name
		val ALSmodelSavePath: String =commonNameProperties.getProperty("CommonName.ALSmodelSavePath") //prefix，hdfs path
	  val LRmodelSavePath: String =commonNameProperties.getProperty("CommonName.LRmodelSavePath") //prefix，hdfs path

	  //name of hive table in rating
	  val crudeRating: String = commonNameProperties.getProperty("CommonName.crudeRating")
	  val userRating: String =commonNameProperties.getProperty("CommonName.userRating")
	  val userParentAssetid: String =commonNameProperties.getProperty("CommonName.userParentAssetid")

	  val crudeRatingN: String = commonNameProperties.getProperty("CommonName.crudeRatingN")
	  val userRatingN: String =commonNameProperties.getProperty("CommonName.userRatingN")
	  val userParentAssetidN: String =commonNameProperties.getProperty("CommonName.userParentAssetidN")

    val useritemrating: String = commonNameProperties.getProperty("CommonName.useritemrating")
	  val useritemratingN: String = commonNameProperties.getProperty("CommonName.useritemratingN")
	//hbase table name
	//recall table name
	val rsRecall: String = commonNameProperties.getProperty("CommonName.rsRecall")
	val rsRecall3: String = commonNameProperties.getProperty("CommonName.rsRecall3")
	val lrResult: String = commonNameProperties.getProperty("CommonName.lrResult")
	val recall_cf = "recall"
	val recommend_history: String =commonNameProperties.getProperty("CommonName.recommend_history")

	//zookeeper url
	val zkUrl ="master,worker1,worker2:2181:/hbase"
	val hbaseConfig = HBaseConfiguration.create()
	//hbaseConfig.set("hbase.rootdir", "hdfs:\\\master:8020\\hbase")
	hbaseConfig.set("hbase.zookeeper.quorum", "master,worker1,worker2") // 设置zookeeper节点
	hbaseConfig.set("hbase.zookeeper.property.clientPort", "2181")
	hbaseConfig.setLong(HConstants.HBASE_REGIONSERVER_LEASE_PERIOD_KEY,120000)
	//user profile
	val scalerIndexName ="play_duration" //量化指标
	//phoenix setting
	val phoenixJDBC ="org.apache.phoenix.jdbc.PhoenixDriver"
	val phoenixJDBCurl ="jdbc:phoenix:master,worker1,worker2:2181/hbase"

	val watchRank: String =commonNameProperties.getProperty("CommonName.watchRank")
	val watchRankCategory: String =commonNameProperties.getProperty("CommonName.watchRankCategory")
	//feature engineering
	val userFeatureHBase: String =commonNameProperties.getProperty("CommonName.userFeatureHBase")//
	val itemFeatureHBase: String =commonNameProperties.getProperty("CommonName.itemFeatureHBase")
	val movie_similarity: String =commonNameProperties.getProperty("CommonName.Movie_Feature")

	val WatchRank: String =commonNameProperties.getProperty("CommonName.WatchRank")
	val WatchRankCategory: String =commonNameProperties.getProperty("CommonName.WatchRankCategory")
	val userFeatureColumnName =Seq("UID","0.TYPE","0.AREA_ID","0.SEX","0.AGE","0.AGEREGISTERED")
	val itemFeatureColumnName =Seq("ITEMID","0.TYPE","0.EPISODE_TOTAL","0.DIRECTOR_ID","0.ACTOR_ID","0.WRITER_ID","0.CATEGORY_ID",
		"0.ONLINE_YEAR_TAG_ID","0.AREA_TAG_ID","0.HIT_COUNT","0.GRADE","0.STAR","0.TAGS_ID","0.DURATION")
	val featureCF ="naturalFeature"
	val userOneHotCode = "userOneHotFeature"
	val itemOneHotCode = "itemOneHotFeature"
	val commonCF ="cf"
	val WatchRankCF="rank"
	val WatchRankCategoryCF="categoryRank"
	/*
	* 向HBase写入数据时指定的region数*/
	val regionsOfHBase = 3
	val itemMultiHotCode: Array[String] = Array("tags","category","actor","writer","director").map(_.toUpperCase)
	//val lrAssembleVector =Array( "userOneHotFeature","TAGS","CATEGORY","ACTOR","WRITER","DIRECTOR","itemOneHotFeature",
		//"AGE","AGEREGISTERED","STAR","DURATION","EPISODE_TOTAL","HIT_COUNT")
val lrAssembleVector =Array("userOneHotfeatures","age","ageRegistered","itemOneHotfeatures",
			"tagsvector","categoryvector","actorvector","writervector","directorvector",
	"star","duration","episode_total","hit_count")
	//将parentassetID转化为ITEMID
	val parentAssetId2itemId =Seq("ITEMID","parent_asset_id".toUpperCase)
}

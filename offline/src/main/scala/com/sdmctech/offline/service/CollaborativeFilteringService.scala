package com.sdmctech.offline.service

import com.sdmctech.offline.common.TService
import com.sdmctech.offline.config.{CommonName, SQLforModelUtil}
import com.sdmctech.offline.dao.CollaborativeFilteringDao
import com.sdmctech.offline.model.CollaborativeFiltering
import com.sdmctech.offline.util.EnvUtil
import org.apache.spark.ml.recommendation.ALSModel
import org.apache.spark.sql.DataFrame

class CollaborativeFilteringService extends TService{
	val modelUtil = ModelUtil(EnvUtil.take())
	override def dataAnalysis(): Any = {
		//迭代次数 ，一般 10 左右
		val maxIter = 5
		//正则，不要太大
		val reg = Array(0.05, 0.1)
		//维度数目，一般越多越好，计算时间也会越长，
		//一般在 10 - 200
		val rank = Array(20, 80)
		//学习率，不要太大
		val alpha = Array(2.0, 3.0)
		val als = CollaborativeFiltering(CollaborativeFilteringDao.traingData)
		//生成最佳的ALS模型
		val model: ALSModel = als.getModel(maxIter, rank, reg, alpha)
		//val alsTestRecallData = model.transform(data)
		//val alsRecallData = als.getALSRecall(model,spark)
		val alsRecallData = als.getALSRecall(model, EnvUtil.take())
		CollaborativeFilteringDao.saveData(alsRecallData)
	}

	//数据处理


	//val data:DataFrame = modelUtil.getUserItemRating2//训练集
	//生成候选集
	//val data3 = data.join(data2,Seq("uid"))
	//召回1：ALS



	//println("finished")
	//    val rmse = getEvaluate(predict)
	//    //      println(rmse)
	//    listMSE += rmse
	//    mapModel += (rmse->model)
	//存储候选集
//	modelUtil.saveRecall(alsRecallData, CommonName.colALSresult)
//	modelUtil.saveALSRecall(alsRecallData, CommonName.colALSresult)
	//		val watchedItems = modelUtil.getUserItemRating3
	//		val watchedResult  = watchedItems.select("uid","itemid")
	//		val watchedItems = modelUtil.getUserItemRating4
	//		import spark.implicits._
	//		val timeUtil =new TimeUtil()
	//		val currentData = timeUtil.getCurrentDate()
	//		val watchedResult  = watchedItems.rdd.map(row=>{
	//			val rowkey =currentData+row.getAs[Int]("uid").toString
	//			val itemid =row.getAs[Int]("itemid").toString
	//			(rowkey,itemid)
	//		}).toDF("uid","itemid").select("uid","itemid")
	//		//modelUtil.saveRecall2(watchedResult,"watchedItemList")
	//		modelUtil.saveRecall("history_rs_recall2","recall",watchedResult,
	//			"watchedItemList","uid","itemid")
	//modelUtil.saveRecall(alsRecallData,colALS)


}

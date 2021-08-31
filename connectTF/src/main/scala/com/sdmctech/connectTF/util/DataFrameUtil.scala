package com.sdmctech.connectTF.util

import org.apache.spark.ml.linalg
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.{DataFrame, functions}
import org.apache.spark.sql.functions.{col, collect_list}

/**
 * @Author: Larkin
 * @Date: 2021/7/16 17:53
 */
object DataFrameUtil {
  /**
   * 聚合，相当进行groupBy
   * */
  def groupBy(recommend:DataFrame,
              keyId:String,collectColumn:String): DataFrame ={
    val existingName = "collect_list("+collectColumn +")"
    val result = recommend.groupBy(col(keyId))
      .agg(collect_list(collectColumn))
      .withColumnRenamed(existingName,
        collectColumn)
      .select(col(keyId),
        col(collectColumn))
    result
  }
  def splitArrayInDataFrame(targetDataFrame:DataFrame,pkName:String,explodeName:String,newName:String):DataFrame= {
    val result = targetDataFrame.withColumn(newName, functions.explode(functions.col(explodeName))).select(col(pkName),
      col(newName))
    result
  }

  val parseStringToVector: UserDefinedFunction =EnvUtil.take().udf
    .register("SparseVectorFromString", (str:String)=>getSparseVectorFromString(str))

  def getSparseVectorFromString(input:String):linalg.Vector ={
    val tmp = input.substring(1,input.length-1).split("\\[")
    val tmp0 = tmp(0).substring(0,tmp(0).indexOf(",")).toInt
    var tmp1=Array(1)
    var tmp2=Array(1.0)
    if(tmp(1)!= null && !tmp(1).equals("],")){
      tmp1 = tmp(1).substring(0,tmp(1).length-2).split(",").map(_.toInt)
    }
    if(tmp(2)!= null && !tmp(2).equals("]")){
      tmp2 = tmp(2).substring(0,tmp(2).length-1).split(",").map(_.toDouble)
    }

    val vs = Vectors.sparse(tmp0, tmp1,tmp2 )
    vs
  }



}

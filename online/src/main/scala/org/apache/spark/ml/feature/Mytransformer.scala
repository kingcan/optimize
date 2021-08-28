/*
 * @Author: guoyilin
 * @Date: 2019-08-22
 * @Time: 19:59
 */
package org.apache.spark.ml.feature

import org.apache.spark.annotation.Since
import org.apache.spark.ml.{Transformer, linalg}
import org.apache.spark.ml.linalg.{VectorUDT, Vectors}
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.ml.param.shared._
import org.apache.spark.ml.util._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, Dataset}

import scala.language.existentials
//import org.apache.spark.annotation.Since

// 一定要有HasOutputCol，jpmml-spark里FeatureConverter.class的registerFeatures函数会用到，不然会报错
class Mytransformer(override val uid: String) extends Transformer with HasInputCols with HasOutputCol{
    // 可不写
//    final val inputCol= new Param[String](this, "inputCol", "The input column")
//    final val outputCol = new Param[String](this, "outputCol", "The output column")

    // 注意HasInputCols对应的是inputCols, 值是Array型, HasInputCol对应的是inputCol, 值是基本数据类型(不是Array型), HasOutputCol同理
    def setInputCol(value: Array[String]): this.type = set(inputCols, value)

    def setOutputCol(value: String): this.type = set(outputCol, value)

    def this() = this(Identifiable.randomUID("Mytransformer "))

    override def copy(extra: ParamMap): Mytransformer  = {
        defaultCopy(extra)
    }

    @Since("1.4.0")
    override def transformSchema(schema: StructType): StructType = {
//        val idx = schema.fieldIndex($(inputCols))
//        val field = schema.fields(idx)
//        if (field.dataType != DoubleType) {
//            throw new Exception(s"Input type ${field.dataType} did not match input type DoubleType")
//        }
        schema.add(StructField($(outputCol), new VectorUDT, false))
    }


    override def transform(df: Dataset[_]):DataFrame = {
        // 这个transform函数只是对df中某一列数据进行处理

        var string2vector = (x: String) => {
            getSparseVectorFromString(x)
        }
        var str2vec = udf(string2vector)
        // str2vec函数中传入你要处理的df中的列名
        df.withColumn($(outputCol), str2vec(col("featuresString")))
    }
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

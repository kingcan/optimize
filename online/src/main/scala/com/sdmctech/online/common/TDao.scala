package com.sdmctech.online.common

import com.sdmctech.online.util.EnvUtil
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame


trait TDao {
     //从文件获取rdd
    def readFile(path:String): RDD[String] = {
        EnvUtil.take()
          .sparkContext.textFile(path)
    }
    //执行sparksql生成dataframe
     def createDataFrameBySparkSQL(sparksql:String):DataFrame={
         EnvUtil.take().sql(sparksql)
     }
     //将dataFrame写入到hive表中
    def writeDataFrameIntoHiveBySpark(data:DataFrame,destinationTable:String): Unit ={
        data.createOrReplaceTempView("hive_tmp_table")
       overwriteTableFromTable("hive_tmp_table",destinationTable)
    }

    def overwriteTableFromTable(sourceTable:String,destinationTable:String): Unit ={
        EnvUtil.take().sql(
            s"insert overwrite table $destinationTable select * from $sourceTable")
    }


}

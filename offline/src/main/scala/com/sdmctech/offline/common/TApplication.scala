package com.sdmctech.offline.common

import com.sdmctech.offline.util.EnvUtil
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait TApplication {
   //  yarn-cluster  local[*]
    def start( app:String = "Application",master:String ="local[*]")( op : => Unit ): Unit = {
        val sparConf = new SparkConf()
        sparConf.set("hive.metastore.uris","thrift://10.10.63.211:9083")


        //sparConf.set("hive.exec.mode.local.auto","true")
        val sc = SparkSession.builder()
          .config(sparConf)
          .appName(app)
          .master(master)
          .enableHiveSupport()
          .getOrCreate()

      sc.sparkContext.setLogLevel("ERROR")
        EnvUtil.put(sc)

        try {
            op
        }
//        } catch {
//            case ex => println(ex.printStackTrace())
//        }

        // TODO 关闭连接
        sc.stop()
        EnvUtil.clear()
    }
}

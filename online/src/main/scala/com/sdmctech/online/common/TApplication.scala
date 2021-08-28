package com.sdmctech.online.common

import com.sdmctech.online.util.EnvUtil
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait TApplication {

    def start( app:String = "Application",master:String ="local[*]")( op : => Unit ): Unit = {
        val sparConf = new SparkConf()
        sparConf.set("hive.metastore.uris","thrift://master:9083")
        sparConf.set("hive.exec.mode.local.auto","true")
        val sc = SparkSession.builder()
          .config(sparConf)
          .appName(app)
          .master(master)
          .enableHiveSupport()
          .getOrCreate()

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

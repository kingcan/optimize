package com.sdmctech.connectTF.common

import com.sdmctech.connectTF.util.EnvUtil
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait TApplication {

    def start( app:String = "Application",master:String ="local[*]")( op : => Unit ): Unit = {
        val sparConf = new SparkConf()
        sparConf.set("hive.metastore.uris","thrift://master:9083")
        sparConf.set("hive.exec.mode.local.auto","true")
        sparConf.set("spark.sql.adaptive.enabled","true")
        sparConf.set("spark.sql.adaptive.shuffle.targetPostShuffleInputSize","64m")
        val sc = SparkSession.builder()
          .config(sparConf)
          .appName(app)
          .master(master)
          .enableHiveSupport()
          .getOrCreate()
        sc.sparkContext.setLogLevel("WARN")
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

package com.sdmctech.online.util

import org.apache.spark.sql.SparkSession

object EnvUtil {

    private val scLocal = new ThreadLocal[SparkSession]()

    def put( sc : SparkSession ): Unit = {
        scLocal.set(sc)
    }

    def take(): SparkSession = {
        scLocal.get()
    }

    def clear(): Unit = {
        scLocal.remove()
    }
}

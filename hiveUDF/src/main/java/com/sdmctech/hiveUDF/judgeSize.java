package com.sdmctech.hiveUDF;

import org.apache.hadoop.hive.ql.exec.UDF;

public class judgeSize extends UDF {

    public int evaluate(Integer duration,Integer sigmod) {
        int result = 0;
        if (duration == null ) {
            return result;
        }
        if (duration > sigmod){
            return 1;
        }
        return result;
    }
}

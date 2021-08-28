package com.sdmctech.hiveUDF;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

//已经注册了多久
public class daysRegistered extends UDF {
    public long evaluate(Text text){
        if(text==null||text.toString().equals("null")||text.toString().equals("NULL")){
            return -1;///注册时间不详
        }else{
            //计算注册了多久
            return intervalDays.getDay(text.toString());
        }
    }
}

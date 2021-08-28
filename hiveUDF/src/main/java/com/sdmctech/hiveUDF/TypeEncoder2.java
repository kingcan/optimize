package com.sdmctech.hiveUDF;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

//用来处理内容类型的udf
public class TypeEncoder2 extends UDF {

    public int evaluate(Text text){
        if(text.toString().equals("null")||text.toString().equals("NULL")){
            return -1;
        }else if(text.toString().equals("vod")){
            return 1;
        }else if(text.toString().equals("series")){
            return 2;
        }else if(text.toString().equals("feature")) {
            return 3;
        }else if(text.toString().equals("svod")) {
            return 4;
        }else{
            return 0;
        }
    }

}
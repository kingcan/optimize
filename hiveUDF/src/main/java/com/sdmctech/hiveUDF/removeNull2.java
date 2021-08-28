package com.sdmctech.hiveUDF;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

//null值处理
public class removeNull2 extends UDF {
    public String evaluate(Text text){
        if(text==null||text.toString().equals("null")||text.toString().equals("NULL")
                ||text.toString().equals("") ||text.toString().equals(" ")){
            return "-1";///注册时间不详
        }else{
            //计算注册了多久
            return (text.toString());
        }
    }
}

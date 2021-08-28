package com.sdmctech.hiveUDF;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.util.Calendar;

/*
根据生日获取年龄
* **/
public class ageCalculate extends UDF {

    public int evaluate(Text text){
        if(text==null||text.toString().equals("null")||text.toString().equals("NULL")||text.toString().equals("NaN-NaN-NaN")){
            return -1;///年龄未知
        }
        else if (intervalDays.getStringType(text.toString().substring(3,4))){///字符的情况
                return Calendar.getInstance().get(Calendar.YEAR) - Integer.valueOf(text.toString().substring(0,4)).intValue();
            }
            else {
            return Calendar.getInstance().get(Calendar.YEAR) - Integer.valueOf(text.toString().substring(7,11)).intValue();
            }

        }
    }
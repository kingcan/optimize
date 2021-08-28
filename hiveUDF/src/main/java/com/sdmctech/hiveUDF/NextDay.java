package com.sdmctech.hiveUDF;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Document:本类作用---->获取明天或者昨天时间
 * User: yangjf
 * Date: 2016/9/14 16:26
 */
public class NextDay extends UDF {
    public String evaluate(int i) {
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动，0代表今天的时间
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }
    public static void main(String[] args) {
        System.out.println("今天时间："+new NextDay().evaluate(0));
        System.out.println("明天时间："+new NextDay().evaluate(1));
        System.out.println("昨天天时间："+new NextDay().evaluate(-1));

    }
}


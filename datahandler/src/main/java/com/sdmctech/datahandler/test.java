package com.sdmctech.datahandler;

import com.sdmctech.common.util.PropertiesUtil;
import com.sdmctech.datahandler.sqoop2hive.SqoopUtil;

import java.util.Properties;

public class test {
    public static void main(String[] args) {
        PropertiesUtil propertiesUtil =new PropertiesUtil();
        Properties properties= PropertiesUtil.initProperties("/sqoopConnectMysql.properties");
        SqoopUtil sqoopUtil =new SqoopUtil();
        sqoopUtil.LoadDataBySqoopWithPartitions(properties,"2021-05-01");
    }
}

package com.sdmctech.datahandler.run;

import com.sdmctech.common.util.PropertiesUtil;

import java.io.File;
import java.util.Properties;

import static com.sdmctech.datahandler.common.CreateTableFactory.createHiveTableDynamic;

public class HiveCreateTableMain {
    public static void main(String[] args) {
        PropertiesUtil propertiesUtil =new PropertiesUtil();
        Properties properties = PropertiesUtil.initProperties("/hiveTables/common-hive-table.properties");
        createHiveTableDynamic(properties,args[0]);
    }
}

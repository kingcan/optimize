package com.sdmctech.datahandler.run;

import com.sdmctech.common.util.HbaseOperation;
import com.sdmctech.common.util.PropertiesUtil;
import com.sdmctech.datahandler.common.CreateTableFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class HBaseCreateTableMain {
    public static void main(String[] args) {
        //PropertiesUtil propertiesUtil =new PropertiesUtil();
        Properties properties= PropertiesUtil.initProperties("/hbase/common-hbase-table.properties");
        Set<String> tables=properties.stringPropertyNames();
        CreateTableFactory.createHBaseTableDynamic(properties,args[0]);
    }
}

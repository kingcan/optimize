package com.sdmctech.datahandler.run;

import com.sdmctech.common.util.PropertiesUtil;
import com.sdmctech.datahandler.common.CreateTableFactory;

import java.util.Properties;
import java.util.Set;

public class PhoenixCreateTableMain {
    public static void main(String[] args) {
        PropertiesUtil propertiesUtil =new PropertiesUtil();
        Properties properties= PropertiesUtil.initProperties("/phoenix/common-phoenix-table.properties");
        CreateTableFactory.createPhoenixTableDynamic(properties,args[0]);
    }

}

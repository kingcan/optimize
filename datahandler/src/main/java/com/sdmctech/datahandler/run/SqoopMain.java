package com.sdmctech.datahandler.run;

import com.sdmctech.common.util.PropertiesUtil;
import com.sdmctech.datahandler.bean.SqoopParams;
import com.sdmctech.datahandler.sqoop2hive.SqoopUtil;

import java.io.File;
import java.util.Properties;

public class SqoopMain {
    public static void main(String[] args) {
        PropertiesUtil propertiesUtil =new PropertiesUtil();
        SqoopUtil sqoopUtil = new SqoopUtil();
        Properties properties = PropertiesUtil.initProperties("/sqoop2hive-tables/common_table.properties");
        SqoopParams sqoopParams =new SqoopParams(args[0],  PropertiesUtil.initProperties("/stable_properties.properties")
                .getProperty("tableNamesToImport").split(","));
        sqoopUtil.LoadTenantsDataBySqoop(properties,sqoopParams);
    }
}

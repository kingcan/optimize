package com.sdmctech.datahandler.common;


import com.sdmctech.common.util.HbaseOperation;
import com.sdmctech.common.util.HiveUtil;
import com.sdmctech.common.util.PhoenixUtil;
import com.sdmctech.common.util.PropertiesUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;

/**
 * 根据配置文件和租户码进行Hive、HBase和Phoenix表的创建
 * */
public class CreateTableFactory {
    private static Properties properties =PropertiesUtil.initProperties("/stable_properties.properties");

    public static PhoenixUtil getPhoenixUtil() {
        return phoenixUtil;
    }

    private static PhoenixUtil phoenixUtil=new PhoenixUtil(
            properties.getProperty("phoenix_url")
    );

    public static HiveUtil getHiveUtil() {
        return hiveUtil;
    }

    private static HiveUtil hiveUtil=new HiveUtil(
            properties.getProperty("hive_url"),
            properties.getProperty("hive_user"),
            properties.getProperty("hive_password")
    );

    public static HbaseOperation getHbaseOperation() {
        return hbaseOperation;
    }

    private static HbaseOperation hbaseOperation=new HbaseOperation(
      properties.getProperty("hbase_url"),
            properties.getProperty("zooker_quorum")
    );
    public static void createHiveTableDynamic(Properties properties,String tenantCode){
        Set<String> tables = properties.stringPropertyNames();
        for (String table :tables) {
            try {
                hiveUtil.createTable(properties.getProperty(table).replaceAll("tableName",
                        table+tenantCode));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            hiveUtil.destory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createHBaseTableDynamic(Properties properties,String tenantCode){
        Set<String> tables = properties.stringPropertyNames();
        for (String table :tables){
            try {
                hbaseOperation.createTable(properties.getProperty(table)
                                .split(":")[0]+tenantCode,
                        properties.getProperty(table).split(":")[1].split(","));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            hbaseOperation.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createPhoenixTableDynamic(Properties properties,String tenantCode){
        Set<String> tables = properties.stringPropertyNames();
        for (String table :tables){
            try {
                phoenixUtil.createTable(properties.getProperty(table).replaceAll("tableName",
                        table+tenantCode));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            phoenixUtil.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

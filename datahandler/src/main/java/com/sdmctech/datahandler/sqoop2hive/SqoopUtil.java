package com.sdmctech.datahandler.sqoop2hive;

import com.sdmctech.common.util.HiveUtil;
import com.sdmctech.datahandler.bean.SqoopParams;
import com.sdmctech.datahandler.common.CreateTableFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.sqoop.Sqoop;
import org.apache.sqoop.SqoopOptions;
import org.apache.sqoop.tool.SqoopTool;

import java.util.Properties;

public class SqoopUtil {

    SqoopTool sqoopTool = SqoopTool.getTool("import");
    SqoopOptions sqoopOptions = new SqoopOptions();
    public  void LoadDataBySqoopWithPartitions(Properties properties,String partitionName){
        sqoopOptions.setConnectString(properties.getProperty("connect"));
        sqoopOptions.setUsername(properties.getProperty("username"));
        sqoopOptions.setPassword(properties.getProperty("password"));
        sqoopOptions.setNumMappers(Integer.parseInt(properties.getProperty("num-mappers")));
        sqoopOptions.setNullStringValue("\\\\N");
        sqoopOptions.setNullNonStringValue("\\\\N");
        sqoopOptions.setFieldsTerminatedBy('\t');
        sqoopOptions.setTargetDir(properties.getProperty("target-dir") + partitionName);
        sqoopOptions.setCompressionCodec(properties.getProperty("compression-codec"));
        sqoopOptions.setHiveDatabaseName(properties.getProperty("hive-database"));
        sqoopOptions.setHiveTableName(properties.getProperty("hive-table"));
        sqoopOptions.setHivePartitionKey(properties.getProperty("hive-partition-key"));
        //sqoopOptions.setHiveImport(true);
        //sqoopOptions.setOverwriteHiveTable(true);
        sqoopOptions.setHiveHome(properties.getProperty("hive-home"));
        //sqoopOptions.setWarehouseDir("/user/hive/warehouse/default");
        sqoopOptions.setHivePartitionValue(partitionName);
        //sqoopOptions.doOverwriteHiveTable();
        //sqoopOptions.setFailIfHiveTableExists(false);
        //sqoopOptions.setDirectMode(true);
        sqoopOptions.setSqlQuery(properties.getProperty("query")+"'"
                + partitionName+"'"+properties.getProperty("query2"));
        //sqoopOptions.setAppendMode(true);
        //sqoopOptions.setClassName(hiveTableName + UUID.randomUUID().toString());
        sqoopOptions.setHadoopMapRedHome(properties.getProperty("hadoop-home"));
        Configuration conf= new Configuration();
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.default.name", properties.getProperty("fs.default.name"));
        Sqoop sqoop = new Sqoop(sqoopTool, SqoopTool.loadPlugins(conf), sqoopOptions);
        Sqoop.runSqoop(sqoop, new String[]{});
        try {
            CreateTableFactory.getHiveUtil().loadDataByPartiton(properties.getProperty("target-dir") + partitionName,
                    properties.getProperty("hive-table"),
                    "("+properties.getProperty("hive-partition-key")+"='"+partitionName+"')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void LoadTenantsDataBySqoop(Properties properties,SqoopParams sqoopParams){
        for (String tableName:sqoopParams.getTableNames()){
            LoadDataBySqoop(properties,sqoopParams.getTenantCode(),tableName);
        }
    }
    public  void LoadDataBySqoop(Properties properties,String tenantCode,String tableName){
        sqoopOptions.setConnectString(properties.getProperty("connect")
                .replaceAll("TenantCode",tenantCode));
        sqoopOptions.setUsername(properties.getProperty("username"));
        sqoopOptions.setPassword(properties.getProperty("password"));
        sqoopOptions.setNumMappers(Integer.parseInt(properties.getProperty("num-mappers")));
        sqoopOptions.setNullStringValue("\\\\N");
        sqoopOptions.setNullNonStringValue("\\\\N");
        sqoopOptions.setFieldsTerminatedBy('\t');
        sqoopOptions.setTargetDir(properties.getProperty("target-dir")
                .replaceAll("TABLENAME",tableName)+tenantCode);
        sqoopOptions.setCompressionCodec(properties.getProperty("compression-codec"));
        sqoopOptions.setHiveDatabaseName(properties.getProperty("hive-database"));
        sqoopOptions.setHiveTableName(properties.getProperty("hive-table").replaceAll("TABLENAME",tableName)+tenantCode);
        sqoopOptions.setHivePartitionKey(properties.getProperty("hive-partition-key"));
        //sqoopOptions.setHiveImport(true);
        //sqoopOptions.setOverwriteHiveTable(true);
        sqoopOptions.setHiveHome(properties.getProperty("hive-home"));
        sqoopOptions.setSqlQuery(properties.getProperty("query")
        .replaceAll("TABLENAME",tableName));
        //sqoopOptions.setAppendMode(true);
        //sqoopOptions.setClassName(hiveTableName + UUID.randomUUID().toString());
        sqoopOptions.setHadoopMapRedHome(properties.getProperty("hadoop-home"));
        Configuration conf= new Configuration();
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.default.name", properties.getProperty("fs.default.name"));
        Sqoop sqoop = new Sqoop(sqoopTool, SqoopTool.loadPlugins(conf), sqoopOptions);
        Sqoop.runSqoop(sqoop, new String[]{});

        try {
            CreateTableFactory.getHiveUtil().loadData(properties.getProperty("target-dir")
                            .replaceAll("TABLENAME",tableName)+tenantCode ,
                    properties.getProperty("hive-table")
                            .replaceAll("TABLENAME",tableName)+tenantCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

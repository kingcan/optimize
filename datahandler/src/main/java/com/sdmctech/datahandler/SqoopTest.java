package com.sdmctech.datahandler;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.sqoop.Sqoop;
import org.apache.sqoop.hive.HiveConfig;
import org.apache.sqoop.tool.SqoopTool;

import java.io.IOException;

public class SqoopTest {
    public static void main(String[] args) throws IOException {
        System.out.println(" begin test2 sqoop");
        String[] argument = new String[]{
                "--connect", "jdbc:mysql://10.10.63.233:3306/cloud_bocms_100019570647?useSSL=false",
                "--username", "root",
                "--password", "SDMC@123456ab",
                "--table", "bo_video_content",
                "--hive-import", "--hive-database", "default", "--hive-overwrite", "--create-hive-table",
                "--hive-table", "ods_bo_video_content",
                "--delete-target-dir",
                "--hadoop-mapred-home", "/opt/cloudera/parcels/CDH-6.3.2-1.cdh6.3.2.p0.1605554/lib/hadoop"
        };
        SqoopTool sqoopTool = SqoopTool.getTool("import");
        Configuration conf = new Configuration();
        //conf.set("fs.default.name", "hdfs://master:8020");
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
//        Configuration hive = HiveConfig.getHiveConf(conf);
        Sqoop sqoop = new Sqoop(sqoopTool, SqoopTool.loadPlugins(conf));
        FileSystem fileSystem = FileSystem.get(conf);
        Path path = fileSystem.getHomeDirectory();
        // 判断output文件夹是否存在，如果存在则删除
        if (fileSystem.exists(path)) {
            // true的意思是，就算output有东西，也一带删除
            fileSystem.delete(path, true);
        }
        int res = Sqoop.runSqoop(sqoop, argument);
        System.out.println(res);
        System.out.println("执行sqoop结束");
    }
}

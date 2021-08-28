package com.sdmctech.common.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HiveUtil {

    private Statement statement = null;
    private static ResultSet rs = null;
   private Connection connection =null;

    public HiveUtil(String url,String user,String password) {
        open(url,user,password);
    }

    static {
        // 1.加载驱动
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
        } catch (ClassNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    private void open(String url,String user,String password) {
        try {
            // 2.打开连接
             connection = DriverManager.getConnection(url,user,password);
            // 3.获得操作对象
            statement = connection.createStatement();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
    /**
     * 创建数据库 - 用户注册时调用
     * @param databaseName 根据用户标识生成的数据库名称
     */
    public void createDatabase(String databaseName) {
        try {
            statement.execute("create database " + databaseName);
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
    /**
     * 切换数据库 - 只对当前会话有效
     * @param databaseName 目标数据库名称
     */
    public void changeDatabase(String databaseName) {
        try {
            statement.execute("use " + databaseName);
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    public void createTable( String sql) throws Exception {
        System.out.println("Running: " + sql);
        statement.execute(sql);
    }

    public void loadLocalData(String filePath,String tableName) throws Exception {
        String sql = "load data local inpath '" + filePath + "' overwrite into table "+tableName;
        System.out.println("Running: " + sql);
        statement.execute(sql);
    }
    public void loadData(String filePath,String tableName) throws Exception {
        String sql = "load data  inpath '" + filePath + "' overwrite into table "+tableName;
        System.out.println("Running: " + sql);
        statement.execute(sql);
    }
    public void loadDataByPartiton(String filePath,String tableName,String partition) throws Exception {
        String sql = "load data  inpath '" + filePath + "' overwrite into table "+tableName+" partition "+partition;
        System.out.println("Running: " + sql);
        statement.execute(sql);
    }
    public void selectData() throws Exception {
        String sql = "select * from t2";
        System.out.println("Running: " + sql);
        rs = statement.executeQuery(sql);
        System.out.println("编号" + "\t" + "姓名" );
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t" + rs.getString(2));
        }
    }
    public static void drop(Statement stmt, String dropSQL) throws Exception {
        boolean bool = stmt.execute(dropSQL);
        System.out.println("删除表是否成功:" + bool);
    }


        /**
         * 获得当前数据库中的数据列表 - 注意切换数据库
         * @return 数据表名称的集合
         */
    public List<String> getTableList() {
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("show tables");
            while(rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 获得数据表的简要信息
     * @param tableName 数据表名称
     * @return 列名及列的数据类型
     */
    public List<String> descTable(String tableName){
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("desc " + tableName);
            while(rs.next()) {
                list.add(rs.getString(1) + "\t" + rs.getString(2));
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 获取数据表前十条的预览数据
     * @param tableName 数据表名称
     * @return 数据表预览数据
     */
    public List<String> tableMsg(String tableName){
        int tableSize = descTable(tableName).size();
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("select * from "+ tableName +" limit 10");
            while(rs.next()) {
                String a = "";
                for(int i = 1; i <= tableSize; i++) {
                    a += rs.getString(i) + "\t";
                }
                list.add(a);
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 获得查询sql执行后的返回结果
     * @param sql 用户自定义sql
     * @return sql执行结果集中的所有数据
     */
    public List<String> getResultData(String sql){
        List<String> list = new ArrayList<>();
        // 生成一个对于当前流程唯一的中间表名称
        // 如果流程会反复执行则先删除该表再创建
        String tableName = "data_flow";
        sql = "create table " + tableName + " as " + sql;
        try {
            // 执行查询语句，同时使用一个表进行记录
            statement.execute(sql);
            // 获得中间表的列信息 - 取决于用户执行sql的结果集结构
            int tableSize = descTable(tableName).size();
            ResultSet rs = statement.executeQuery("select * from " + tableName);
            while(rs.next()) {
                String a = "";
                for(int i = 1; i <= tableSize; i++) {
                    a += rs.getString(i) + "\t";
                }
                list.add(a);
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return list;
    }

    public void destory() throws Exception {
        if ( rs != null) {
            rs.close();
        }
        if (statement != null) {
            statement.close();
        }
        if ( connection!= null) {
            connection.close();
        }
    }
}

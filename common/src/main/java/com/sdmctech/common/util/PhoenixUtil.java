package com.sdmctech.common.util;

import java.io.IOException;
import java.sql.*;

public class PhoenixUtil {
    private Statement statement = null;
    private Connection connection =null;
    private static ResultSet rs = null;
    static {
        // 1.加载驱动
        try {
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        } catch (ClassNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    private void open(String url) {
        try {
            // 2.打开连接
            connection = DriverManager.getConnection(url);
            // 3.获得操作对象
            statement = connection.createStatement();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
    public PhoenixUtil(String phoenix_url) {
        open(phoenix_url);
    }


    public void createTable(String sql) throws SQLException {
        statement.executeUpdate(sql);
    }

    public void queryTable(String sql) throws SQLException {
        rs = statement.executeQuery(sql);
       while (rs.next()) {
            System.out.println("id="+ rs.getString("id")+"|name="
                    +rs.getString("name")+"|age="+rs.getString("age")
                    +"sex="+rs.getString("sex"));
        }

    }

    public void close(){
        try {

            if (rs != null) {

                rs.close();

            }

            if (null != statement) {

                statement.close();

            }
            if (null != connection) {

                connection.close();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

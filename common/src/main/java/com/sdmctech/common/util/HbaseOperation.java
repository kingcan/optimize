package com.sdmctech.common.util;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HbaseOperation implements Serializable {

    public static Configuration configuration;

    private static Connection connection;

    private static Admin admin;

    private static TableDescriptor tableDescriptor;

   public HbaseOperation(String hbase_url,String quorum){
       init(hbase_url,quorum);
   }
    //建立连接

    private static void init(String hbase_url, String quorum) {

        configuration = HBaseConfiguration.create();

        configuration.set("hbase.rootdir", hbase_url);

        configuration.set("hbase.zookeeper.quorum", quorum);

        configuration.set("hbase.zookeeper.property.client", "2181");

        try {

            connection = ConnectionFactory.createConnection(configuration);

            admin = connection.getAdmin();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    //关闭连接

    public  void close() {

        try {

            if (admin != null) {

                admin.close();

            }

            if (null != connection) {

                connection.close();

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    /**
     * 1：创建表-pass
     *
     * @param tableName 表名
     * @param colFamily 列族
     * @throws IOException
     */

    public  void createTable(String tableName, String[] colFamily) throws IOException {


        TableName mytable = TableName.valueOf(tableName);

        if (admin.tableExists(mytable)) {

            System.out.println("talbe is exists!");

        } else {

            //表描述器构造器

            TableDescriptorBuilder tdb = TableDescriptorBuilder.newBuilder(mytable);

            //创建集合用于存放列描述对象

            List<ColumnFamilyDescriptor> families = new ArrayList<>();

            //将每个列族对应的ColumnFamilyDescriptor对象添加到集合中

            for (String cols : colFamily) {

                families.add(ColumnFamilyDescriptorBuilder.newBuilder(cols.getBytes()).build());

            }

            tableDescriptor = tdb.setColumnFamilies(families).build();

            //创建表

            admin.createTable(tableDescriptor);


        }


    }

    /**
     * 2：实现单个数据插入-pass
     * <p>
     * 修改数和插入数据一样，不会覆盖原来版本，通过时间戳区别
     *
     * @param tableName
     * @param rowKey
     * @param colFamily
     * @param col
     * @param val
     * @throws IOException
     */

    public void insertRow(String tableName, String rowKey, String colFamily, String col, String val) throws IOException {


        Table table = connection.getTable(TableName.valueOf(tableName));

        Put put = new Put(rowKey.getBytes());

        put.addColumn(colFamily.getBytes(), col.getBytes(), val.getBytes());

        table.put(put);

        table.close();

        close();

    }

    /**
     * 3-1：通过行键-列族-列：获取单个数据-pass
     *
     * @param tableName
     * @param rowKey
     * @param colFamily
     * @param col
     * @throws IOException
     */

    public  void getData(String tableName, String rowKey, String colFamily, String col) throws IOException {


        Table table = connection.getTable(TableName.valueOf(tableName));

        Get get = new Get(rowKey.getBytes());

        get.addColumn(colFamily.getBytes(), col.getBytes());

        Result result = table.get(get);

        showCell(result);

        table.close();

        close();

    }

    public String getItemId(String tableName, String rowKey, String colFamily, String col) throws IOException {


        Table table = connection.getTable(TableName.valueOf(tableName));

        Get get = new Get(rowKey.getBytes());

        get.addColumn(colFamily.getBytes(), col.getBytes());

        Result result = table.get(get);
        String ids = null;
        for (Cell cell : result.rawCells()) {
            ids = Bytes.toString(CellUtil.cloneValue(cell));
        }
        return ids.replace("WrappedArray(", "")
                .replace(")", "").trim();

    }

    /**
     * 3-2：通过行键获取值-pass
     *
     * @param tableName
     * @param rowKey
     * @throws IOException
     */

    public  void getDataByRowkey(String tableName, String rowKey) throws IOException {


        Table table = connection.getTable(TableName.valueOf(tableName));

        //获得一行

        Get get = new Get(Bytes.toBytes(rowKey));

        Result set = table.get(get);

        Cell[] cells = set.rawCells();

        System.out.print("查询结果");

        for (Cell cell : cells) {

            System.out.println(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()) + "::" +

                    Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));

        }

        table.close();

        close();

    }

    /**
     * 4：给制定表添加列族
     *
     * @param tableName
     * @param familyName
     * @throws IOException
     */

    public  void addColumnFamily(String tableName, String familyName) throws IOException {


        TableName tn = TableName.valueOf(tableName);


        // 通过新增的familyName来构建ColumnFamilyDescriptor对象

        ColumnFamilyDescriptor columnFamily = ColumnFamilyDescriptorBuilder.newBuilder(familyName.getBytes()).build();

        admin.addColumnFamily(tn, columnFamily);

        close();

    }


    /**
     * 5：删除单个数据-pass
     *
     * @param tableName
     * @param rowKey
     * @param colFamily
     * @param col
     * @throws IOException
     */

    public  void deleteRow(String tableName, String rowKey, String colFamily, String col) throws IOException {


        Table table = connection.getTable(TableName.valueOf(tableName));

        Delete delete = new Delete(rowKey.getBytes());

        //删除指定列族

        //delete.addFamily(Bytes.toBytes(colFamily));

        //删除指定列

        //delete.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));

        table.delete(delete);

        table.close();

        close();

    }


    /**
     * 格式化输出获取的数据
     *
     * @param result
     */

    public  void showCell(Result result) {

        Cell[] cells = result.rawCells();

        for (Cell cell : cells) {

            System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");

            System.out.println("Timetamp:" + cell.getTimestamp() + " ");

            System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");

            System.out.println("row Name:" + new String(CellUtil.cloneQualifier(cell)) + " ");

            System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");

        }

    }

    /**
     * 6：查看已有的表-pass
     *
     * @throws IOException
     */

    public  void listTables() throws IOException {


        TableName[] tables = admin.listTableNames();

        for (TableName table : tables) {

            System.out.println("hbase数据库的表:" + table.toString());

        }

        close();

    }

    /**
     * 7：删除一张表
     *
     * @param tableName
     * @throws IOException
     */

    public  void deleteTable(String tableName) throws IOException {


        TableName tn = TableName.valueOf(tableName);

        if (!admin.tableExists(tn)) {

            System.out.print("要删除的表不存在");

        } else {

            admin.disableTable(tn);

            admin.deleteTable(tn);

        }

        close();

    }
}

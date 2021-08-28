package com.sdmctech.datahandler.bean;

public class SqoopParams {


    private String tenantCode;
    private String[] tableNames;

    public SqoopParams(String tenantCode, String[] tableNames) {
        this.tenantCode = tenantCode;
        this.tableNames = tableNames;
    }

    public String[] getTableNames() {
        return tableNames;
    }

    public void setTableNames(String[] tableNames) {
        this.tableNames = tableNames;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }




}

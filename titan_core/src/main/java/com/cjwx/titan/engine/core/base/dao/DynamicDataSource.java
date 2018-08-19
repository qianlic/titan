package com.cjwx.titan.engine.core.base.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @Description: 动态多数据源
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    private Map<Object, Object> dataSourceMap = null;

    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }

    public static String getDbType() {
        return contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }

    public Map<Object, Object> getDataSourceMap() {
        return dataSourceMap;
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        this.dataSourceMap = targetDataSources;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();
    }

}

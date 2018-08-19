package com.cjwx.titan.engine.core.base.dao.query;

import com.cjwx.titan.engine.util.ObjectUtils;
import com.cjwx.titan.engine.util.StringUtils;

import java.util.List;

/**
 * @Description: 数据库sql助手
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class SqlHelper {

    public static final String DELETE = "DELETE ";
    public static final String UPDATE = "UPDATE ";
    public static final String SET = "SET ";
    public static final String SELECT = "SELECT ";
    public static final String FROM = "FROM ";
    public static final String WHERE = "WHERE ";
    public static final String GROUP_BY = "GROUP BY ";
    public static final String ORDER_BY = "ORDER BY ";

    public static String createDeleteSql(ExecuteParameter executeParameter) {
        StringBuilder sql = new StringBuilder();

        sql.append(createDeleteSql(executeParameter.getTable()));
        sql.append(createWhereSql(executeParameter.getWhereList()));
        executeParameter.setSql(sql.toString());

        return executeParameter.getSql();
    }

    public static String createUpdateSql(ExecuteParameter executeParameter) {
        StringBuilder sql = new StringBuilder();

        sql.append(createUpdateSql(executeParameter.getTable()));
        sql.append(createSetSql(executeParameter.getSetList()));
        sql.append(createWhereSql(executeParameter.getWhereList()));
        executeParameter.setSql(sql.toString());

        return executeParameter.getSql();
    }

    public static String createSelectSql(QueryParameter queryParameter) {
        StringBuilder sql = new StringBuilder();

        sql.append(createSelectSql(queryParameter.getSelectList()));
        sql.append(createFromSql(queryParameter.getFromList()));
        sql.append(createWhereSql(queryParameter.getWhereList()));
        sql.append(createGroupBySql(queryParameter.getGroupByList()));
        sql.append(createOrderBySql(queryParameter.getOrderByList()));
        queryParameter.setSql(sql.toString());

        return queryParameter.getSql();
    }

    public static String createCountSql(QueryParameter queryParameter) {
        StringBuilder sql = new StringBuilder();

        sql.append(createCountSql());
        sql.append(createFromSql(queryParameter.getFromList()));
        sql.append(createWhereSql(queryParameter.getWhereList()));
        sql.append(createGroupBySql(queryParameter.getGroupByList()));
        queryParameter.setSql(sql.toString());

        return queryParameter.getSql();
    }

    private static String createDeleteSql(String table) {
        return DELETE + FROM + table + StringUtils.SPACE_STRING;
    }

    private static String createUpdateSql(String table) {
        return UPDATE + table + StringUtils.SPACE_STRING;
    }

    private static String createSetSql(List<String> setList) {
        if (ObjectUtils.isEmpty(setList)) {
            return StringUtils.NULL_STRING;
        }
        return SET + StringUtils.listToString(setList) + StringUtils.SPACE_STRING;
    }

    public static String createCountSql() {
        return SELECT + " COUNT(0) ";
    }

    private static String createSelectSql(List<String> selectList) {
        if (ObjectUtils.isEmpty(selectList)) {
            return SELECT + " * ";
        }
        return SELECT + StringUtils.listToString(selectList) + StringUtils.SPACE_STRING;
    }

    private static String createFromSql(List<String> fromList) {
        if (ObjectUtils.isEmpty(fromList)) {
            return StringUtils.NULL_STRING;
        }
        return FROM + StringUtils.listToString(fromList) + StringUtils.SPACE_STRING;
    }

    private static String createWhereSql(List<String> whereList) {
        if (ObjectUtils.isEmpty(whereList)) {
            return StringUtils.NULL_STRING;
        }
        return WHERE + StringUtils.listToString(whereList, "\\s+", StringUtils.SPACE_STRING)
                + StringUtils.SPACE_STRING;
    }

    private static String createGroupBySql(List<String> groupByList) {
        if (ObjectUtils.isEmpty(groupByList)) {
            return StringUtils.NULL_STRING;
        }
        return GROUP_BY + StringUtils.listToString(groupByList) + StringUtils.SPACE_STRING;
    }

    private static String createOrderBySql(List<String> orderByList) {
        if (ObjectUtils.isEmpty(orderByList)) {
            return StringUtils.NULL_STRING;
        }
        return ORDER_BY + StringUtils.listToString(orderByList) + StringUtils.SPACE_STRING;
    }

}

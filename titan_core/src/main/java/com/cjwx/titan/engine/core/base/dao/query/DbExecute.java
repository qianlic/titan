package com.cjwx.titan.engine.core.base.dao.query;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.util.ObjectUtils;
import com.cjwx.titan.engine.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 数据库执行
 * @Author: qian li
 * @Date: 2018年04月26日 19:44
 */
public class DbExecute {

    private BaseDao baseDao;
    private ExecuteParameter executeParameter;

    public DbExecute(BaseDao baseDao) {
        this.baseDao = baseDao;
        this.executeParameter = new ExecuteParameter();
    }

    public DbExecute table(String table) {
        this.executeParameter.table(table);
        return this;
    }

    public DbExecute set(Map<String, Object> set) {
        if (ObjectUtils.isNotEmpty(set)) {
            set.forEach(this::set);
        }
        return this;
    }

    public DbExecute set(String column, Object value) {
        this.executeParameter.set(column + " = ?");
        this.executeParameter.arg(value);
        return this;
    }

    public DbExecute where(String where, Object value) {
        this.executeParameter.where(where);
        this.executeParameter.arg(value);
        return this;
    }

    public DbExecute where(String column, String condition, Object value) {
        this.executeParameter.where(new SqlCondition(column, condition, value));
        return this;
    }

    public <T> DbExecute in(String column, List<T> values) {
        if (ObjectUtils.isNotEmpty(values)) {
            if (ObjectUtils.isNotEmpty(this.executeParameter.getWhereList())) {
                column = SqlCondition.CONDITION.AND + column;
            }
            column += SqlCondition.CONDITION.IN + "( ";
            String[] s = new String[values.size()];
            Arrays.fill(s, " ? ");
            column += Arrays.stream(s).collect(Collectors.joining(StringUtils.COMMA_STRING));
            column += " )";
            this.executeParameter.where(column);
            this.executeParameter.args(values);
        }
        return this;
    }

    public DbExecute eq(Map<String, Object> where) {
        if (ObjectUtils.isNotEmpty(where)) {
            where.forEach(this::eq);
        }
        return this;
    }

    public DbExecute eq(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.EQ, value);
    }

    public DbExecute gt(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.GT, value);
    }

    public DbExecute lt(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.LT, value);
    }

    public DbExecute gtEq(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.GTEQ, value);
    }

    public DbExecute ltEq(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.LTEQ, value);
    }

    public DbExecute unequal(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.UNEQUAL, value);
    }

    public DbExecute like(String column, Object value) {
        return where(column, SqlCondition.CONDITION.LIKE, value);
    }

    public int execute(String sql) {
        Object[] args = this.executeParameter.getArgs();
        return this.baseDao.sqlQuery(sql, args).executeUpdate();
    }

    public int update() {
        return this.execute(SqlHelper.createUpdateSql(this.executeParameter));
    }

    public int delete() {
        return this.execute(SqlHelper.createDeleteSql(this.executeParameter));
    }

}

package com.cjwx.titan.engine.core.base.dao.query;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.util.ObjectUtils;
import com.cjwx.titan.engine.util.StringUtils;
import org.hibernate.query.NativeQuery;

import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 数据库查询
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class DbQuery<T> {

    private BaseDao baseDao;
    private QueryParameter queryParameter;
    private Class<T> bean;

    public DbQuery(BaseDao baseDao, Class<T> bean) {
        this.baseDao = baseDao;
        this.queryParameter = new QueryParameter();
        this.bean = bean;
        Table t = bean.getAnnotation(Table.class);
        if (ObjectUtils.isNotEmpty(t)) {
            this.table(t.name());
        }
    }

    public DbQuery<T> table(String table) {
        this.queryParameter.from(table);
        return this;
    }

    public DbQuery<T> select(String column) {
        this.queryParameter.select(column);
        return this;
    }

    public DbQuery<T> groupBy(String column) {
        this.queryParameter.groupBy(column);
        return this;
    }

    public DbQuery<T> orderBy(String column) {
        this.queryParameter.orderBy(column);
        return this;
    }

    public DbQuery<T> orderDesc(String column) {
        return this.orderBy(column + " DESC");
    }

    public DbQuery<T> orderAsc(String column) {
        return this.orderBy(column + " ASC");
    }

    public DbQuery<T> where(String where, Object value) {
        this.queryParameter.where(where);
        this.queryParameter.arg(value);
        return this;
    }

    public DbQuery<T> where(String column, String condition, Object value) {
        this.queryParameter.where(new SqlCondition(column, condition, value));
        return this;
    }

    public <R> DbQuery<T> in(String column, List<R> values) {
        if (ObjectUtils.isNotEmpty(values)) {
            if (ObjectUtils.isNotEmpty(this.queryParameter.getWhereList())) {
                column = SqlCondition.CONDITION.AND + column;
            }
            column += SqlCondition.CONDITION.IN + "( ";
            String[] s = new String[values.size()];
            Arrays.fill(s, " ? ");
            column += Arrays.stream(s).collect(Collectors.joining(StringUtils.COMMA_STRING));
            column += " )";
            this.queryParameter.where(column);
            this.queryParameter.args(values);
        }
        return this;
    }

    public DbQuery<T> eq(Map<String, Object> where) {
        if (ObjectUtils.isNotEmpty(where)) {
            where.forEach(this::eq);
        }
        return this;
    }

    public DbQuery<T> eq(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.EQ, value);
    }

    public DbQuery<T> gt(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.GT, value);
    }

    public DbQuery<T> lt(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.LT, value);
    }

    public DbQuery<T> gtEq(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.GTEQ, value);
    }

    public DbQuery<T> ltEq(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.LTEQ, value);
    }

    public DbQuery<T> unequal(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.UNEQUAL, value);
    }

    public DbQuery<T> like(String column, Object value) {
        return where(column, SqlCondition.CONDITION.LIKE, value);
    }

    public NativeQuery<T> build() {
        String sql = SqlHelper.createSelectSql(this.queryParameter);
        Object[] args = this.queryParameter.getArgs();
        return this.baseDao.sqlQuery(sql, args, this.bean);
    }

    public List<T> list() {
        return this.build().getResultList();
    }

    public T uniqueResult() {
        return this.build().getSingleResult();
    }

    public int count() {
        String sql = SqlHelper.createCountSql(this.queryParameter);
        Object[] args = this.queryParameter.getArgs();
        NativeQuery<BigInteger> q = this.baseDao.sqlQuery(sql, args);
        return q.getSingleResult().intValue();
    }

    public PageList<T> page(int start, int pageSize) {
        PageList<T> pageList = new PageList<>(start, pageSize);
        pageList.setList(this.build().setFirstResult(start).setMaxResults(pageSize).getResultList());
        pageList.setTotal(this.count());
        return pageList;
    }

}

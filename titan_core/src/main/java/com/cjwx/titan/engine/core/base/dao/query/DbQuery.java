package com.cjwx.titan.engine.core.base.dao.query;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.util.ObjectUtils;
import com.cjwx.titan.engine.util.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 数据库查询
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class DbQuery {

    private BaseDao baseDao;
    private QueryParameter queryParameter;

    public DbQuery(BaseDao baseDao) {
        this.baseDao = baseDao;
        this.queryParameter = new QueryParameter();
    }

    public DbQuery select(String column) {
        this.queryParameter.select(column);
        return this;
    }

    public DbQuery from(String table) {
        this.queryParameter.from(table);
        return this;
    }

    public DbQuery groupBy(String column) {
        this.queryParameter.groupBy(column);
        return this;
    }

    public DbQuery orderBy(String column) {
        this.queryParameter.orderBy(column);
        return this;
    }

    public DbQuery orderDesc(String column) {
        return this.orderBy(column + " DESC");
    }

    public DbQuery orderAsc(String column) {
        return this.orderBy(column + " ASC");
    }

    public DbQuery where(String where, Object value) {
        this.queryParameter.where(where);
        this.queryParameter.arg(value);
        return this;
    }

    public DbQuery where(String column, String condition, Object value) {
        this.queryParameter.where(new SqlCondition(column, condition, value));
        return this;
    }

    public <T> DbQuery in(String column, List<T> values) {
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

    public DbQuery eq(Map<String, Object> where) {
        if (ObjectUtils.isNotEmpty(where)) {
            where.forEach(this::eq);
        }
        return this;
    }

    public DbQuery eq(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.EQ, value);
    }

    public DbQuery gt(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.GT, value);
    }

    public DbQuery lt(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.LT, value);
    }

    public DbQuery gtEq(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.GTEQ, value);
    }

    public DbQuery ltEq(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.LTEQ, value);
    }

    public DbQuery unequal(String column, Object value) {
        return this.where(column, SqlCondition.CONDITION.UNEQUAL, value);
    }

    public DbQuery like(String column, Object value) {
        return where(column, SqlCondition.CONDITION.LIKE, value);
    }

    public DbQuery query(String table, String column, Object value) {
        this.from(table);
        return this.eq(column, value);
    }

    public DbQuery query(Object bean) {
        Table table = bean.getClass().getAnnotation(Table.class);
        Map<String, Object> params = ObjectUtils.objectToHashMap(table);
        if (ObjectUtils.isNotEmpty(params)) {
            params.forEach(this::eq);
        }
        return this;
    }

    public Query build() {
        String sql = SqlHelper.createSelectSql(this.queryParameter);
        Object[] args = this.queryParameter.getArgs();
        return this.baseDao.sqlQuery(sql, args).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
    }

    public Query build(Class bean) {
        String sql = SqlHelper.createSelectSql(this.queryParameter);
        Object[] args = this.queryParameter.getArgs();
        return this.baseDao.sqlQuery(sql, args, bean);
    }

    public <T> List<T> list(Class<T> bean) {
        return this.build(bean).list();
    }

    public List<Object> list() {
        return this.build().list();
    }

    public <T> T uniqueResult(Class<T> bean) {
        return (T) this.build(bean).setMaxResults(1).uniqueResult();
    }

    public Object uniqueResult() {
        return this.build().setMaxResults(1).uniqueResult();
    }

    public int count() {
        String sql = SqlHelper.createCountSql(this.queryParameter);
        Object[] args = this.queryParameter.getArgs();
        return Integer.parseInt(this.baseDao.sqlQuery(sql, args).uniqueResult().toString());
    }

    public <T> PageList<T> page(int start, int pageSize, Class<T> bean) {
        PageList pageList = new PageList(start, pageSize);
        pageList.setList(this.build(bean).setFirstResult(start).setMaxResults(pageSize).list());
        pageList.setTotal(this.count());
        return pageList;
    }

    public PageList<Object> page(int start, int pageSize) {
        PageList pageList = new PageList(start, pageSize);
        pageList.setList(this.build().setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setFirstResult(start).setMaxResults(pageSize).list());
        pageList.setTotal(this.count());
        return pageList;
    }

}

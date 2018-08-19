package com.cjwx.titan.engine.core.base.dao;

import com.cjwx.titan.engine.core.base.dao.query.DbExecute;
import com.cjwx.titan.engine.core.base.dao.query.DbQuery;
import com.cjwx.titan.engine.util.ObjectUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import javax.annotation.Resource;
import java.util.stream.IntStream;

/**
 * @Description: dao基类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public abstract class BaseDao {

    @Resource
    private SessionFactory sessionFactory;

    public SQLQuery sqlQuery(String sql) {
        return this.getSession().createSQLQuery(sql);
    }

    public SQLQuery sqlQuery(String sql, Object args[]) {
        SQLQuery q = this.sqlQuery(sql);
        if (ObjectUtils.isNotEmpty(args) && args.length > 0) {
            IntStream.range(0, args.length).forEach(idx -> q.setParameter(idx, args[idx]));
        }
        return q;
    }

    public Query sqlQuery(String sql, Object args[], Class bean) {
        return this.sqlQuery(sql, args).setResultTransformer(Transformers.aliasToBean(bean));
    }

    public void save(Object bean) {
        this.getSession().save(bean);
    }

    public void delete(Object bean) {
        this.getSession().delete(bean);
    }

    public void update(Object bean) {
        this.getSession().update(bean);
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public DbQuery getQuery() {
        return new DbQuery(this);
    }

    public DbExecute getExecute() {
        return new DbExecute(this);
    }

}

package com.cjwx.titan.engine.core.base.dao;

import com.cjwx.titan.engine.core.base.dao.query.DbExecute;
import com.cjwx.titan.engine.core.base.dao.query.DbQuery;
import com.cjwx.titan.engine.util.ObjectUtils;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @Description: dao基类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public abstract class BaseDao {

    @PersistenceContext
    private EntityManager entityManager;

    public NativeQuery sqlQuery(String sql) {
        return this.getSession().createNativeQuery(sql);
    }

    public NativeQuery sqlQuery(String sql, Object args[]) {
        NativeQuery q = this.sqlQuery(sql);
        if (ObjectUtils.isNotEmpty(args) && args.length > 0) {
            IntStream.range(0, args.length).forEach(idx -> q.setParameter(idx + 1, args[idx]));
        }
        return q;
    }

    public <T> NativeQuery<T> sqlQuery(String sql, Object args[], Class<T> bean) {
        NativeQuery<T> q = this.sqlQuery(sql, args);
        q.setResultTransformer(Transformers.aliasToBean(bean));
        return q;
    }

    public void insert(Object bean) {
        this.getSession().saveOrUpdate(bean);
    }

    public void batchInsert(List beans) {
        Session session = this.getSession();
        for (int i = 0; i < beans.size(); i++) {
            this.insert(beans.get(i));
            if (i % 100 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    public void delete(Object bean) {
        this.getSession().delete(bean);
    }

    public void update(Object bean) {
        this.getSession().update(bean);
    }

    public Session getSession() {
        return (Session) entityManager.getDelegate();
    }

    public <T> DbQuery<T> getQuery(Class<T> bean) {
        return new DbQuery<>(this, bean);
    }

    public <T> DbExecute<T> getExecute(Class<T> bean) {
        return new DbExecute(this, bean);
    }

}

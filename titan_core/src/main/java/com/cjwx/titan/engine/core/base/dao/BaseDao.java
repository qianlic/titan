package com.cjwx.titan.engine.core.base.dao;

import com.cjwx.titan.engine.core.base.dao.query.DbExecute;
import com.cjwx.titan.engine.core.base.dao.query.DbQuery;
import com.cjwx.titan.engine.util.ObjectUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @Description: dao基类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public abstract class BaseDao {

    @Resource
    private SessionFactory sessionFactory;

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

    public long insert(Object bean) {
        return (long) this.getSession().save(bean);
    }

    public long batchInsert(List beans) {
        Session session = this.getSession();
        long count = 0;
        for (int i = 0; i < beans.size(); i++) {
            count = count + (long) session.save(beans.get(i));
            if (i % 100 == 0) {
                session.flush();
                session.clear();
            }
        }
        return count;
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

    public <T> DbQuery<T> getQuery(Class<T> bean) {
        return new DbQuery<>(this, bean);
    }

    public <T> DbExecute<T> getExecute(Class<T> bean) {
        return new DbExecute(this, bean);
    }

}

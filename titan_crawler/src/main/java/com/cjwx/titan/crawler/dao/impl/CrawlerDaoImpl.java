package com.cjwx.titan.crawler.dao.impl;

import com.cjwx.titan.crawler.bean.ClrCrawlerBean;
import com.cjwx.titan.crawler.dao.CrawlerDao;
import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.util.ObjectUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CrawlerDaoImpl extends BaseDao implements CrawlerDao {

    @Override
    public int delete(List ids) {
        return this.getExecute(ClrCrawlerBean.class).in("id", ids).delete();
    }

    @Override
    public int update(int id, Map<String, Object> set) {
        return this.getExecute(ClrCrawlerBean.class).set(set).eq("id", id).update();
    }

    @Override
    public int update(List ids, boolean status) {
        return this.getExecute(ClrCrawlerBean.class).set("status", status).in("id", ids).update();
    }

    @Override
    public List<ClrCrawlerBean> select() {
        return this.getQuery(ClrCrawlerBean.class).list();
    }

    @Override
    public List<ClrCrawlerBean> select(List<String> ids) {
        if (ObjectUtils.isNotEmpty(ids)) {
            return this.getQuery(ClrCrawlerBean.class).eq("status", true).in("id", ids).list();
        }
        return null;
    }

    @Override
    public PageList<ClrCrawlerBean> select(int start, int size, Map<String, Object> where) {
        return this.getQuery(ClrCrawlerBean.class).eq(where).page(start, size);
    }

}

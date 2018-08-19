package com.cjwx.titan.crawler.dao.impl;

import com.cjwx.titan.crawler.bean.ClrWebUrlBean;
import com.cjwx.titan.crawler.dao.WebUrlDao;
import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class WebUrlDaoImpl extends BaseDao implements WebUrlDao {

    @Override
    public long createWebUrl(ClrWebUrlBean page) {
        return (long) this.getSession().save(page);
    }

    @Override
    public int deleteWebUrl(List ids) {
        return this.getExecute().table(ClrWebUrlBean.TABLE).in("id", ids).delete();
    }

    @Override
    public PageList<ClrWebUrlBean> findWebUrlList(int start, int size, Map<String, Object> whereCondition) {
        return this.getQuery().from(ClrWebUrlBean.TABLE).eq(whereCondition).page(start, size, ClrWebUrlBean.class);
    }

}

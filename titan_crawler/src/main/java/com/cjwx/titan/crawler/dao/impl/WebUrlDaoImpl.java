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
    public int delete(List ids) {
        return this.getExecute(ClrWebUrlBean.class).in("id", ids).delete();
    }

    @Override
    public PageList<ClrWebUrlBean> select(int start, int size, Map<String, Object> where) {
        return this.getQuery(ClrWebUrlBean.class).eq(where).page(start, size);
    }

}

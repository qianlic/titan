package com.cjwx.titan.server.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.ComArticleBean;
import com.cjwx.titan.server.dao.ArticleDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ArticleDaoImpl extends BaseDao implements ArticleDao {

    @Override
    public int delete(List ids) {
        return this.getExecute(ComArticleBean.class).in("id", ids).delete();
    }

    @Override
    public int update(int id, Map<String, Object> set) {
        return this.getExecute(ComArticleBean.class).set(set).eq("id", id).update();
    }

    @Override
    public PageList<ComArticleBean> select(int start, int size, Map<String, Object> where) {
        return this.getQuery(ComArticleBean.class).eq(where).page(start, size);
    }
}

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
    public void createArticle(ComArticleBean article) {
        this.save(article);
    }

    @Override
    public int deleteArticle(List ids) {
        return this.getExecute().table(ComArticleBean.TABLE).in("id", ids).delete();
    }

    @Override
    public int updateArticle(int id, Map<String, Object> set) {
        return this.getExecute().table(ComArticleBean.TABLE).set(set).eq("id", id).update();
    }

    @Override
    public PageList<ComArticleBean> findArticleList(int start, int size, Map<String, Object> wheres) {
        return this.getQuery().from(ComArticleBean.TABLE).eq(wheres).page(start, size, ComArticleBean.class);
    }
}

package com.cjwx.titan.server.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.ComArticleBean;

import java.util.List;
import java.util.Map;

public interface ArticleDao {

    void createArticle(ComArticleBean article);

    int deleteArticle(List ids);

    int updateArticle(int id, Map<String, Object> set);

    PageList<ComArticleBean> findArticleList(int start, int size, Map<String, Object> wheres);

}

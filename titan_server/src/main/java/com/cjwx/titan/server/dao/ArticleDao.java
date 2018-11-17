package com.cjwx.titan.server.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.ComArticleBean;

import java.util.List;
import java.util.Map;

public interface ArticleDao {

    void insert(Object bean);

    int delete(List ids);

    int update(int id, Map<String, Object> set);

    PageList<ComArticleBean> select(int start, int size, Map<String, Object> where);

}

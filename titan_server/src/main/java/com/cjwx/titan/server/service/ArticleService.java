package com.cjwx.titan.server.service;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.ComArticleBean;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年09月07日 14:36
 */
public interface ArticleService {

    void createArticle(ComArticleBean article);

    int deleteArticle(List ids);

    int updateArticle(int id, Map<String, Object> set);

    PageList<ComArticleBean> getArticleList(int start, int size, Map<String, Object> wheres);

}

package com.cjwx.spark.server.service;

import com.cjwx.spark.server.entity.ComArticle;
import com.cjwx.spark.engine.core.model.PageList;

import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年09月07日 14:36
 */
public interface ArticleService {

    int createArticle(ComArticle article);

    int deleteArticle(List<Long> ids);

    int updateArticle(ComArticle article);

    PageList<ComArticle> getArticleList(int start, int size, ComArticle article);

}

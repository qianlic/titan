package com.cjwx.spark.server.service;

import com.cjwx.spark.server.entity.ComArticleEntity;
import com.cjwx.spark.engine.core.model.PageList;

import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年09月07日 14:36
 */
public interface ArticleService {

    ComArticleEntity createArticle(ComArticleEntity article);

    int deleteArticle(List<Long> ids);

    ComArticleEntity updateArticle(ComArticleEntity article);

    PageList<ComArticleEntity> getArticleList(int start, int size, ComArticleEntity article);

}

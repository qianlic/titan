package com.cjwx.spark.server.service;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.server.dto.ComArticleDTO;

import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年09月07日 14:36
 */
public interface ArticleService {

    ResultDTO<Integer> createArticle(ComArticleDTO article) throws Exception;

    ResultDTO<Integer> updateArticle(ComArticleDTO article) throws Exception;

    ResultDTO<Integer> deleteArticle(List<Long> ids);

    ResultDTO<PageDTO<ComArticleDTO>> getArticleList(ComArticleDTO article, int start, int size) throws Exception;

}

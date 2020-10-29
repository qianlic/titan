package com.cjwx.spark.server.service.Impl;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.MapperUtils;
import com.cjwx.spark.engine.util.ObjectUtils;
import com.cjwx.spark.server.dto.ComArticleDTO;
import com.cjwx.spark.server.entity.ComArticle;
import com.cjwx.spark.server.repository.ArticleRepository;
import com.cjwx.spark.server.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 文章管理
 * @Author: qian li
 * @Date: 2018年09月07日 14:35
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleRepository articleRepository;

    @Override
    public ResultDTO<Integer> createArticle(ComArticleDTO article) throws Exception {
        return MapperUtils.insert(articleRepository, ObjectUtils.convert(article, ComArticle.class));
    }

    @Override
    public ResultDTO<Integer> updateArticle(ComArticleDTO article) throws Exception {
        return MapperUtils.update(articleRepository, ObjectUtils.convert(article, ComArticle.class));
    }

    @Override
    public ResultDTO<Integer> deleteArticle(List<Long> ids) {
        return MapperUtils.delete(articleRepository, ids);
    }

    @Override
    public ResultDTO<PageDTO<ComArticleDTO>> getArticleList(ComArticleDTO article, int start, int size) throws Exception {
        return MapperUtils.pageList(articleRepository, ObjectUtils.convert(article, ComArticle.class), start, size, ComArticleDTO.class);
    }

}

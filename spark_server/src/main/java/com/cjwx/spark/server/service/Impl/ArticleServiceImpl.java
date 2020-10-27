package com.cjwx.spark.server.service.Impl;

import com.cjwx.spark.engine.core.model.PageList;
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
    public int createArticle(ComArticle article) {
        return articleRepository.insert(article);
    }

    @Override
    public int deleteArticle(List<Long> ids) {
        return articleRepository.deleteBatchIds(ids);
    }

    @Override
    public int updateArticle(ComArticle article) {
        return articleRepository.updateById(article);
    }

    @Override
    public PageList<ComArticle> getArticleList(int start, int size, ComArticle article) {
        return PageList.of(articleRepository, article, start, size);
    }

}

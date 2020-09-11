package com.cjwx.spark.server.service.Impl;

import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.server.entity.ComArticleEntity;
import com.cjwx.spark.server.repository.ArticleRepository;
import com.cjwx.spark.server.service.ArticleService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
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
    public ComArticleEntity createArticle(ComArticleEntity article) {
        return articleRepository.save(article);
    }

    @Override
    public int deleteArticle(List<Long> ids) {
        return articleRepository.deleteByIdIn(ids);
    }

    @Override
    public ComArticleEntity updateArticle(ComArticleEntity article) {
        return articleRepository.save(article);
    }

    @Override
    public PageList<ComArticleEntity> getArticleList(int start, int size, ComArticleEntity article) {
        return PageList.of(articleRepository.findAll(Example.of(article), PageRequest.of(start, size)));
    }

}

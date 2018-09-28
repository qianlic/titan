package com.cjwx.titan.server.service.Impl;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.ComArticleBean;
import com.cjwx.titan.server.dao.ArticleDao;
import com.cjwx.titan.server.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: 文章管理
 * @Author: qian li
 * @Date: 2018年09月07日 14:35
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;


    @Override
    public void createArticle(ComArticleBean article) {
        articleDao.insert(article);
    }

    @Override
    public int deleteArticle(List ids) {
        return articleDao.delete(ids);
    }

    @Override
    public int updateArticle(int id, Map<String, Object> set) {
        return articleDao.update(id, set);
    }

    @Override
    public PageList<ComArticleBean> getArticleList(int start, int size, Map<String, Object> wheres) {
        return articleDao.select(start, size, wheres);
    }
}

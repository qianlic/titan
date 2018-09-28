package com.cjwx.titan.crawler.service.impl;

import com.cjwx.titan.crawler.bean.ClrPageBean;
import com.cjwx.titan.crawler.bean.ClrWebUrlBean;
import com.cjwx.titan.crawler.dao.PageDao;
import com.cjwx.titan.crawler.dao.WebUrlDao;
import com.cjwx.titan.crawler.service.WebUrlService;
import com.cjwx.titan.engine.core.model.PageList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WebUrlServiceImpl implements WebUrlService {

    @Resource
    private PageDao pageDao;
    @Resource
    private WebUrlDao webUrlDao;

    @Override
    public void createPage(ClrPageBean page) {
        page.setUrlId(webUrlDao.insert(page.getWebURL()));
        pageDao.insert(page);
    }

    @Override
    public int deletePage(List ids) {
        return pageDao.delete(ids);
    }

    @Override
    public int deleteWebUrl(List ids) {
        return webUrlDao.delete(ids);
    }

    @Override
    public ClrPageBean getPage(int id) {
        return pageDao.select(id);
    }

    @Override
    public PageList<ClrWebUrlBean> getWebUrlList(int start, int size, Map<String, Object> whereCondition) {
        return webUrlDao.select(start, size, whereCondition);
    }

}
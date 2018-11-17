package com.cjwx.titan.crawler.service.impl;

import com.cjwx.titan.crawler.bean.ClrCrawlerBean;
import com.cjwx.titan.crawler.crawler.schedule.CrawlerScheduler;
import com.cjwx.titan.crawler.crawler.schedule.UrlSeed;
import com.cjwx.titan.crawler.dao.CrawlerDao;
import com.cjwx.titan.crawler.service.CrawlerService;
import com.cjwx.titan.engine.core.model.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CrawlerServiceImpl
 *
 * @author Qian Li
 * @date 2016/6/2
 */
@Service
@Transactional
public class CrawlerServiceImpl implements CrawlerService {

    @Resource
    private CrawlerDao crawlerDao;
    @Autowired
    private CrawlerScheduler crawlerScheduler;

    @Override
    public void execute(List<String> ids) {
        crawlerScheduler.schedule(crawlerDao.select(ids)
                .stream()
                .map(c -> new UrlSeed(c.getCode(), c.getUrl()))
                .collect(Collectors.toList()));
    }

    @Override
    public void batchInsert(List<ClrCrawlerBean> crawlers) {
        crawlerDao.batchInsert(crawlers);
    }

    @Override
    public List<ClrCrawlerBean> getCrawlerList() {
        return crawlerDao.select();
    }

    @Override
    public PageList<ClrCrawlerBean> getCrawlerList(int start, int size, Map<String, Object> whereCondition) {
        return crawlerDao.select(start, size, whereCondition);
    }

}

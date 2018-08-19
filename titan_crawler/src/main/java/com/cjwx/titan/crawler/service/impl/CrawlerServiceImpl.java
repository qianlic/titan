package com.cjwx.titan.crawler.service.impl;

import com.cjwx.titan.crawler.BasicCrawler;
import com.cjwx.titan.crawler.bean.ClrCrawlerBean;
import com.cjwx.titan.crawler.crawler.CrawlConfig;
import com.cjwx.titan.crawler.crawler.CrawlConstant;
import com.cjwx.titan.crawler.crawler.CrawlController;
import com.cjwx.titan.crawler.dao.CrawlerDao;
import com.cjwx.titan.crawler.service.CrawlerService;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public void execute(List ids) {
        List<ClrCrawlerBean> crawlers = crawlerDao.findCrawlerByIds(ids);
        crawlers.forEach(c -> {
            if (CrawlConstant.contains(c.getName())) {
                CrawlController controller = CrawlConstant.get(c.getName());
                controller.close();
                controller.start(BasicCrawler.class, c.getNumber());
            } else {
                CrawlConfig config = new CrawlConfig();
                config.setResumableCrawling(c.isResumable());
                CrawlController controller = new CrawlController(config);
                controller.addSeed(c.getSeed());
                controller.start(BasicCrawler.class, c.getNumber());
                CrawlConstant.put(c.getName(), controller);
            }
        });
    }

    @Override
    public void createCrawler(ClrCrawlerBean crawler) {
        crawlerDao.createCrawler(crawler);
    }

    @Override
    public int deleteCrawler(List ids) {
        return crawlerDao.deleteCrawler(ids);
    }

    @Override
    public int updateCrawler(int id, Map<String, Object> set) {
        return crawlerDao.updateCrawler(id, set);
    }

    @Override
    public int updateStatus(List ids, boolean status) {
        return crawlerDao.updateStatus(ids, status);
    }

    @Override
    public PageList<ClrCrawlerBean> getCrawlerList(int start, int size, Map<String, Object> whereCondition) {
        return crawlerDao.findCrawlerList(start, size, whereCondition);
    }

    @Override
    public List<ClrCrawlerBean> findCrawlerByIds(String ids) {
        return crawlerDao.findCrawlerByIds(StringUtils.stringToList(ids));
    }

}

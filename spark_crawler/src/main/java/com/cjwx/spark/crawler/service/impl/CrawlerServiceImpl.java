package com.cjwx.spark.crawler.service.impl;

import com.cjwx.spark.crawler.crawler.schedule.CrawlerScheduler;
import com.cjwx.spark.crawler.entity.ClrCrawlerEntity;
import com.cjwx.spark.crawler.crawler.schedule.UrlSeed;
import com.cjwx.spark.crawler.repository.CrawlerRepository;
import com.cjwx.spark.crawler.service.CrawlerService;
import com.cjwx.spark.engine.core.model.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
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
    private CrawlerRepository crawlerRepository;
    @Autowired
    private CrawlerScheduler crawlerScheduler;

    @Override
    public void execute(List<Long> ids) {
        crawlerScheduler.schedule(crawlerRepository.findAllById(ids)
                .stream()
                .map(c -> new UrlSeed(c.getCode(), c.getUrl()))
                .collect(Collectors.toList()));
    }

    @Override
    public void batchInsert(List<ClrCrawlerEntity> crawlers) {
        crawlerRepository.saveAll(crawlers);
    }

    @Override
    public List<ClrCrawlerEntity> getCrawlerList() {
        return crawlerRepository.findAll();
    }

    @Override
    public PageList<ClrCrawlerEntity> getCrawlerList(int start, int size, ClrCrawlerEntity crawler) {
        return PageList.of(crawlerRepository.findAll(Example.of(crawler), PageRequest.of(start, size)));
    }

}

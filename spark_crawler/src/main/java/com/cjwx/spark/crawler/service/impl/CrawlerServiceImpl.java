package com.cjwx.spark.crawler.service.impl;

import com.cjwx.spark.crawler.crawler.schedule.CrawlerScheduler;
import com.cjwx.spark.crawler.crawler.schedule.UrlSeed;
import com.cjwx.spark.crawler.dto.ClrCrawlerDTO;
import com.cjwx.spark.crawler.entity.ClrCrawler;
import com.cjwx.spark.crawler.repository.CrawlerRepository;
import com.cjwx.spark.crawler.service.CrawlerService;
import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.MapperUtils;
import com.cjwx.spark.engine.util.ObjectUtils;
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
    @Resource
    private CrawlerScheduler crawlerScheduler;

    @Override
    public ResultDTO<List<ClrCrawlerDTO>> getCrawlerList() throws Exception {
        return MapperUtils.list(crawlerRepository, ClrCrawlerDTO.class);
    }

    @Override
    public ResultDTO<PageDTO<ClrCrawlerDTO>> getCrawlerList(ClrCrawlerDTO crawler, int start, int size) throws Exception {
        return MapperUtils.pageList(crawlerRepository,
                ObjectUtils.convert(crawler, ClrCrawler.class),
                start, size, ClrCrawlerDTO.class);
    }

    @Override
    public void execute(List<Long> ids) {
        List<ClrCrawler> crawlers = crawlerRepository.selectBatchIds(ids);
        if (crawlers != null && !crawlers.isEmpty()) {
            crawlerScheduler.schedule(crawlers.stream()
                    .map(c -> new UrlSeed(c.getCode(), c.getUrl()))
                    .collect(Collectors.toList()));
        }
    }

    @Override
    public void batchInsert(List<ClrCrawlerDTO> crawlers) {
        //TODO
    }

}

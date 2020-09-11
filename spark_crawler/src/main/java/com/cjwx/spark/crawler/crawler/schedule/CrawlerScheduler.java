package com.cjwx.spark.crawler.crawler.schedule;

import com.cjwx.spark.crawler.entity.ClrCrawlerEntity;
import com.cjwx.spark.crawler.service.CrawlerService;
import com.cjwx.spark.crawler.util.JsoupUtils;
import com.cjwx.spark.engine.util.ObjectUtils;
import com.cjwx.spark.engine.util.ProcessUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月16日 13:06
 */
@Slf4j
@Component
public class CrawlerScheduler {

    private Frontier frontier;
    @Autowired
    private Map<String, CrawlerFunction> crawlerFunctions;
    @Autowired
    private CrawlerService crawlerService;

    public void schedule(List<UrlSeed> urls) {
        frontier.scheduleAll(urls);
    }

    public void start(final int number, final int limit, final int interval) {
        try {
            frontier = new Frontier();
            frontier.empty();
            register();
            for (int i = 1; i <= number; i++) {
                createCrawlerThread(i, limit, interval);
            }
        } catch (Exception e) {
            log.error("Error happened", e);
        }
    }

    public void createCrawlerThread(int i, int limit, int interval) {
        new Thread(() -> {
            while (true) {
                List<UrlSeed> assignedURLs = frontier.getNextURLs(limit);
                if (assignedURLs.isEmpty()) {
                    ProcessUtils.threadSleep(interval);
                    continue;
                }
                for (UrlSeed curURL : assignedURLs) {
                    JsoupUtils.doGet(crawlerFunctions.get(curURL.getCrawler()), curURL);
                }
            }
        }, "Crawler " + i).start();
        log.info("Crawler {} started", i);
    }

    public void register() {
        Map<String, ClrCrawlerEntity> crawlers = crawlerService.getCrawlerList().stream()
                .collect(Collectors.toMap(ClrCrawlerEntity::getCode, c -> {
                    c.setStatus(false);
                    return c;
                }));
        this.crawlerFunctions.values().stream()
                .map(c -> c.getClass().getAnnotation(HtmlCrawler.class))
                .filter(ObjectUtils::isNotEmpty)
                .forEach(c -> {
                    ClrCrawlerEntity crawler = crawlers.get(c.value());
                    if (crawler == null) {
                        crawler = new ClrCrawlerEntity();
                        crawler.setCode(c.value());
                    }
                    crawler.setName(c.tag());
                    crawler.setUrl(c.url());
                    crawler.setStatus(true);
                    crawlers.put(c.value(), crawler);
                });
        crawlerService.batchInsert(new ArrayList<>(crawlers.values()));
    }

}

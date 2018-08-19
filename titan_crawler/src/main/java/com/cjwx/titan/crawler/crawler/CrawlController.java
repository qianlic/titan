package com.cjwx.titan.crawler.crawler;

import com.cjwx.titan.crawler.WebCrawler;
import com.cjwx.titan.crawler.WebCrawlerFactory;
import com.cjwx.titan.crawler.fetcher.PageFetcher;
import com.cjwx.titan.crawler.frontier.Frontier;
import com.cjwx.titan.crawler.robotstxt.RobotstxtConfig;
import com.cjwx.titan.crawler.robotstxt.RobotstxtServer;
import com.cjwx.titan.crawler.url.URLCanonicalizer;
import com.cjwx.titan.crawler.url.WebURL;
import com.cjwx.titan.crawler.util.ThreadUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class CrawlController extends Configurable {

    private List<WebCrawler> crawlers;
    private WebCrawlerFactory<?> crawlerFactory;

    protected PageFetcher pageFetcher;
    protected RobotstxtServer robotstxtServer;
    protected Frontier frontier;

    protected boolean finished = false;
    protected boolean shuttingDown = false;

    protected final Object waitingLock = new Object();

    public CrawlController(CrawlConfig config) {
        super(config);

        frontier = new Frontier(config);
        pageFetcher = new PageFetcher(config);
        robotstxtServer = new RobotstxtServer(new RobotstxtConfig(), pageFetcher);
    }

    public <T extends WebCrawler> void start(final Class<T> _c, final int numberOfCrawlers) {
        crawlerFactory = new DefaultWebCrawlerFactory<>(_c);
        this.start(numberOfCrawlers);
    }

    protected void start(final int numberOfCrawlers) {
        try {
            finished = false;
            crawlers = new ArrayList<>();
            try {
                //启动crawler线程，数量numberOfCrawlers
                for (int i = 1; i <= numberOfCrawlers; i++) {
                    createCrawlerThread(i);
                }
                Thread monitorThread = new Thread(new CrawlMonitor(this));
                monitorThread.start();
            } catch (Exception e) {
                log.error("Unexpected Error", e);
            }
            waitUntilFinish();
        } catch (Exception e) {
            log.error("Error happened", e);
        }
    }
    //关闭
    public void close() {
        log.info("All of the crawlers are stopped. Finishing the process...");
        frontier.finish();
        log.info("Waiting for 10 seconds before final clean up...");
        ThreadUtil.sleep(10);
        pageFetcher.shutDown();
        finished = true;
        waitingLock.notifyAll();
    }
    //强关中断
    public void shutdown() {
        log.info("Shutting down...");
        this.shuttingDown = true;
        pageFetcher.shutDown();
        frontier.finish();
    }

    //新增url
    public void addSeed(String pageUrl) {
        this.addSeed(pageUrl, -1);
    }

    public void addSeed(String pageUrl, int docId) {
        String canonicalUrl = URLCanonicalizer.getCanonicalURL(pageUrl);
        if (canonicalUrl == null) {
            log.error("Invalid seed URL: {}", pageUrl);
        } else {
            WebURL webUrl = new WebURL();
            webUrl.setURL(canonicalUrl);
            webUrl.setDocid(docId);
            webUrl.setDepth((short) 0);
            this.addSeed(webUrl);
        }
    }

    public void addSeed(WebURL webUrl) {
        if (robotstxtServer.allows(webUrl)) {
            frontier.schedule(webUrl);
        } else {
            log.warn("Robots.txt does not allow this seed: {}", webUrl.getUrl());
        }
    }

    public void waitUntilFinish() {
        while (!finished) {
            synchronized (waitingLock) {
                if (finished) {
                    return;
                }
                try {
                    waitingLock.wait();
                    log.info("Shutting down success...");
                } catch (InterruptedException e) {
                    log.error("Error occurred", e);
                }
            }
        }
    }

    public List<WebCrawler> removeCrawler(int i) {
        crawlers.remove(i);
        return crawlers;
    }

    public WebCrawler createCrawlerThread(int i) throws Exception {
        WebCrawler crawler = crawlerFactory.newInstance();
        Thread thread = new Thread(crawler, "Crawler " + i);
        crawler.setThread(thread);
        crawler.init(i, this);
        thread.start();
        crawlers.add(crawler);
        log.info("Crawler {} started", i);
        return crawler;
    }

}

package com.cjwx.titan.crawler.crawler;

import com.cjwx.titan.crawler.WebCrawler;
import com.cjwx.titan.crawler.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CrawlMonitor implements Runnable {

    protected CrawlController controller;

    public CrawlMonitor(CrawlController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            synchronized (controller.getWaitingLock()) {
                while (true) {
                    ThreadUtil.sleep(10);
                    boolean someoneIsWorking = false;
                    List<WebCrawler> crawlers = controller.getCrawlers();
                    for (int i = 0; i < crawlers.size(); i++) {
                        Thread thread = crawlers.get(i).getThread();
                        if (!thread.isAlive()) {
                            if (!controller.isShuttingDown()) {
                                log.info("Thread {} was dead, I'll recreate it", crawlers.get(i).getMyId());
                                controller.removeCrawler(i);
                                controller.createCrawlerThread(i + 1);
                            }
                        } else if (crawlers.get(i).isNotWaitingForNewURLs()) {
                            someoneIsWorking = true;
                        }
                    }
                    boolean shut_on_empty = controller.getConfig().isShutdownOnEmptyQueue();
                    if (!someoneIsWorking && shut_on_empty) {
                        if (!controller.isShuttingDown()) {
                            log.info("It looks like no thread is working, waiting for 10 seconds to make sure...");
                            ThreadUtil.sleep(10);
                            for (int i = 0; i < crawlers.size(); i++) {
                                Thread thread = crawlers.get(i).getThread();
                                if (thread.isAlive() && crawlers.get(i).isNotWaitingForNewURLs()) {
                                    someoneIsWorking = true;
                                }
                            }
                        }
                        if (!someoneIsWorking) {
                            if (!controller.isShuttingDown()) {
                                long queueLength = controller.getFrontier().getQueueLength();
                                if (queueLength > 0) {
                                    continue;
                                }
                                log.info("No thread is working and no more URLs are in queue waiting for another 10 seconds to make sure...");
                                ThreadUtil.sleep(10);
                                queueLength = controller.getFrontier().getQueueLength();
                                if (queueLength > 0) {
                                    continue;
                                }
                            }
                            controller.close();
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Unexpected Error", e);
        }
    }

}

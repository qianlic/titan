package com.cjwx.titan.crawler.frontier;

import com.cjwx.titan.crawler.crawler.Configurable;
import com.cjwx.titan.crawler.crawler.CrawlConfig;
import com.cjwx.titan.crawler.url.WebURL;
import com.cjwx.titan.engine.reids.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 数据存储
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Slf4j
public class Frontier extends Configurable {

    private static String WORK_QUEUES = "PendingURLsDB";
    private static String WORK_HISTORIES = "HistoryURLsDB";

    protected boolean isFinished = false;
    protected long scheduledPages = 0;

    //线程同步锁
    protected final Object mutex = new Object();
    protected final Object waitingList = new Object();

    public Frontier(CrawlConfig config) {
        super(config);
        if (!config.isResumableCrawling()) {
            empty();
        }
    }

    public long getQueueLength() {
        return RedisUtils.sizeList(WORK_QUEUES);
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void empty() {
        synchronized (mutex) {
            RedisUtils.remove(WORK_QUEUES);
            RedisUtils.remove(WORK_HISTORIES);
        }
    }

    public void finish() {
        isFinished = true;
        synchronized (waitingList) {
            waitingList.notifyAll();
        }
    }

    public List<WebURL> getNextURLs(int max) {
        while (true) {
            synchronized (mutex) {
                if (isFinished) return Arrays.asList();
                if (RedisUtils.exists(WORK_QUEUES)) {
                    try {
                        List<Object> result = RedisUtils.getList(WORK_QUEUES, 0, max);
                        if (result.size() > 0) {
                            RedisUtils.remove(WORK_QUEUES, max);
                            return result.stream().map(url -> (WebURL) url).collect(Collectors.toList());
                        }
                    } catch (Exception e) {
                        log.error("Error while getting next urls", e);
                    }
                }
            }
            try {
                synchronized (waitingList) {
                    waitingList.wait();
                }
            } catch (InterruptedException ignored) {
            }
            if (isFinished) return Arrays.asList();
        }
    }

    public void scheduleAll(List<WebURL> urls) {
        synchronized (mutex) {
            urls.stream().forEach(this::saveUrl);
            synchronized (waitingList) {
                waitingList.notifyAll();
            }
        }
    }

    public void schedule(WebURL url) {
        synchronized (mutex) {
            saveUrl(url);
        }
    }

    private void saveUrl(WebURL url) {
        try {
            if (RedisUtils.exists(WORK_HISTORIES, url.getUrl())) {
                return;
            }
            RedisUtils.setList(WORK_QUEUES, url);
            RedisUtils.setHash(WORK_HISTORIES, url.getUrl(), url);
            scheduledPages++;
        } catch (Exception e) {
            log.error("Error while putting the url in the work queue", e);
        }
    }

}

package com.cjwx.spark.crawler.crawler.schedule;

import com.cjwx.spark.engine.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 数据存储
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Slf4j
public class Frontier {

    private static String WORK_QUEUES = "PendingURLsDB";
    private static String WORK_HISTORIES = "HistoryURLsDB";

    protected boolean isFinished = false;
    protected long scheduledPages = 0;

    //线程同步锁
    protected final Object mutex = new Object();
    protected final Object waitingList = new Object();

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

    public List<UrlSeed> getNextURLs(int max) {
        while (true) {
            synchronized (mutex) {
                if (isFinished) return Collections.emptyList();
                if (RedisUtils.exists(WORK_QUEUES) && max > 0) {
                    try {
                        List<Object> result = RedisUtils.getList(WORK_QUEUES, 0, max - 1);
                        if (result.size() > 0) {
                            RedisUtils.remove(WORK_QUEUES, max);
                            return result.stream()
                                    .filter(url -> url instanceof UrlSeed)
                                    .map(url -> (UrlSeed) url)
                                    .collect(Collectors.toList());
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
            if (isFinished) return Collections.emptyList();
        }
    }

    public void scheduleAll(List<UrlSeed> urls) {
        synchronized (mutex) {
            urls.forEach(this::saveUrl);
            synchronized (waitingList) {
                waitingList.notifyAll();
            }
        }
    }

    public void schedule(UrlSeed url) {
        synchronized (mutex) {
            saveUrl(url);
        }
    }

    private void saveUrl(UrlSeed url) {
        try {
            if (url.isHistory()) {
                if (RedisUtils.exists(WORK_HISTORIES, url.getUrl())) {
                    return;
                }
                RedisUtils.setHash(WORK_HISTORIES, url.getUrl(), url);
            }
            RedisUtils.setList(WORK_QUEUES, url);
            scheduledPages++;
        } catch (Exception e) {
            log.error("Error while putting the url in the work queue", e);
        }
    }
}

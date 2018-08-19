package com.cjwx.titan.crawler;

import com.cjwx.titan.crawler.crawler.CrawlController;
import com.cjwx.titan.crawler.crawler.exceptions.NotAllowedContentException;
import com.cjwx.titan.crawler.crawler.exceptions.PageBiggerThanMaxSizeException;
import com.cjwx.titan.crawler.crawler.exceptions.ParseException;
import com.cjwx.titan.crawler.crawler.exceptions.RedirectException;
import com.cjwx.titan.crawler.fetcher.Page;
import com.cjwx.titan.crawler.fetcher.PageFetchResult;
import com.cjwx.titan.crawler.parser.ParseData;
import com.cjwx.titan.crawler.parser.Parser;
import com.cjwx.titan.crawler.url.WebURL;
import com.cjwx.titan.crawler.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import uk.org.lidalia.slf4jext.Level;

import java.util.ArrayList;
import java.util.List;

@Slf4j
abstract public class WebCrawler implements Runnable {

    protected int myId;
    protected CrawlController myController;
    private Thread myThread;
    private Parser parser;
    private boolean isWaitingForNewURLs;

    public void init(int id, CrawlController crawlController) {
        this.myId = id;
        this.parser = new Parser(crawlController.getConfig());
        this.myController = crawlController;
        this.isWaitingForNewURLs = false;
    }

    //根据url进行网页的解析，对返回为TRUE的网页进行抓取。
    abstract public boolean shouldVisit(Page referringPage, WebURL url);

    //解析网页内容，利用page方法得到网页的内容和属性
    abstract public void visit(Page page);

    @Override
    public void run() {
        while (true) {
            isWaitingForNewURLs = true;
            List<WebURL> assignedURLs = myController.getFrontier().getNextURLs(50);
            isWaitingForNewURLs = false;
            if (assignedURLs.isEmpty()) {
                if (myController.getFrontier().isFinished()) return;
                ThreadUtil.sleep(3);
            } else {
                for (WebURL curURL : assignedURLs) {
                    System.out.println(myThread.getName() + myThread.isInterrupted());
                    if (myController.isShuttingDown()) {
                        log.info("Exiting because of handler shutdown.");
                        return;
                    } else if (curURL != null) {
                        processPage(curURL);
                    }
                }
            }
        }
    }

    //页面处理
    private void processPage(WebURL curURL) {
        PageFetchResult fetchResult = null;
        try {
            if (curURL == null) {
                throw new Exception("Failed processing a NULL url !?");
            }
            fetchResult = myController.getPageFetcher().fetchPage(curURL);
            int statusCode = fetchResult.getStatusCode();
            Page page = new Page(curURL);
            page.setFetchResponseHeaders(fetchResult.getResponseHeaders());
            page.setStatusCode(statusCode);
            if (statusCode < 200 || statusCode > 299) {
                if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
                        || statusCode == HttpStatus.SC_MOVED_TEMPORARILY
                        || statusCode == HttpStatus.SC_MULTIPLE_CHOICES
                        || statusCode == HttpStatus.SC_SEE_OTHER
                        || statusCode == HttpStatus.SC_TEMPORARY_REDIRECT
                        || statusCode == 308) {
                    page.setRedirect(true);
                    if (myController.getConfig().isFollowRedirects()) {
                        String movedToUrl = fetchResult.getMovedToUrl();
                        if (movedToUrl == null) {
                            throw new RedirectException(Level.WARN, "Unexpected error, URL: " + curURL + " is redirected to NOTHING");
                        }
                        page.setRedirectedToUrl(movedToUrl);
                        WebURL webURL = new WebURL();
                        webURL.setURL(movedToUrl);
                        webURL.setParentDocid(curURL.getParentDocid());
                        webURL.setParentUrl(curURL.getParentUrl());
                        webURL.setDepth(curURL.getDepth());
                        webURL.setDocid(-1);
                        if (shouldVisit(page, webURL)) {
                            myController.addSeed(webURL);
                        } else {
                            log.debug("Not visiting: {} as per your \"shouldVisit\" policy", webURL.getUrl());
                        }
                    }
                } else {
                    log.warn("Skipping URL: {}, StatusCode: {}", fetchResult.getFetchedUrl(), statusCode);
                }
            } else if (!fetchResult.fetchContent(page)) {
                log.warn("Can't fetch content of: {}", curURL.getUrl());
            } else {
                parser.parse(page, curURL.getUrl());
                ParseData parseData = page.getParseData();
                List<WebURL> toSchedule = new ArrayList<>();
                int maxCrawlDepth = myController.getConfig().getMaxDepthOfCrawling();
                for (WebURL webURL : parseData.getOutgoingUrls()) {
                    webURL.setParentDocid(curURL.getDocid());
                    webURL.setParentUrl(curURL.getUrl());
                    webURL.setDocid(-1);
                    webURL.setDepth((short) (curURL.getDepth() + 1));
                    if ((maxCrawlDepth == -1) || (curURL.getDepth() < maxCrawlDepth)) {
                        if (shouldVisit(page, webURL)) {
                            myController.addSeed(webURL);
                        } else {
                            log.debug("Not visiting: {} as per your \"shouldVisit\" policy", webURL.getUrl());
                        }
                    }
                }
                myController.getFrontier().scheduleAll(toSchedule);
                visit(page);
            }
        } catch (PageBiggerThanMaxSizeException e) {
            log.warn("Skipping a URL: {} which was bigger ( {} ) than max allowed size", curURL.getUrl(), e.getPageSize());
        } catch (ParseException pe) {
            log.warn("Parsing error of: {}", curURL.getUrl());
        } catch (RedirectException re) {
            log.info(re.level.name(), re);
        } catch (NotAllowedContentException nace) {
            log.debug("Skipping: {} as it contains binary content which you configured not to crawl", curURL.getUrl());
        } catch (Exception e) {
            String urlStr = (curURL == null ? "NULL" : curURL.getUrl());
            log.warn("Unhandled exception while fetching {}: {}", urlStr, e.getMessage());
            log.info("Stacktrace: ", e);
        } finally {
            if (fetchResult != null) {
                fetchResult.discardContentIfNotConsumed();
            }
        }
    }

    public Thread getThread() {
        return myThread;
    }

    public void setThread(Thread myThread) {
        this.myThread = myThread;
    }

    public int getMyId() {
        return myId;
    }

    public boolean isNotWaitingForNewURLs() {
        return !isWaitingForNewURLs;
    }
}

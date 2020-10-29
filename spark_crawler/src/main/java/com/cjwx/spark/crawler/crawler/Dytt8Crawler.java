package com.cjwx.spark.crawler.crawler;

import com.cjwx.spark.crawler.crawler.schedule.CrawlerFunction;
import com.cjwx.spark.crawler.crawler.schedule.CrawlerScheduler;
import com.cjwx.spark.crawler.crawler.schedule.HtmlCrawler;
import com.cjwx.spark.crawler.crawler.schedule.UrlSeed;
import com.cjwx.spark.crawler.entity.ClrWebUrl;
import com.cjwx.spark.crawler.repository.WebUrlRepository;
import com.cjwx.spark.engine.util.StringUtils;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Description: 电影天堂
 * @Author: qian li
 * @Date: 2018年11月14日 16:01
 */
@Transactional
@HtmlCrawler(value = "dytt8Crawler", tag = "电影天堂", url = "https://www.dytt8.net/")
public class Dytt8Crawler implements CrawlerFunction {

    @Autowired
    private WebUrlRepository webUrlRepository;
    @Autowired
    private CrawlerScheduler crawlerScheduler;

    @Override
    public void process(UrlSeed seed, Document document) {
        switch (seed.getType()) {
            case 2:
                getMovieLink(document);
                break;
            case 1:
                getMovie(document);
                break;
            default:
                getPage(document);
        }
    }

    /**
     * 获取目录信息
     */
    public void getMenu(Document document) {
        List<UrlSeed> urls = document.select("#menu a").stream()
                .filter(a -> !"#".equals(a.attr("href")) && !"APP下载".equals(a.text()))
                .map(e -> new UrlSeed("dytt8Crawler", e.absUrl("href"), true))
                .collect(Collectors.toList());
        crawlerScheduler.schedule(urls);
    }

    /**
     * 获取翻页信息
     */
    public void getPage(Document document) {
        getMenu(document);
        List<UrlSeed> urls = document.select("select[name='sldd'] option").stream()
                .map(e -> new UrlSeed("dytt8Crawler", 1, e.absUrl("value")))
                .collect(Collectors.toList());
        crawlerScheduler.schedule(urls);
    }

    /**
     * 获取影视信息
     */
    public void getMovie(Document document) {
        List<UrlSeed> urls = document.select(".co_content8 .ulink").stream()
                .map(e -> new UrlSeed("dytt8Crawler", 2, e.absUrl("href")))
                .collect(Collectors.toList());
        crawlerScheduler.schedule(urls);
    }

    /**
     * 保存链接
     */
    public void getMovieLink(Document document) {
        document.select("#Zoom table a").stream().forEach(e -> {
            ClrWebUrl weburl = new ClrWebUrl();
            weburl.setDomain(document.baseUri());
            weburl.setAnchor(document.title());
            weburl.setTag(e.text());
            weburl.setUrl(e.absUrl("href"));
            webUrlRepository.insert(weburl);
        });
    }

    public static void main(String[] args) {
        String str = "2013年动作《金刚王：死亡救赎》720p.BD中字迅雷下载_阳光电影";
        String s = "《", e = "》";
        System.out.println(StringUtils.getAttr(str, s, e));
        for (int i = 0; i < 100; i++) {
            System.out.println(String.format("%017d", Math.abs(UUID.randomUUID().hashCode())));
        }
    }

}

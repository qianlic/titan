package com.cjwx.titan.crawler;

import com.cjwx.titan.crawler.bean.ClrPageBean;
import com.cjwx.titan.crawler.bean.ClrWebUrlBean;
import com.cjwx.titan.crawler.fetcher.Page;
import com.cjwx.titan.crawler.handler.WebUrlHandler;
import com.cjwx.titan.crawler.url.WebURL;
import com.cjwx.titan.engine.core.helper.ApplicationContextHelper;
import org.springframework.beans.BeanUtils;

import java.util.regex.Pattern;

public class BasicCrawler extends WebCrawler {

    private static final Pattern filters = Pattern.compile(".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    //根据url进行网页的解析，对返回为TRUE的网页进行抓取。
    @Override
    public boolean shouldVisit(Page referringPage, WebURL weburl) {
        String domain = "http://" + referringPage.getWebURL().getSubDomain() + "." + referringPage.getWebURL().getDomain().toLowerCase();
        String href = weburl.getUrl().toLowerCase();

        if (filters.matcher(href).matches()) {
            return false;
        }
        /*else if (!href.startsWith(domain)) {
            return false;
        }*/
        return true;
    }

    //解析网页内容，利用page方法得到网页的内容和属性
    @Override
    public void visit(Page page) {
        WebURL webURL = page.getWebURL();
        ClrPageBean pb = new ClrPageBean();
        BeanUtils.copyProperties(page,pb);
        pb.setContent(page.getContentData());
        ClrWebUrlBean wu = new ClrWebUrlBean();
        BeanUtils.copyProperties(webURL,wu);
        pb.setWebURL(wu);
        WebUrlHandler handler = (WebUrlHandler) ApplicationContextHelper.getBean("webUrlHandler");
        handler.create(pb);
    }

}

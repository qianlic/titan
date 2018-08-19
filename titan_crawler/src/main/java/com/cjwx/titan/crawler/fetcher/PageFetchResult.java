package com.cjwx.titan.crawler.fetcher;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Data
@Slf4j
public class PageFetchResult {

    protected int statusCode;
    protected HttpEntity entity = null;
    protected Header[] responseHeaders = null;
    protected String fetchedUrl = null;
    protected String movedToUrl = null;

    public boolean fetchContent(Page page) {
        try {
            page.load(entity);
            page.setFetchResponseHeaders(responseHeaders);
            return true;
        } catch (Exception e) {
            log.info("Exception while fetching content for: {} [{}]", page.getWebURL().getUrl(), e.getMessage());
        }
        return false;
    }

    public void discardContentIfNotConsumed() {
        try {
            if (entity != null) {
                EntityUtils.consume(entity);
            }
        } catch (IOException ignored) {
        } catch (Exception e) {
            log.warn("Unexpected error occurred while trying to discard content", e);
        }
    }

}

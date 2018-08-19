package com.cjwx.titan.crawler.fetcher;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import com.cjwx.titan.crawler.parser.ParseData;
import com.cjwx.titan.crawler.url.WebURL;

@Data
@Slf4j
public class Page {

    protected WebURL webURL;

    protected boolean redirect;

    protected String redirectedToUrl;

    protected int statusCode;

    protected byte[] contentData;

    protected String contentType;

    protected String contentEncoding;

    protected String contentCharset;

    private String language;

    protected Header[] fetchResponseHeaders;

    protected ParseData parseData;

    public Page(WebURL url) {
        this.webURL = url;
    }

    public void load(HttpEntity entity) throws Exception {
        Header type = entity.getContentType();
        if (type != null) {
            contentType = type.getValue();
        }
        Header encoding = entity.getContentEncoding();
        if (encoding != null) {
            contentEncoding = encoding.getValue();
        }
        Charset charset = ContentType.getOrDefault(entity).getCharset();
        if (charset != null) {
            contentCharset = charset.displayName();
        }
        contentData = EntityUtils.toByteArray(entity);
    }

    public String getContent() {
        if (contentCharset != null) {
            try {
                return new String(contentData, contentCharset);
            } catch (UnsupportedEncodingException e) {
                log.debug("请求 content 转码失败：", e);
            }
        }
        return new String(contentData);
    }

}

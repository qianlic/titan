package com.cjwx.titan.crawler.parser;

import java.util.Map;
import java.util.Set;

import com.cjwx.titan.crawler.url.WebURL;
import lombok.Data;

@Data
public class HtmlParseData implements ParseData {

    private String html;
    private String text;
    private String title;
    private Map<String, String> metaTags;

    private Set<WebURL> outgoingUrls;

    @Override
    public String toString() {
        return text;
    }

}

package com.cjwx.titan.crawler.parser;

import lombok.Data;

@Data
public class ExtractedUrlAnchorPair {

    private String href;
    private String anchor;
    private String tag;

}

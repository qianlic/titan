package com.cjwx.spark.crawler.entity;

import com.cjwx.spark.engine.core.entity.BaseEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by CJWX on 2016/4/4.
 */
@Slf4j
@Data
public class ClrPage extends BaseEntity {

    private boolean redirect;
    private String redirectedToUrl;

    private int statusCode;
    private String contentType;
    private String contentEncoding;
    private String contentCharset;
    private String language;
    private byte[] content;
    private long urlId;

    private ClrWebUrl webURL;

}

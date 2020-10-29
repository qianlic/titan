package com.cjwx.spark.crawler.dto;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

/**
 * Created by CJWX on 2016/4/4.
 */
@Slf4j
@Data
public class ClrPageDTO extends BaseDTO {

    private boolean redirect;
    private String redirectedToUrl;

    private int statusCode;
    private String contentType;
    private String contentEncoding;
    private String contentCharset;
    private String language;
    private byte[] content;
    private long urlId;

    private ClrWebUrlDTO webURL;

    public String getContent() {
        if (contentCharset != null) {
            try {
                return new String(content, contentCharset);
            } catch (UnsupportedEncodingException e) {
                log.debug("请求 content 转码失败：", e);
            }
        }
        return new String(content);
    }

}

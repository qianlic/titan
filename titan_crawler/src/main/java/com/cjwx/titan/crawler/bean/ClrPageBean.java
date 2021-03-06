package com.cjwx.titan.crawler.bean;

import com.cjwx.titan.engine.core.base.bean.AbstractBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.UnsupportedEncodingException;

/**
 * Created by CJWX on 2016/4/4.
 */
@Slf4j
@Data
@Entity
@Table(name = "clr_page")
public class ClrPageBean extends AbstractBean {

    private boolean redirect;
    private String redirectedToUrl;

    private int statusCode;
    private String contentType;
    private String contentEncoding;
    private String contentCharset;
    private String language;
    private byte[] content;
    private long urlId;

    @Transient
    private ClrWebUrlBean webURL;

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

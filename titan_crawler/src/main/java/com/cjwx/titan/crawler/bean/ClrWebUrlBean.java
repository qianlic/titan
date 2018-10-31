package com.cjwx.titan.crawler.bean;

import com.cjwx.titan.engine.core.base.bean.AbstractBean;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CJWX on 2016/4/4.
 */
@Data
@Entity
@Table(name = "clr_weburl")
public class ClrWebUrlBean extends AbstractBean {

    private String subDomain;
    private String domain;

    private short depth;
    private String path;

    private String url;
    private String parentUrl;

    private String anchor;
    private String tag;

}

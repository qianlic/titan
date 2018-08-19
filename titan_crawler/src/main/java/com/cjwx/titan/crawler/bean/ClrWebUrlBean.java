package com.cjwx.titan.crawler.bean;

import com.cjwx.titan.engine.core.base.dao.AbstractBean;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by CJWX on 2016/4/4.
 */
@Data
@Entity
@Table(name = ClrWebUrlBean.TABLE)
public class ClrWebUrlBean extends AbstractBean {

    @Transient
    public static final String TABLE = "clr_weburl";

    private String subDomain;
    private String domain;

    private short depth;
    private String path;

    private String url;
    private String parentUrl;

    private String anchor;
    private String tag;

}

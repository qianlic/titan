package com.cjwx.titan.crawler.bean;

import com.cjwx.titan.engine.core.base.dao.AbstractBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * ClrCrawlerBean
 *
 * @author Qian Li
 * @date 2016/6/2
 */
@Data
@Entity
@Table(name = ClrCrawlerBean.TABLE)
public class ClrCrawlerBean extends AbstractBean {

    @Transient
    public static final String TABLE = "clr_crawler";

    @Column(name = "name")
    private String name;
    @Column(name = "resumable")
    private boolean resumable;
    @Column(name = "seed")
    private String seed;
    @Column(name = "number")
    private int number;

}

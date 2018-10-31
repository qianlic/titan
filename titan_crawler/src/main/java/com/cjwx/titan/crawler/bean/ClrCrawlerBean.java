package com.cjwx.titan.crawler.bean;

import com.cjwx.titan.engine.core.base.bean.AbstractBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ClrCrawlerBean
 *
 * @author Qian Li
 * @date 2016/6/2
 */
@Data
@Entity
@Table(name = "clr_crawler")
public class ClrCrawlerBean extends AbstractBean {

    @Column(name = "name")
    private String name;
    @Column(name = "resumable")
    private boolean resumable;
    @Column(name = "seed")
    private String seed;
    @Column(name = "number")
    private int number;

}

package com.cjwx.spark.crawler.entity;

import com.cjwx.spark.engine.entity.AbstractEntity;
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
public class ClrCrawlerEntity extends AbstractEntity {

    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;

}

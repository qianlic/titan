package com.cjwx.spark.crawler.entity;

import com.cjwx.spark.engine.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CJWX on 2016/4/4.
 */
@Data
@Entity
@Table(name = "clr_weburl")
public class ClrWebUrlEntity extends AbstractEntity {

    private String domain;

    private short depth;
    private String path;

    private String url;

    private String anchor;
    private String tag;

}

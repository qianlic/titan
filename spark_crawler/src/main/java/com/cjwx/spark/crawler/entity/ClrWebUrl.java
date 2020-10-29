package com.cjwx.spark.crawler.entity;

import com.cjwx.spark.engine.core.entity.BaseEntity;
import lombok.Data;

/**
 * Created by CJWX on 2016/4/4.
 */
@Data
public class ClrWebUrl extends BaseEntity {

    private String domain;

    private short depth;

    private String path;

    private String url;

    private String anchor;

    private String tag;

}

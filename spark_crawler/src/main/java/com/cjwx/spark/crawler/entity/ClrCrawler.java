package com.cjwx.spark.crawler.entity;

import com.cjwx.spark.engine.core.entity.BaseEntity;
import lombok.Data;

/**
 * ClrCrawlerBean
 *
 * @author Qian Li
 * @date 2016/6/2
 */
@Data
public class ClrCrawler extends BaseEntity {

    private String code;

    private String name;

    private String url;

}

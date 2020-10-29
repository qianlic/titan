package com.cjwx.spark.crawler.dto;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import lombok.Data;

/**
 * Created by CJWX on 2016/4/4.
 */
@Data
public class ClrWebUrlDTO extends BaseDTO {

    private String domain;

    private short depth;
    private String path;

    private String url;

    private String anchor;
    private String tag;

}

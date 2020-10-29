package com.cjwx.spark.crawler.dto;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import lombok.Data;

/**
 * ClrCrawlerBean
 *
 * @author Qian Li
 * @date 2016/6/2
 */
@Data
public class ClrCrawlerDTO extends BaseDTO {

    private String code;

    private String name;

    private String url;

}

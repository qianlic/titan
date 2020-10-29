package com.cjwx.spark.server.dto;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import lombok.Data;

@Data
public class ComImageDTO extends BaseDTO {

    private String filename;

    private String storeName;

    private String hash;

    private String url;

    private String width;

    private String height;

}

package com.cjwx.spark.server.entity;

import com.cjwx.spark.engine.core.entity.BaseEntity;
import lombok.Data;

@Data
public class ComImage extends BaseEntity {

    private String filename;

    private String storeName;

    private String hash;

    private String url;

    private String width;

    private String height;

    private String size;

}

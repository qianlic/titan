package com.cjwx.spark.server.entity;

import com.cjwx.spark.engine.entity.AbstractEntity;
import lombok.Data;

@Data
public class ComImage extends AbstractEntity {

    private String filename;

    private String storename;

    private String hash;

    private String url;

    private String width;

    private String height;

    private String size;

}

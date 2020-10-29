package com.cjwx.spark.server.entity;

import com.cjwx.spark.engine.core.entity.BaseEntity;
import lombok.Data;

@Data
public class ComArticle extends BaseEntity {

    private String title;

    private String tags;

    private String content;

    private String author;

}

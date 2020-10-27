package com.cjwx.spark.server.entity;

import com.cjwx.spark.engine.entity.AbstractEntity;
import lombok.Data;

@Data
public class ComArticle extends AbstractEntity {

    private String title;

    private String tags;

    private String content;

    private String author;

}

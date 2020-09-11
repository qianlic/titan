package com.cjwx.spark.server.entity;

import com.cjwx.spark.engine.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "com_article")
public class ComArticleEntity extends AbstractEntity {

    @Column(name = "title")
    private String title;
    @Column(name = "tags")
    private String tags;
    @Column(name = "content")
    private String content;
    @Column(name = "author")
    private String author;

}

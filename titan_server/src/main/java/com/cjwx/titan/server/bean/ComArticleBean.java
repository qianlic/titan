package com.cjwx.titan.server.bean;

import com.cjwx.titan.engine.core.base.dao.AbstractBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "com_article")
public class ComArticleBean extends AbstractBean {

    @Column(name = "title")
    private String title;
    @Column(name = "tags")
    private String tags;
    @Column(name = "content")
    private String content;
    @Column(name = "author")
    private String author;

}

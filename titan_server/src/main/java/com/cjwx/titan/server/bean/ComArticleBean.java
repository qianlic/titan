package com.cjwx.titan.server.bean;

import com.cjwx.titan.engine.core.base.dao.AbstractBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name = ComArticleBean.TABLE)
public class ComArticleBean extends AbstractBean {

    @Transient
    public static final String TABLE = "com_article";

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "author")
    private String author;

}

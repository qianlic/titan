package com.cjwx.titan.server.bean;

import com.cjwx.titan.engine.core.base.dao.AbstractBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name = ComImageBean.TABLE)
public class ComImageBean extends AbstractBean {

    @Transient
    public static final String TABLE = "com_image";

    @Column(name = "filename")
    private String filename;
    @Column(name = "storename")
    private String storename;
    @Column(name = "hash")
    private String hash;
    @Column(name = "url")
    private String url;
    @Column(name = "width")
    private String width;
    @Column(name = "height")
    private String height;
    @Column(name = "size")
    private String size;

}

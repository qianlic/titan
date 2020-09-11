package com.cjwx.spark.server.entity;

import com.cjwx.spark.engine.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "com_image")
public class ComImageEntity extends AbstractEntity {

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

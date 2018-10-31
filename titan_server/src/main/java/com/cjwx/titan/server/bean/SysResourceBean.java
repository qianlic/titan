package com.cjwx.titan.server.bean;

import com.cjwx.titan.engine.core.base.bean.ResourceBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CJWX on 2016/4/12.
 */
@Data
@Entity
@Table(name = "sys_resource")
public class SysResourceBean extends ResourceBean {

    @Column(name = "description")
    private String description;
    @Column(name = "url")
    private String url;
    @Column(name = "ico")
    private String ico;
    @Column(name = "level")
    private int level;

}

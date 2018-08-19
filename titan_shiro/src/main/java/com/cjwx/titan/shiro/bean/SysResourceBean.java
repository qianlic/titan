package com.cjwx.titan.shiro.bean;

import com.cjwx.titan.engine.core.base.bean.ResourceBean;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by CJWX on 2016/4/12.
 */
@Data
@Entity
@Table(name = SysResourceBean.TABLE)
public class SysResourceBean extends ResourceBean {

    @Transient
    public static final String TABLE = "sys_resource";

    @Column(name = "description")
    private String description;
    @Column(name = "url")
    private String url;
    @Column(name = "ico")
    private String ico;
    @Column(name = "level")
    private int level;

}

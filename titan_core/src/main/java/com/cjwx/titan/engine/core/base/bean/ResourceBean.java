package com.cjwx.titan.engine.core.base.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @Description: 资源
 * @Author: qian li
 * @Date: 2018年04月03日 15:44
 */
@Data
@MappedSuperclass
public class ResourceBean extends AbstractBean {

    @Column(name = "resourcecode")
    private String resourcecode;
    @Column(name = "resourcename")
    private String resourcename;
    @Column(name = "url")
    private String url;
    @Column(name = "type")
    private String type;
    @Column(name = "parentid")
    private long parentid;

}

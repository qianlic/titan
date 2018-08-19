package com.cjwx.titan.engine.core.base.bean;

import com.cjwx.titan.engine.core.base.dao.AbstractBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @Description: 角色
 * @Author: qian li
 * @Date: 2018年04月03日 15:41
 */
@Data
@MappedSuperclass
public class RoleBean extends AbstractBean {

    @Column(name = "rolecode")
    private String rolecode;
    @Column(name = "rolename")
    private String rolename;
    @Column(name = "resourceids")
    private String resourceids;

}

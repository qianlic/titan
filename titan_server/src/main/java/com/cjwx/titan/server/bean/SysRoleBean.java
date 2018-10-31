package com.cjwx.titan.server.bean;

import com.cjwx.titan.engine.core.base.bean.RoleBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CJWX on 2016/4/10.
 */
@Data
@Entity
@Table(name = "sys_role")
public class SysRoleBean extends RoleBean {

    @Column(name = "description")
    private String description;

}

package com.cjwx.titan.server.bean;

import com.cjwx.titan.engine.core.base.bean.RoleBean;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by CJWX on 2016/4/10.
 */
@Data
@Entity
@Table(name = SysRoleBean.TABLE)
public class SysRoleBean extends RoleBean {

    @Transient
    public static final String TABLE = "sys_role";

    @Column(name = "description")
    private String description;

}

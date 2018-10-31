package com.cjwx.titan.server.bean;

import com.cjwx.titan.engine.core.base.bean.UserBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by CJWX on 2016/4/10.
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUserBean extends UserBean {

    @Column(name = "sex")
    private String sex;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "email")
    private String email;
    @Column(name = "imgurl")
    private String imgurl;

    @Transient
    private List<SysRoleBean> roles;
    @Transient
    private List<SysResourceBean> resources;

}

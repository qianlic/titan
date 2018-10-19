package com.cjwx.titan.server.bean;

import com.cjwx.titan.engine.core.base.bean.UserBean;
import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Created by CJWX on 2016/4/10.
 */
@Data
@Entity
@Table(name = SysUserBean.TABLE)
public class SysUserBean extends UserBean {

    @Transient
    public static final String TABLE = "sys_user";

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

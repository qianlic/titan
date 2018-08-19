package com.cjwx.titan.engine.core.base.bean;

import com.cjwx.titan.engine.core.base.dao.AbstractBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @Description: 用户
 * @Author: qian li
 * @Date: 2018年04月03日 15:29
 */
@Data
@MappedSuperclass
public class UserBean extends AbstractBean {

    @Column(name = "usercode")
    private String usercode;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "salt")
    private String salt;
    @Column(name = "roleids")
    private String roleids;
    @Column(name = "lastlogintime")
    private Date lastlogintime;

}

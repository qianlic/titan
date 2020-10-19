package com.cjwx.spark.engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Description: 用户
 * @Author: qian li
 * @Date: 2018年04月03日 15:29
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUserEntity extends AbstractEntity {

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @Column(name = "salt")
    private String salt;

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

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(cascade = CascadeType.ALL)
    private List<SysRoleEntity> roles;

}

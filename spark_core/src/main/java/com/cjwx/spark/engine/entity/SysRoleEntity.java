package com.cjwx.spark.engine.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Description: 角色
 * @Author: qian li
 * @Date: 2018年04月03日 15:41
 */
@Data
@Entity
@Table(name = "sys_role")
public class SysRoleEntity extends AbstractEntity {

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;

    @JoinTable(name = "sys_role_resource",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id"))
    @ManyToMany(cascade = CascadeType.ALL)
    private List<SysResourceEntity> resources;

}

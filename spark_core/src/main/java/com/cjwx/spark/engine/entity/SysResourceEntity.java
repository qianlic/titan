package com.cjwx.spark.engine.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 资源
 * @Author: qian li
 * @Date: 2018年04月03日 15:44
 */
@Data
@Entity
@Table(name = "sys_resource")
public class SysResourceEntity extends AbstractEntity {

    @Column(name = "resource_code")
    private String resourceCode;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "url")
    private String url;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "ico")
    private String ico;

    @Column(name = "level")
    private int level;

    @Column(name = "parent_id")
    private int parentId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    @OrderBy("resourceCode asc")
    private List<SysResourceEntity> children = new ArrayList<>();

}

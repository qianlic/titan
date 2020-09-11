package com.cjwx.spark.engine.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 抽象bean
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 3587850931125773471L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "status")
    private Boolean status = true;

    @CreatedBy
    @Column(name = "create_user")
    private Long createUser;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedBy
    @Column(name = "update_user")
    private Long updateUser;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

}

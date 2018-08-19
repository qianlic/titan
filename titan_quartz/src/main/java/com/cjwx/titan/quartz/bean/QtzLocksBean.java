package com.cjwx.titan.quartz.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:47
 */
@Data
@Table(name="qtz_locks")
public class QtzLocksBean {

    @Column(name = "sched_name")
    private String schedName;
    @Column(name = "lock_name")
    private String lockName;

}

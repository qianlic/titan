package com.cjwx.titan.quartz.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:43
 */
@Data
@Table(name = "qtz_job_details")
public class QtzJobDetailsBean {

    @Column(name = "sched_name")
    private String schedName;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "job_group")
    private String jobGroup;
    @Column(name = "description")
    private String description;
    @Column(name = "job_class_name")
    private String jobClassName;
    @Column(name = "is_durable")
    private String isDurable;
    @Column(name = "is_nonconcurrent")
    private String isNonconcurrent;
    @Column(name = "is_update_data")
    private String isUpdateData;
    @Column(name = "requests_recovery")
    private String requestsRecovery;
    @Column(name = "job_data")
    private String jobData;

}

package com.cjwx.titan.quartz.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:36
 */
@Data
@Table(name="qtz_fired_triggers")
public class QtzFiredTriggersBean {

    @Column(name = "sched_name")
    private String schedName;
    @Column(name = "trigger_name")
    private String triggerName;
    @Column(name = "trigger_group")
    private String triggerGroup;
    @Column(name = "instance_name")
    private String instanceName;
    @Column(name = "fired_time")
    private String firedTime;
    @Column(name = "sched_time")
    private String schedTime;
    @Column(name = "priority")
    private String priority;
    @Column(name = "state")
    private String state;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "job_group")
    private String jobGroup;
    @Column(name = "is_nonconcurrent")
    private String isNonconcurrent;
    @Column(name = "requests_recovery")
    private String requestsRecovery;

}

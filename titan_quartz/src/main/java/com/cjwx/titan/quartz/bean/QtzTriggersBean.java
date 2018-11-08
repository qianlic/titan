package com.cjwx.titan.quartz.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 15:02
 */
@Data
@Table(name = "qtz_triggers")
public class QtzTriggersBean {

    @Column(name = "sched_name")
    private String schedName;
    @Column(name = "trigger_name")
    private String triggerName;
    @Column(name = "trigger_group")
    private String triggerGroup;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "job_group")
    private String jobGroup;
    @Column(name = "tags")
    private String description;
    @Column(name = "next_fire_time")
    private String nextFireTime;
    @Column(name = "prev_fire_time")
    private String prevFireTime;
    @Column(name = "priority")
    private int priority;
    @Column(name = "trigger_state")
    private String triggerState;
    @Column(name = "trigger_type")
    private String triggerType;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "calendar_name")
    private String calendarName;
    @Column(name = "misfire_instr")
    private String misfireInstr;
    @Column(name = "job_date")
    private String jobDate;

}

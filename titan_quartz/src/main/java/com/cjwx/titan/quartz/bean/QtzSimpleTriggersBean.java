package com.cjwx.titan.quartz.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:54
 */
@Data
@Table(name="qtz_simple_triggers")
public class QtzSimpleTriggersBean {

    @Column(name = "sched_name")
    private String schedName;
    @Column(name = "trigger_name")
    private String triggerName;
    @Column(name = "trigger_group")
    private String triggerGroup;
    @Column(name = "repeat_count")
    private String repeatCount;
    @Column(name = "repeat_interval")
    private String repeatInterval;
    @Column(name = "times_triggered")
    private String timesTriggered;

}

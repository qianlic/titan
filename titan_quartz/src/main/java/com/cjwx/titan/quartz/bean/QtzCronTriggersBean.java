package com.cjwx.titan.quartz.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:31
 */
@Data
@Table(name="qtz_cron_triggers")
public class QtzCronTriggersBean {

    @Column(name = "sched_name")
    private String schedName;
    @Column(name = "trigger_name")
    private String triggerName;
    @Column(name = "trigger_group")
    private String triggerGroup;
    @Column(name = "cron_expression")
    private String cronExpression;
    @Column(name = "time_zone_id")
    private String timeZoneId;

}

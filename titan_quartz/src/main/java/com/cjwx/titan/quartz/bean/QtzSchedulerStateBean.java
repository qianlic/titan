package com.cjwx.titan.quartz.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:51
 */
@Data
@Table(name="qtz_scheduler_state")
public class QtzSchedulerStateBean {

    @Column(name = "sched_name")
    private String schedName;
    @Column(name = "instance_name")
    private String instanceName;
    @Column(name = "last_checkin_time")
    private String lastCheckinTime;
    @Column(name = "checkin_interval")
    private String checkinInterval;

}

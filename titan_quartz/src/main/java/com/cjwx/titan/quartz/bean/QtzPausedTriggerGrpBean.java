package com.cjwx.titan.quartz.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:49
 */
@Data
@Table(name="qtz_paused_trigger_grps")
public class QtzPausedTriggerGrpBean {

    @Column(name = "sched_name")
    private String schedName;
    @Column(name = "trigger_group")
    private String triggerGroup;

}

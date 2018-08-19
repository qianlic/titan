package com.cjwx.titan.quartz.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:28
 */
@Data
@Table(name="qtz_calendars")
public class QtzCalendarsBean {

    @Column(name = "sched_name")
    private String schedName;
    @Column(name = "calendar_name")
    private String calendarName;
    @Column(name = "calendar")
    private String calendar;

}

package com.cjwx.titan.quartz.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月08日 14:58
 */
@Data
@Table(name="qtz_simprop_triggers")
public class QtzSimpropTriggersBean {

    @Column(name = "sched_name")
    private String schedName;
    @Column(name = "trigger_name")
    private String triggerName;
    @Column(name = "trigger_group")
    private String triggerGroup;
    @Column(name = "str_prop_1")
    private String strProp1;
    @Column(name = "str_prop_2")
    private String strProp2;
    @Column(name = "str_prop_3")
    private String strProp3;
    @Column(name = "int_prop_1")
    private String intProp1;
    @Column(name = "int_prop_2")
    private String intProp2;
    @Column(name = "long_prop_1")
    private String longProp1;
    @Column(name = "long_prop_2")
    private String longProp2;
    @Column(name = "dec_prop_1")
    private String decProp1;
    @Column(name = "dec_prop_2")
    private String decProp2;
    @Column(name = "bool_prop_1")
    private String boolProp1;
    @Column(name = "bool_prop_2")
    private String boolProp2;

}

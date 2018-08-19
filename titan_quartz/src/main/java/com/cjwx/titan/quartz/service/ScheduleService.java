package com.cjwx.titan.quartz.service;

import org.quartz.SchedulerException;

import java.util.List;

public interface ScheduleService {

    List<String> getJobGroupNames() throws SchedulerException;

    List<String> getTriggerGroupNames() throws SchedulerException;

    void start() throws SchedulerException;

    void shutdown() throws SchedulerException;

}
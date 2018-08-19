package com.cjwx.titan.quartz.service.impl;

import com.cjwx.titan.quartz.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 定时调度器服务
 * @Author: qian li
 * @Date: 2018年03月29日 19:09
 */
@Slf4j
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private Scheduler scheduler;

    /**
     * 获取任务组
     */
    @Override
    public List<String> getJobGroupNames() throws SchedulerException {
        return scheduler.getJobGroupNames();
    }

    /**
     * 获取触发器组
     */
    @Override
    public List<String> getTriggerGroupNames() throws SchedulerException {
        return scheduler.getTriggerGroupNames();
    }

    /**
     * 启动调度器
     */
    @Override
    public void start() throws SchedulerException {
        scheduler.start();
    }

    /**
     * 关闭调度器
     */
    @Override
    public void shutdown() throws SchedulerException {
        scheduler.shutdown(true);
    }

}
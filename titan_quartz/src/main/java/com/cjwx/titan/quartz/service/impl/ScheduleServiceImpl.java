package com.cjwx.titan.quartz.service.impl;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.config.QuartzConfiguration;
import com.cjwx.titan.quartz.dao.TriggerDao;
import com.cjwx.titan.quartz.entity.QtzScheduleEntity;
import com.cjwx.titan.quartz.execute.BaseExecService;
import com.cjwx.titan.quartz.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Resource
    private TriggerDao triggerDao;

    @Override
    public PageList<QtzScheduleEntity> getScheduleList(int start, int size, Map<String, Object> wheres) {
        PageList<QtzScheduleEntity> page = triggerDao.select(start, size, wheres);
        page.setList(page.getList().stream()
                .map(this::getTriggerEntity)
                .map(this::getJobEntity)
                .collect(Collectors.toList()));
        return page;
    }

    @Override
    public void create(QtzScheduleEntity job) {
        try {
            JobDetail j = updateJobDetail(JobBuilder.newJob(BaseExecService.class), job)
                    .storeDurably(true)
                    .build();
            Trigger t = updateTrigger(TriggerBuilder.newTrigger(), job).build();
            scheduler.scheduleJob(j, t);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
    }

    @Override
    public void update(QtzScheduleEntity job) {
        try {
            updateJobDetail(job);
            updateTrigger(job);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
    }

    /**
     * 更新任务参数
     */
    public JobDetail updateJobDetail(QtzScheduleEntity job) throws SchedulerException {
        return updateJobDetail(getJobDetail(job).getJobBuilder(), job).build();
    }

    public JobBuilder updateJobDetail(JobBuilder jobBuilder, QtzScheduleEntity job) {
        return jobBuilder.withIdentity(job.getName(), job.getGroup())
                .withDescription(job.getDescription())
                .usingJobData(QuartzConfiguration.SERVER_KEY, job.getService())
                .usingJobData(QuartzConfiguration.PATH_KEY, job.getMethod())
                .usingJobData(QuartzConfiguration.DATA_KEY, job.getData());
    }

    /**
     * 创建定时触发器
     */
    public Trigger updateTrigger(QtzScheduleEntity trigger) throws SchedulerException {
        return updateTrigger(getTrigger(trigger).getTriggerBuilder(), trigger).build();
    }

    public TriggerBuilder updateTrigger(TriggerBuilder triggerBuilder, QtzScheduleEntity trigger) {
        String cronExpression = trigger.getCronExpression();
        if (!CronExpression.isValidExpression(cronExpression)) {
            throw new IllegalArgumentException("cronExpression cannot be null");
        }
        ScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(cronExpression);
        return triggerBuilder.withIdentity(trigger.getName(), trigger.getGroup())
                .withPriority(trigger.getPriority())
                .withSchedule(cronSchedule)
                .withDescription(trigger.getDescription());
    }

    /**
     * 获取任务参数
     */
    public QtzScheduleEntity getJobEntity(QtzScheduleEntity job) {
        try {
            JobDetail jobDetail = getJobDetail(job);
            JobDataMap data = jobDetail.getJobDataMap();
            job.setService(data.getString(QuartzConfiguration.SERVER_KEY));
            job.setMethod(data.getString(QuartzConfiguration.PATH_KEY));
            job.setData(data.getString(QuartzConfiguration.DATA_KEY));
            job.setDescription(jobDetail.getDescription());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return job;
    }

    /**
     * 获取任务参数
     */
    public QtzScheduleEntity getTriggerEntity(QtzScheduleEntity trigger) {
        try {
            CronTrigger cronTrigger = (CronTrigger) getTrigger(trigger);
            trigger.setPriority(cronTrigger.getPriority());
            trigger.setCronExpression(cronTrigger.getCronExpression());
            trigger.setState(getRunState(trigger));
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return trigger;
    }

    /**
     * 查看触发器运行状态
     */
    public String getRunState(QtzScheduleEntity trigger) throws SchedulerException {
        return scheduler.getTriggerState(trigger.getTriggerKey()).name();
    }

    /**
     * 获取单个任务信息
     */
    public JobDetail getJobDetail(QtzScheduleEntity job) throws SchedulerException {
        log.debug("获取JOB:" + job.getName());
        return scheduler.getJobDetail(job.getJobKey());
    }

    /**
     * 获取单个任务信息
     */
    public Trigger getTrigger(QtzScheduleEntity trigger) throws SchedulerException {
        log.debug("获取JOB:" + trigger.getName());
        return scheduler.getTrigger(trigger.getTriggerKey());
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
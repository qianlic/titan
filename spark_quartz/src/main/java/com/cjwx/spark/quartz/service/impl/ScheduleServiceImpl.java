package com.cjwx.spark.quartz.service.impl;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.core.exception.ServiceException;
import com.cjwx.spark.engine.util.ExceptionUtils;
import com.cjwx.spark.engine.util.MapperUtils;
import com.cjwx.spark.engine.util.ObjectUtils;
import com.cjwx.spark.quartz.config.QuartzConfig;
import com.cjwx.spark.quartz.dto.QtzJobDTO;
import com.cjwx.spark.quartz.entity.QtzJobKey;
import com.cjwx.spark.quartz.execute.JobExecuteAdvice;
import com.cjwx.spark.quartz.repository.JobRepository;
import com.cjwx.spark.quartz.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;
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
    private JobRepository jobRepository;

    @Override
    public ResultDTO<PageDTO<QtzJobDTO>> getScheduleList(QtzJobDTO job, int start, int size) throws Exception {
        ResultDTO<PageDTO<QtzJobDTO>> result = MapperUtils.pageList(jobRepository,
                ObjectUtils.convert(job, QtzJobKey.class), start, size, QtzJobDTO.class);
        PageDTO<QtzJobDTO> page = result.getData();
        page.setList(page.getList().stream()
                .map(this::getTriggerEntity)
                .map(this::getJobEntity)
                .collect(Collectors.toList()));
        return result;
    }

    /**
     * 创建定时任务
     */
    @Override
    public void create(QtzJobDTO job) {
        try {
            JobDetail j = initJobDetail(JobBuilder.newJob(JobExecuteAdvice.class), job);
            Trigger t = initTrigger(TriggerBuilder.newTrigger(), job);
            scheduler.scheduleJob(j, t);
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            log.debug(e.getMessage(), e);
            throw new ServiceException("创建定时任务失败！" + e.getMessage());
        }
    }

    /**
     * 更新定时任务
     */
    @Override
    public void update(QtzJobDTO job) {
        try {
            JobDetail j = initJobDetail(getJobBuilder(job.getJobName(), job.getJobGroup()), job);
            Set<Trigger> t = new HashSet<>();
            t.add(initTrigger(getTriggerBuilder(job.getJobName(), job.getJobGroup()), job));
            scheduler.scheduleJob(j, t, true);
        } catch (SchedulerException e) {
            throw new ServiceException("更新定时任务失败！" + e.getMessage());
        }
    }

    /**
     * 删除定时任务
     */
    @Override
    public void delete(QtzJobDTO job) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            ExceptionUtils.throwError("删除定时任务失败！" + e.getMessage());
        }
    }

    /**
     * 暂停定时任务
     */
    @Override
    public void pause(QtzJobDTO job) {
        try {
            scheduler.pauseJob(JobKey.jobKey(job.getJobName(), job.getJobGroup()));
        } catch (SchedulerException e) {
            ExceptionUtils.throwError("暂停定时任务失败！" + e.getMessage());
        }
    }

    /**
     * 暂停定时任务
     */
    @Override
    public void resume(QtzJobDTO job) {
        try {
            scheduler.resumeJob(JobKey.jobKey(job.getJobName(), job.getJobGroup()));
        } catch (SchedulerException e) {
            ExceptionUtils.throwError("恢复定时任务失败！" + e.getMessage());
        }
    }

    /**
     * 立即运行任务
     */
    @Override
    public void start(QtzJobDTO job) {
        try {
            scheduler.triggerJob(JobKey.jobKey(job.getJobName(), job.getJobGroup()));
        } catch (SchedulerException e) {
            ExceptionUtils.throwError("运行定时任务失败！" + e.getMessage());
        }
    }

    public JobDetail initJobDetail(JobBuilder jobBuilder, QtzJobDTO job) {
        return initJobBuilder(jobBuilder, job).build();
    }

    public JobBuilder initJobBuilder(JobBuilder jobBuilder, QtzJobDTO job) {
        return jobBuilder.withIdentity(job.getJobName(), job.getJobGroup())
                .withDescription(job.getDescription())
                .usingJobData(QuartzConfig.SERVER_KEY, job.getServer())
                .usingJobData(QuartzConfig.PATH_KEY, job.getPath())
                .usingJobData(QuartzConfig.DATA_KEY, job.getData());
    }

    public Trigger initTrigger(TriggerBuilder<?> triggerBuilder, QtzJobDTO trigger) {
        return initTriggerBuilder(triggerBuilder, trigger).build();
    }

    public TriggerBuilder<?> initTriggerBuilder(TriggerBuilder triggerBuilder, QtzJobDTO trigger) {
        String cronExpression = trigger.getCronExpression();
        if (!CronExpression.isValidExpression(cronExpression)) {
            ExceptionUtils.throwError("请输入正确的CRON表达式！");
        }
        CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(cronExpression);
        return triggerBuilder.withIdentity(trigger.getJobName(), trigger.getJobGroup())
                .withPriority(trigger.getPriority())
                .withSchedule(cronSchedule)
                .withDescription(trigger.getDescription());
    }

    public QtzJobDTO getJobEntity(QtzJobDTO job) {
        try {
            JobDetail jobDetail = getJobDetail(job.getJobName(), job.getJobGroup());
            JobDataMap data = jobDetail.getJobDataMap();
            job.setDescription(jobDetail.getDescription());
            job.setServer(data.getString(QuartzConfig.SERVER_KEY));
            job.setPath(data.getString(QuartzConfig.PATH_KEY));
            job.setData(data.getString(QuartzConfig.DATA_KEY));
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return job;
    }

    public QtzJobDTO getTriggerEntity(QtzJobDTO trigger) {
        try {
            CronTrigger cronTrigger = (CronTrigger) getTrigger(trigger.getJobName(), trigger.getJobGroup());
            trigger.setPriority(cronTrigger.getPriority());
            trigger.setCronExpression(cronTrigger.getCronExpression());
            trigger.setPreviousTime(cronTrigger.getPreviousFireTime());
            trigger.setNextTime(cronTrigger.getNextFireTime());
            trigger.setState(getRunState(trigger));
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return trigger;
    }

    /**
     * 查看触发器运行状态
     */
    public String getRunState(QtzJobDTO job) throws SchedulerException {
        return scheduler.getTriggerState(TriggerKey.triggerKey(job.getJobName(), job.getJobGroup())).name();
    }

    /**
     * 获取任务builder
     */
    public JobBuilder getJobBuilder(String name, String group) throws SchedulerException {
        return getJobDetail(name, group).getJobBuilder();
    }

    /**
     * 获取触发器builder
     */
    public TriggerBuilder<?> getTriggerBuilder(String name, String group) throws SchedulerException {
        return getTrigger(name, group).getTriggerBuilder();
    }

    /**
     * 获取任务信息
     */
    public JobDetail getJobDetail(String name, String group) throws SchedulerException {
        log.debug("获取JOB:" + name);
        return scheduler.getJobDetail(JobKey.jobKey(name, group));
    }

    /**
     * 获取触发器信息
     */
    public Trigger getTrigger(String name, String group) throws SchedulerException {
        log.debug("获取TRIGGER:" + name);
        return scheduler.getTrigger(TriggerKey.triggerKey(name, group));
    }

}
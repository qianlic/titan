package com.cjwx.titan.quartz.service.impl;

import com.cjwx.titan.engine.core.exception.ServiceException;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.config.QuartzConfiguration;
import com.cjwx.titan.quartz.dao.JobDao;
import com.cjwx.titan.quartz.bean.QtzScheduleJobBean;
import com.cjwx.titan.quartz.execute.JobExecuteService;
import com.cjwx.titan.quartz.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
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
    private JobDao jobDao;

    @Override
    public PageList<QtzScheduleJobBean> getScheduleList(int start, int size, Map<String, Object> wheres) {
        PageList<QtzScheduleJobBean> page = jobDao.select(start, size, wheres);
        page.getList().stream().map(this::getTriggerEntity).map(this::getJobEntity).collect(Collectors.toList());
        return page;
    }

    /**
     * 创建定时任务
     */
    @Override
    public void create(QtzScheduleJobBean job) {
        try {
            JobDetail j = initJobDetail(JobBuilder.newJob(JobExecuteService.class), job);
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
    public void update(QtzScheduleJobBean job) {
        try {
            JobDetail j = initJobDetail(getJobBuilder(job), job);
            Set<Trigger> t = new HashSet<>();
            t.add(initTrigger(getTriggerBuilder(job), job));
            scheduler.scheduleJob(j, t, true);
        } catch (SchedulerException e) {
            log.debug(e.getMessage(), e);
            throw new ServiceException("更新定时任务失败！" + e.getMessage());
        }
    }

    /**
     * 删除定时任务
     */
    @Override
    public void delete(QtzScheduleJobBean job) {
        try {
            TriggerKey triggerKey = job.getTriggerKey();
            JobKey jobKey = job.getJobKey();
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.debug(e.getMessage(), e);
            throw new ServiceException("删除定时任务失败！" + e.getMessage());
        }
    }

    /**
     * 暂停定时任务
     */
    @Override
    public void pause(QtzScheduleJobBean job) {
        try {
            scheduler.pauseJob(job.getJobKey());
        } catch (SchedulerException e) {
            log.debug(e.getMessage(), e);
            throw new ServiceException("暂停定时任务失败！" + e.getMessage());
        }
    }

    /**
     * 暂停定时任务
     */
    @Override
    public void resume(QtzScheduleJobBean job) {
        try {
            scheduler.resumeJob(job.getJobKey());
        } catch (SchedulerException e) {
            log.debug(e.getMessage(), e);
            throw new ServiceException("恢复定时任务失败！" + e.getMessage());
        }
    }

    /**
     * 立即运行任务
     */
    @Override
    public void start(QtzScheduleJobBean job) {
        try {
            scheduler.triggerJob(job.getJobKey());
        } catch (SchedulerException e) {
            log.debug(e.getMessage(), e);
            throw new ServiceException("运行定时任务失败！" + e.getMessage());
        }
    }

    public JobDetail initJobDetail(JobBuilder jobBuilder, QtzScheduleJobBean job) {
        return initJobBuilder(jobBuilder, job).build();
    }

    public JobBuilder initJobBuilder(JobBuilder jobBuilder, QtzScheduleJobBean job) {
        return jobBuilder.withIdentity(job.getName(), job.getGroup())
                .withDescription(job.getDescription())
                .usingJobData(QuartzConfiguration.SERVER_KEY, job.getServer())
                .usingJobData(QuartzConfiguration.PATH_KEY, job.getPath())
                .usingJobData(QuartzConfiguration.DATA_KEY, job.getData());
    }

    public Trigger initTrigger(TriggerBuilder triggerBuilder, QtzScheduleJobBean trigger) {
        return initTriggerBuilder(triggerBuilder, trigger).build();
    }

    public TriggerBuilder initTriggerBuilder(TriggerBuilder triggerBuilder, QtzScheduleJobBean trigger) {
        String cronExpression = trigger.getCronExpression();
        if (!CronExpression.isValidExpression(cronExpression)) {
            throw new ServiceException("请输入正确的CRON表达式！");
        }
        ScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(cronExpression);
        return triggerBuilder.withIdentity(trigger.getName(), trigger.getGroup())
                .withPriority(trigger.getPriority())
                .withSchedule(cronSchedule)
                .withDescription(trigger.getDescription());
    }

    public QtzScheduleJobBean getJobEntity(QtzScheduleJobBean job) {
        try {
            JobDetail jobDetail = getJobDetail(job);
            JobDataMap data = jobDetail.getJobDataMap();
            job.setDescription(jobDetail.getDescription());
            job.setServer(data.getString(QuartzConfiguration.SERVER_KEY));
            job.setPath(data.getString(QuartzConfiguration.PATH_KEY));
            job.setData(data.getString(QuartzConfiguration.DATA_KEY));
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return job;
    }

    public QtzScheduleJobBean getTriggerEntity(QtzScheduleJobBean trigger) {
        try {
            CronTrigger cronTrigger = (CronTrigger) getTrigger(trigger);
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
    public String getRunState(QtzScheduleJobBean trigger) throws SchedulerException {
        return scheduler.getTriggerState(trigger.getTriggerKey()).name();
    }

    /**
     * 获取任务builder
     */
    public JobBuilder getJobBuilder(QtzScheduleJobBean job) throws SchedulerException {
        log.debug("获取JOB:" + job.getName());
        return getJobDetail(job).getJobBuilder();
    }

    /**
     * 获取任务信息
     */
    public JobDetail getJobDetail(QtzScheduleJobBean job) throws SchedulerException {
        log.debug("获取JOB:" + job.getName());
        return scheduler.getJobDetail(job.getJobKey());
    }

    /**
     * 获取触发器builder
     */
    public TriggerBuilder getTriggerBuilder(QtzScheduleJobBean trigger) throws SchedulerException {
        return getTrigger(trigger).getTriggerBuilder();
    }

    /**
     * 获取触发器信息
     */
    public Trigger getTrigger(QtzScheduleJobBean trigger) throws SchedulerException {
        log.debug("获取JOB:" + trigger.getName());
        return scheduler.getTrigger(trigger.getTriggerKey());
    }

}
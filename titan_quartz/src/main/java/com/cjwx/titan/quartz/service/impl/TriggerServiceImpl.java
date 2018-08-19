package com.cjwx.titan.quartz.service.impl;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.dao.TriggerDao;
import com.cjwx.titan.quartz.entity.QtzTriggerEntity;
import com.cjwx.titan.quartz.service.TriggerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class TriggerServiceImpl implements TriggerService {

    @Resource
    private Scheduler scheduler;
    @Resource
    private TriggerDao triggerDao;

    @Override
    public PageList<QtzTriggerEntity> getTriggerList(int start, int size, Map<String, Object> wheres) {
        PageList<QtzTriggerEntity> page = triggerDao.findTrigerList(start, size, wheres);
        page.setList(page.getList().stream().map(this::getTriggerEntity).collect(Collectors.toList()));
        return page;
    }

    /**
     * 创建定时触发器
     */
    @Override
    public void createTrigger(QtzTriggerEntity trigger) {
        try {
            String cronExpression = trigger.getCronExpression();
            if (!CronExpression.isValidExpression(cronExpression)) {
                throw new IllegalArgumentException("cronExpression cannot be null");
            }
            ScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(cronExpression);
            TriggerBuilder t = TriggerBuilder.newTrigger()
                    .withIdentity(trigger.getName(), trigger.getGroup())
                    .withPriority(trigger.getPriority())
                    .withSchedule(cronSchedule)
                    .forJob(trigger.getJobKey())
                    .withDescription(trigger.getDescription());
            scheduler.scheduleJob(t.build());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
    }

    /**
     * 获取任务参数
     */
    @Override
    public QtzTriggerEntity getTriggerEntity(QtzTriggerEntity trigger) {
        try {
            CronTrigger cronTrigger = (CronTrigger) getTrigger(trigger);
            trigger.setPriority(cronTrigger.getPriority());
            trigger.setCronExpression(cronTrigger.getCronExpression());
            trigger.setDescription(cronTrigger.getDescription());
            trigger.setJobKey(cronTrigger.getJobKey());
            trigger.setState(getRunState(trigger));
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return trigger;
    }

    /**
     * 获取单个任务信息
     */
    @Override
    public Trigger getTrigger(QtzTriggerEntity trigger) throws SchedulerException {
        log.debug("获取JOB:" + trigger.getName());
        return scheduler.getTrigger(trigger.getTriggerKey());
    }

    /**
     * 查看触发器运行状态
     */
    @Override
    public String getRunState(QtzTriggerEntity trigger) throws SchedulerException {
        return scheduler.getTriggerState(trigger.getTriggerKey()).name();
    }

    /**
     * 暂停单个触发器
     */
    @Override
    public boolean delete(QtzTriggerEntity trigger) {
        try {
            log.debug("删除TRIGGER:" + trigger.getName());
            return scheduler.unscheduleJob(trigger.getTriggerKey());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 暂停单个触发器
     */
    @Override
    public void pause(QtzTriggerEntity trigger) {
        try {
            log.debug("暂停TRIGGER:" + trigger.getName());
            scheduler.pauseTrigger(trigger.getTriggerKey());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
    }

    /**
     * 恢复单个触发器
     */
    @Override
    public void resume(QtzTriggerEntity trigger) {
        try {
            log.debug("恢复TRIGGER:" + trigger.getName());
            scheduler.resumeTrigger(trigger.getTriggerKey());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
    }

}

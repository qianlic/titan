package com.cjwx.spark.quartz.service.impl;

import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.quartz.entity.QtzExecuteLog;
import com.cjwx.spark.quartz.repository.LogRepository;
import com.cjwx.spark.quartz.service.ExecuteLogService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月09日 15:44
 */
@Service
@Transactional
public class ExecuteLogServiceImpl implements ExecuteLogService {

    @Resource
    private LogRepository logRepository;

    @Override
    public void create(QtzExecuteLog log) {
        logRepository.save(log);
    }

    @Override
    public int delete(List<Long> ids) {
        return logRepository.deleteByIdIn(ids);
    }

    @Override
    public PageList<QtzExecuteLog> getLogList(int start, int size, QtzExecuteLog wheres) {
        return PageList.of(logRepository.findAll(Example.of(wheres), PageRequest.of(start, size)));
    }

}

package com.cjwx.spark.quartz.service.impl;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.MapperUtils;
import com.cjwx.spark.engine.util.ObjectUtils;
import com.cjwx.spark.quartz.dto.QtzExecuteLogDTO;
import com.cjwx.spark.quartz.entity.QtzExecuteLog;
import com.cjwx.spark.quartz.repository.LogRepository;
import com.cjwx.spark.quartz.service.ExecuteLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 定时任务执行日志
 * @Author: qian li
 * @Date: 2018年11月09日 15:44
 */
@Service
@Transactional
public class ExecuteLogServiceImpl implements ExecuteLogService {

    @Resource
    private LogRepository logRepository;

    @Override
    public ResultDTO<Integer> create(QtzExecuteLogDTO log) throws Exception {
        return MapperUtils.insert(logRepository, ObjectUtils.convert(log, QtzExecuteLog.class));
    }

    @Override
    public ResultDTO<Integer> delete(List<Long> ids) {
        return MapperUtils.delete(logRepository, ids);
    }

    @Override
    public ResultDTO<PageDTO<QtzExecuteLogDTO>> getLogList(QtzExecuteLogDTO log, int start, int size) throws Exception {
        return MapperUtils.pageList(logRepository, ObjectUtils.convert(log, QtzExecuteLog.class), start, size, QtzExecuteLogDTO.class);
    }

}

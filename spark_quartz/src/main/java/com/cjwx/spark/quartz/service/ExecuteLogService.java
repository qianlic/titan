package com.cjwx.spark.quartz.service;

import com.cjwx.spark.quartz.entity.QtzExecuteLogEntity;
import com.cjwx.spark.engine.core.model.PageList;

import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月09日 15:44
 */
public interface ExecuteLogService {

    void create(QtzExecuteLogEntity log);

    int delete(List<Long> ids);

    PageList<QtzExecuteLogEntity> getLogList(int start, int size, QtzExecuteLogEntity wheres);

}

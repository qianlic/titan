package com.cjwx.spark.quartz.service;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.quartz.dto.QtzExecuteLogDTO;

import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月09日 15:44
 */
public interface ExecuteLogService {

    ResultDTO<Integer> create(QtzExecuteLogDTO log) throws Exception;

    ResultDTO<Integer> delete(List<Long> ids);

    ResultDTO<PageDTO<QtzExecuteLogDTO>> getLogList(QtzExecuteLogDTO log, int start, int size) throws Exception;

}

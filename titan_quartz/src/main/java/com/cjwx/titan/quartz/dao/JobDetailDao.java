package com.cjwx.titan.quartz.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.entity.QtzJobEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月03日 9:10
 */
public interface JobDetailDao {

    List<QtzJobEntity> select();

    PageList<QtzJobEntity> select(int start, int size, Map<String, Object> where);

}

package com.cjwx.titan.quartz.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.dao.TriggerDao;
import com.cjwx.titan.quartz.entity.QtzScheduleEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月03日 9:12
 */
@Repository
public class TriggerDaoImpl extends BaseDao implements TriggerDao {

    private static final String CUSTOM_TABLE = "qtz_triggers";
    private static final String CUSTOM_COLUMS = "TRIGGER_NAME 'name',TRIGGER_GROUP 'group'";

    @Override
    public PageList<QtzScheduleEntity> select(int start, int size, Map<String, Object> where) {
        return this.getQuery(QtzScheduleEntity.class).table(CUSTOM_TABLE)
                .select(CUSTOM_COLUMS).eq(where).page(start, size);
    }

}

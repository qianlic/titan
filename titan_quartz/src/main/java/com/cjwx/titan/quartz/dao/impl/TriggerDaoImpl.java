package com.cjwx.titan.quartz.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.dao.TriggerDao;
import com.cjwx.titan.quartz.entity.QtzTriggerEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月03日 9:12
 */
@Repository
public class TriggerDaoImpl extends BaseDao implements TriggerDao {

    @Override
    public PageList<QtzTriggerEntity> select(int start, int size, Map<String, Object> where) {
        return this.getQuery().select("TRIGGER_NAME 'name',TRIGGER_GROUP 'group'")
                .from("qtz_triggers").eq(where).page(start, size, QtzTriggerEntity.class);
    }

}

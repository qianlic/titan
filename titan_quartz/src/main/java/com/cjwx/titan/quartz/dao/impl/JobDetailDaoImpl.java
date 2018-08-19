package com.cjwx.titan.quartz.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.dao.JobDetailDao;
import com.cjwx.titan.quartz.entity.QtzJobEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月03日 9:12
 */
@Repository
public class JobDetailDaoImpl extends BaseDao implements JobDetailDao {

    @Override
    public List<QtzJobEntity> findJobDetailList() {
        return this.getQuery().select("JOB_NAME 'name',JOB_GROUP 'group'")
                .from("qtz_job_details").list(QtzJobEntity.class);
    }

    @Override
    public PageList<QtzJobEntity> findJobDetailList(int start, int size, Map<String, Object> wheres) {
        return this.getQuery().select("JOB_NAME 'name',JOB_GROUP 'group'")
                .from("qtz_job_details").eq(wheres).page(start, size, QtzJobEntity.class);
    }

}

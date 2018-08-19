package com.cjwx.titan.crawler.dao;

import com.cjwx.titan.crawler.bean.ClrWebUrlBean;
import com.cjwx.titan.engine.core.model.PageList;

import java.util.List;
import java.util.Map;

public interface WebUrlDao {

    long createWebUrl(ClrWebUrlBean page);

    int deleteWebUrl(List ids);

    PageList<ClrWebUrlBean> findWebUrlList(int start, int size, Map<String, Object> whereCondition);

}

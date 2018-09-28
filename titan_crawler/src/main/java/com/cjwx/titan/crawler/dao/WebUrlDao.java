package com.cjwx.titan.crawler.dao;

import com.cjwx.titan.crawler.bean.ClrWebUrlBean;
import com.cjwx.titan.engine.core.model.PageList;

import java.util.List;
import java.util.Map;

public interface WebUrlDao {

    long insert(Object bean);

    int delete(List ids);

    PageList<ClrWebUrlBean> select(int start, int size, Map<String, Object> where);

}

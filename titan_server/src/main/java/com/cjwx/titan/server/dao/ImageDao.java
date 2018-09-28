package com.cjwx.titan.server.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.ComImageBean;

import java.util.Map;

public interface ImageDao {

    long insert(Object bean);

    int delete(String hash);

    PageList<ComImageBean> select(int start, int size, Map<String, Object> where);
    
}

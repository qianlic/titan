package com.cjwx.titan.server.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.SysImageBean;

import java.util.Map;

public interface ImageDao {

    void createImage(SysImageBean image);

    int deleteImage(String hash);

    PageList<SysImageBean> findImageList(int start, int size, Map<String, Object> wheres);
    
}

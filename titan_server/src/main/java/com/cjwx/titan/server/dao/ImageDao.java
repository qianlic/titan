package com.cjwx.titan.server.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.ComImageBean;

import java.util.Map;

public interface ImageDao {

    void createImage(ComImageBean image);

    int deleteImage(String hash);

    PageList<ComImageBean> findImageList(int start, int size, Map<String, Object> wheres);
    
}

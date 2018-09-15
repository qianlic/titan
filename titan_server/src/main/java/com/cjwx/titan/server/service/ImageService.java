package com.cjwx.titan.server.service;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.SysImageBean;

import java.io.File;
import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年09月07日 14:36
 */
public interface ImageService {

    SysImageBean upload(String name, File file);

    void delete(String hash);

    PageList<SysImageBean> getImageList(int start, int size, Map<String, Object> wheres);

}

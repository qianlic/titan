package com.cjwx.spark.server.service;

import com.cjwx.spark.server.entity.ComImage;
import com.cjwx.spark.engine.core.model.PageList;

import java.io.File;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年09月07日 14:36
 */
public interface ImageService {

    ComImage upload(String name, File file);

    void delete(String hash);

    PageList<ComImage> getImageList(int start, int size, ComImage imge);

}

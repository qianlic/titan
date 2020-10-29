package com.cjwx.spark.server.service;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.server.dto.ComImageDTO;

import java.io.File;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年09月07日 14:36
 */
public interface ImageService {

    ResultDTO<Integer> createImage(ComImageDTO image) throws Exception;

    ResultDTO<Integer> updateImage(ComImageDTO image) throws Exception;

    ResultDTO<Integer> deleteImage(String hash);

    ResultDTO<PageDTO<ComImageDTO>> getImageList(ComImageDTO image, int start, int size) throws Exception;

    ResultDTO<ComImageDTO> upload(String name, File file) throws Exception;

    ResultDTO<Integer> delete(String hash);

}

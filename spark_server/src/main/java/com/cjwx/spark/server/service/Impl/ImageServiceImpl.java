package com.cjwx.spark.server.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.*;
import com.cjwx.spark.server.dto.ComImageDTO;
import com.cjwx.spark.server.entity.ComImage;
import com.cjwx.spark.server.repository.ImageRepository;
import com.cjwx.spark.server.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;

/**
 * @Description: 图片管理
 * @Author: qian li
 * @Date: 2018年09月07日 14:35
 */
@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    public static final String upload_url = "https://sm.ms/api/upload";
    public static final String delete_url = "https://sm.ms/api/delete";

    @Resource
    private ImageRepository imageRepository;

    @Override
    public ResultDTO<Integer> createImage(ComImageDTO image) throws Exception {
        return MapperUtils.insert(imageRepository, ObjectUtils.convert(image, ComImage.class));
    }

    @Override
    public ResultDTO<Integer> updateImage(ComImageDTO image) throws Exception {
        return MapperUtils.update(imageRepository, ObjectUtils.convert(image, ComImage.class));
    }

    @Override
    public ResultDTO<Integer> deleteImage(String hash) {
        return MapperUtils.delete(imageRepository, new LambdaQueryWrapper<ComImage>()
                .eq(ComImage::getHash, hash));
    }

    @Override
    public ResultDTO<PageDTO<ComImageDTO>> getImageList(ComImageDTO image, int start, int size) throws Exception {
        return MapperUtils.pageList(imageRepository, ObjectUtils.convert(image, ComImage.class), start, size, ComImageDTO.class);
    }

    @Override
    public ResultDTO<ComImageDTO> upload(String name, File file) throws Exception {
        String result = HttpClientUtils.doFromRequest(upload_url, new HashMap<String, Object>() {{
            put("smfile", file);
        }});
        if (StringUtils.isNotEmpty(result)) {
            JSONObject josn = JSONObject.parseObject(result);
            if ("success".equalsIgnoreCase(josn.getString("code"))) {
                ComImageDTO image = josn.getObject("script", ComImageDTO.class);
                image.setFilename(name);
                createImage(image);
                return ResultUtils.success(image);
            } else {
                return ResultUtils.fail("图片上传失败，" + josn.getString("msg"));
            }
        }
        return ResultUtils.fail("图片上传失败，接口返回空");
    }

    @Override
    public ResultDTO<Integer> delete(String hash) {
        String url = delete_url + "/" + hash;
        String result = HttpClientUtils.dohttpRequest(url, "GET", null);
        if (StringUtils.isNotEmpty(result) && result.contains("File delete success")) {
            return deleteImage(hash);
        }
        return ResultUtils.fail("图片删除失败，" + result);
    }

}

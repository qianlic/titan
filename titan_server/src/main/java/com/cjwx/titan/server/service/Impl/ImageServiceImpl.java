package com.cjwx.titan.server.service.Impl;

import com.cjwx.titan.engine.util.HttpClientUtils;
import com.cjwx.titan.server.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 图片上传
 * @Author: qian li
 * @Date: 2018年09月07日 14:35
 */
@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    String upload_url = "https://sm.ms/api/upload";

    @Override
    public void upload(File file) {
        Map<String, Object> param = new HashMap<>();
        param.put("smfile", file);
        HttpClientUtils.doFromRequest(upload_url, param);
    }

}

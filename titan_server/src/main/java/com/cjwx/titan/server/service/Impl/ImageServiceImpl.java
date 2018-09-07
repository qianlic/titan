package com.cjwx.titan.server.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.titan.engine.core.exception.ServiceException;
import com.cjwx.titan.engine.util.HttpClientUtils;
import com.cjwx.titan.engine.util.StringUtils;
import com.cjwx.titan.server.bean.SysImageBean;
import com.cjwx.titan.server.dao.ImageDao;
import com.cjwx.titan.server.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 图片管理
 * @Author: qian li
 * @Date: 2018年09月07日 14:35
 */
@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    String upload_url = "https://sm.ms/api/upload";

    @Resource
    private ImageDao imageDao;

    /**
     *  图片上传
     */
    @Override
    public void upload(String name, File file) {
        Map<String, Object> param = new HashMap<>();
        param.put("smfile", file);
        String result = HttpClientUtils.doFromRequest(upload_url, param);
        if (StringUtils.isNotEmpty(result)) {
            JSONObject josn = JSONObject.parseObject(result);
            if ("success".equalsIgnoreCase(josn.getString("code"))) {
                SysImageBean imge = josn.getObject("data", SysImageBean.class);
                imge.setFilename(name);
                imageDao.createImage(imge);
            } else {
                throw new ServiceException(josn.getString("msg"));
            }
        }
    }

}

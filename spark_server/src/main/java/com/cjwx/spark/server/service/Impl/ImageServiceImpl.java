package com.cjwx.spark.server.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cjwx.spark.engine.core.exception.ServiceException;
import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.util.HttpClientUtils;
import com.cjwx.spark.engine.util.StringUtils;
import com.cjwx.spark.server.entity.ComImage;
import com.cjwx.spark.server.repository.ImageRepository;
import com.cjwx.spark.server.service.ImageService;
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

    public static final String upload_url = "https://sm.ms/api/upload";
    public static final String delete_url = "https://sm.ms/api/delete";

    @Resource
    private ImageRepository imageRepository;

    @Override
    public ComImage upload(String name, File file) {
        ComImage imge = null;
        Map<String, Object> param = new HashMap<>();
        param.put("smfile", file);
        String result = HttpClientUtils.doFromRequest(upload_url, param);
        if (StringUtils.isNotEmpty(result)) {
            JSONObject josn = JSONObject.parseObject(result);
            if ("success".equalsIgnoreCase(josn.getString("code"))) {
                imge = josn.getObject("script", ComImage.class);
                imge.setFilename(name);
                imageRepository.insert(imge);
            } else {
                throw new ServiceException(josn.getString("msg"));
            }
        }
        return imge;
    }

    @Override
    public void delete(String hash) {
        String url = delete_url + "/" + hash;
        String result = HttpClientUtils.dohttpRequest(url, "GET", null);
        if (StringUtils.isNotEmpty(result) && result.contains("File delete success")) {
            LambdaQueryWrapper<ComImage> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ComImage::getHash, hash);
            imageRepository.delete(wrapper);
        }
    }

    @Override
    public PageList<ComImage> getImageList(int start, int size, ComImage imge) {
        return PageList.of(imageRepository, imge, start, size);
    }

}

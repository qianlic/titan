package com.cjwx.titan.server.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.titan.engine.core.exception.ServiceException;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.util.HttpClientUtils;
import com.cjwx.titan.engine.util.StringUtils;
import com.cjwx.titan.server.bean.ComImageBean;
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

    public static final String upload_url = "https://sm.ms/api/upload";
    public static final String delete_url = "https://sm.ms/api/delete";

    @Resource
    private ImageDao imageDao;

    @Override
    public ComImageBean upload(String name, File file) {
        ComImageBean imge = null;
        Map<String, Object> param = new HashMap<>();
        param.put("smfile", file);
        String result = HttpClientUtils.doFromRequest(upload_url, param);
        if (StringUtils.isNotEmpty(result)) {
            JSONObject josn = JSONObject.parseObject(result);
            if ("success".equalsIgnoreCase(josn.getString("code"))) {
                imge = josn.getObject("script", ComImageBean.class);
                imge.setFilename(name);
                imageDao.insert(imge);
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
            imageDao.delete(hash);
        }
    }

    @Override
    public PageList<ComImageBean> getImageList(int start, int size, Map<String, Object> wheres) {
        return imageDao.select(start, size, wheres);
    }

}

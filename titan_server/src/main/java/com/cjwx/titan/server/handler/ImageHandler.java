package com.cjwx.titan.server.handler;

import com.cjwx.titan.engine.core.web.annotation.RestHandler;
import com.cjwx.titan.server.service.ImageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @Author: qian li
 * @Date: 2018年08月01日 16:12
 */
@RestHandler("图片管理接口")
@RequestMapping(value = "/system/image/", method = RequestMethod.POST)
public class ImageHandler {

    @Resource
    private ImageService imageService;

    @RequestMapping("upload")
    public void upload(MultipartFile file) {
        imageService.upload((File) file);
    }

}

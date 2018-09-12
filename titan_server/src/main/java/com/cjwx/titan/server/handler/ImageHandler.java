package com.cjwx.titan.server.handler;

import com.cjwx.titan.engine.core.web.annotation.RestHandler;
import com.cjwx.titan.engine.util.StringUtils;
import com.cjwx.titan.server.bean.SysImageBean;
import com.cjwx.titan.server.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @Author: qian li
 * @Date: 2018年08月01日 16:12
 */
@Slf4j
@RestHandler("图片管理接口")
@RequestMapping(value = "/system/image/", method = RequestMethod.POST)
public class ImageHandler {

    @Resource
    private ImageService imageService;

    /**
     * 图片上传
     */
    @RequestMapping("upload")
    public SysImageBean upload(MultipartFile file) {
        SysImageBean img = null;
        try {
            String fullName = file.getOriginalFilename();
            int i = fullName.lastIndexOf(".");
            String prefix = fullName.substring(0, i);
            String suffix = fullName.substring(i);
            File temp = File.createTempFile(StringUtils.randomUUID(), suffix);
            file.transferTo(temp);
            img = imageService.upload(prefix, temp);
            if (temp.exists()) temp.delete();
        } catch (Exception e) {
            log.debug("文件上传异常：", e);
        }
        return img;
    }

    /**
     * 图片删除
     */
    @RequestMapping("remove")
    public void remove(String hash) {
        imageService.delete(hash);
    }

}

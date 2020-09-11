package com.cjwx.spark.server.handler;

import com.cjwx.spark.engine.core.model.Model;
import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.util.StringUtils;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.server.entity.ComImageEntity;
import com.cjwx.spark.server.service.ImageService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @Author: qian li
 * @Date: 2018年08月01日 16:12
 */
@Slf4j
@Api(tags = "图片管理接口")
@RestHandler(value = "/image/")
public class ImageHandler {

    @Resource
    private ImageService imageService;

    /**
     * 图片上传
     */
    @RestMethod("upload")
    public ComImageEntity upload(MultipartFile file) {
        ComImageEntity img = null;
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
    @RestMethod("remove")
    public void remove(@RequestBody String hash) {
        imageService.delete(hash);
    }

    /**
     * 图片列表
     */
    @RestMethod("list")
    public PageList<ComImageEntity> list(@RequestBody Model<ComImageEntity> model) {
        return imageService.getImageList(model.getStart(), model.getSize(), model.getParams());
    }

}

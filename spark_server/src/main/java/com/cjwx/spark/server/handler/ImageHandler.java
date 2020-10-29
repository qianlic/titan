package com.cjwx.spark.server.handler;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.StringUtils;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.server.dto.ComImageDTO;
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
     * 图片列表
     */
    @RestMethod("list")
    public ResultDTO<PageDTO<ComImageDTO>> list(@RequestBody ComImageDTO image) throws Exception {
        return imageService.getImageList(image, image.getStart(), image.getSize());
    }

    /**
     * 图片上传
     */
    @RestMethod("upload")
    public ResultDTO<ComImageDTO> upload(MultipartFile file) throws Exception {
        String fullName = file.getOriginalFilename();
        assert fullName != null;
        int i = fullName.lastIndexOf(".");
        String prefix = fullName.substring(0, i);
        String suffix = fullName.substring(i);
        File temp = File.createTempFile(StringUtils.randomUUID(), suffix);
        file.transferTo(temp);
        ResultDTO<ComImageDTO> result = imageService.upload(prefix, temp);
        if (temp.exists()) temp.delete();
        return result;
    }

    /**
     * 图片删除
     */
    @RestMethod("remove")
    public ResultDTO<Integer> remove(@RequestBody String hash) {
        return imageService.delete(hash);
    }

}

package com.cjwx.spark.server.handler;

import com.cjwx.spark.engine.core.constant.AppConstant;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.ResultUtils;
import com.cjwx.spark.engine.util.file.FileTool;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 文件服务
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@RestController
public class FileHandler {

    @Resource
    private FileTool fileTool;

    @RestMethod(AppConstant.FTP_UPLOAD)
    public ResultDTO<String> uploadToFtpServer(MultipartFile file) throws Exception {
        return ResultUtils.success(fileTool.uploadFileToFtpServer(file));
    }

    @RestMethod(AppConstant.FTP_DOWNLOAD)
    public void downloadToFtpServer(HttpServletResponse response, String url) throws Exception {
        fileTool.downloadFileToFtpServer(url, response.getOutputStream());
    }

}

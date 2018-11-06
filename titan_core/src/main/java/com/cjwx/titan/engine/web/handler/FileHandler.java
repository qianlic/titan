package com.cjwx.titan.engine.web.handler;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.util.file.FileTool;
import com.cjwx.titan.engine.web.http.Result;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(HttpConstant.FTP_UPLOAD)
    public Result uploadToFtpServer(MultipartFile file) throws Exception {
        return new Result(fileTool.uploadFileToFtpServer(file));
    }

    @RequestMapping(HttpConstant.FTP_DOWNLOAD)
    public void downloadToFtpServer(HttpServletResponse response, String url) throws Exception {
        fileTool.downloadFileToFtpServer(url, response.getOutputStream());
    }

}

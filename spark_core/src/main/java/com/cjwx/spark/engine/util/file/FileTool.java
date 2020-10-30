package com.cjwx.spark.engine.util.file;

import com.cjwx.spark.engine.core.constant.AppConstant;
import com.cjwx.spark.engine.util.ExceptionUtils;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Description: 文件操作工具类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
public class FileTool {

    private String ftpURL;
    private int ftpPort;
    private String ftpAdmin;
    private String ftpPassword;
    private String ftpRootPath;
    private Long maxFileSize;
    private String fileFormat;

    public String uploadFile(MultipartFile multipartFile) {
        String fileType = checkFileName(multipartFile);
        String fileName = System.currentTimeMillis() + "." + fileType;
        File file = new File("/" + fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            ExceptionUtils.throwError("文件:[" + fileName + "]格式不符", e);
        }
        return fileName;
    }

    public String uploadFileToFtpServer(MultipartFile multipartFile) throws Exception {
        String fileType = checkFileName(multipartFile);
        List<String> fileNames = new ArrayList<>();
        connectFtpServer(ftp -> {
            try {
                String fileName = createFileNameByType(fileType);
                ftp.storeFile(fileName, multipartFile.getInputStream());
                fileNames.add(fileName);
            } catch (Exception ex) {
                ExceptionUtils.throwError("文件上传异常", ex);
            }
        });
        return fileNames.get(0);
    }

    public void downloadFileToFtpServer(String path, OutputStream os) throws Exception {
        String[] arrays;
        arrays = getFileName(path);
        if (arrays == null) {
            ExceptionUtils.throwError("文件目录或文件不存在");
        }
        connectFtpServer(ftp -> {
            try {
                FTPFile[] fs = ftp.listFiles();
                int len$ = fs.length;
                int i$ = 0;
                do {
                    if (i$ >= len$) break;
                    FTPFile ff = fs[i$];
                    if (ff.getName().equals(arrays[1])) {
                        ftp.retrieveFile(ff.getName(), os);
                        break;
                    }
                    i$++;
                } while (true);
            } catch (Exception ex) {
                ExceptionUtils.throwError("连错误的Unicode字符串!");
            }
        });
    }

    private void connectFtpServer(Consumer<FTPClient> opt) throws Exception {
        FTPClient ftp = new FTPClient();
        ftp.connect(ftpURL, ftpPort);
        if (!ftp.login(ftpAdmin, ftpPassword)) {
            ExceptionUtils.throwError("FTP连接异常");
        }
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply))
            ftp.disconnect();
        ftp.changeWorkingDirectory(this.ftpRootPath);

        ftp.setBufferSize(1024);
        ftp.setControlEncoding(AppConstant.DEFAULT_CHARSET);
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
        conf.setServerLanguageCode("zh");
        ftp.enterLocalPassiveMode();
        ftp.setFileType(2);

        opt.accept(ftp);
        try {
            ftp.logout();
        } finally {
            try {
                if (ftp.isConnected()) {
                    ftp.disconnect();
                }
            } catch (IOException e) {
                ExceptionUtils.throwError("关闭FTP连接发生异常！", e);
            }
        }
    }

    private String checkFileName(MultipartFile multipartFile) {
        if (multipartFile == null) {
            ExceptionUtils.throwError("未获取到上传文件");
        }
        String contentType = multipartFile.getOriginalFilename();
        if (StringUtils.isEmpty(contentType)) {
            ExceptionUtils.throwError("不支持的文件格式");
        }
        int n = contentType.lastIndexOf(".");
        if (n == -1) {
            ExceptionUtils.throwError("不支持的文件格式");
        }
        String fileType = contentType.substring(n + 1);

        boolean isAllow = false;
        for (String format : fileFormat.split(",")) {
            if (fileType.equalsIgnoreCase(format)) {
                isAllow = true;
                break;
            }
        }
        if (!isAllow) {
            ExceptionUtils.throwError("不允许格式[" + fileType + "]上传");
        }
        if (multipartFile.getSize() > maxFileSize) {
            ExceptionUtils.throwError("文件大小不能超过" + maxFileSize / 1024 / 1024 + "M");
        }
        return fileType;
    }

    private String createFileNameByType(String fileType) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        return format.format(new Date()) + "." + fileType;
    }

    private String[] getFileName(String path) {
        if (path.startsWith("/upload/files/")) {
            path = path.substring("/upload/files/".length());
            String[] arrays = path.split("/");
            StringBuilder pathBuilder = new StringBuilder();
            for (int i = 0; i < arrays.length - 1; i++)
                pathBuilder.append("/").append(arrays[i]);
            path = pathBuilder.toString();
            return (new String[]{
                    path, arrays[arrays.length - 1]
            });
        } else {
            return null;
        }
    }

}

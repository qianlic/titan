package com.cjwx.titan.engine.util.file;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.core.exception.ServiceException;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 文件操作工具类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
public class FileTool {

    private FTPClient ftp;

    private String ftpURL;
    private int ftpPort;
    private String ftpAdmin;
    private String ftpPassword;
    private String ftpRootPath;
    private Long maxFileSize;
    private String fileFormat;

    public String uploadFile(MultipartFile multipartFile) {
        String fileName = "";
        try {
            fileName = new String(multipartFile.getOriginalFilename().getBytes("ISO8859-1"), HttpConstant.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e1) {
        }
        int n = fileName.lastIndexOf(".");
        if (n == -1) {
            throw new ServiceException("文件:[" + fileName + "]格式不符");
        }
        String fileType = fileName.substring(n + 1);

        boolean isAllow = false;
        for (String format : fileFormat.split(",")) {
            if (fileType.equalsIgnoreCase(format)) {
                isAllow = true;
                break;
            }
        }

        if (!isAllow) {
            throw new ServiceException("文件:[" + fileName + "]格式不符");
        }
        fileName = new Date().getTime() + "." + fileType;
        File file = new File("/" + fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException e) {
            throw new ServiceException("文件上传异常", e);
        } catch (IOException e) {
            throw new ServiceException("文件上传异常", e);
        }
        return fileName;
    }

    public String uploadFileToFtpServer(MultipartFile multipartFile) throws Exception {
        String fileName = null;

        if (multipartFile == null) {
            throw new Exception("未获取到上传文件");
        }
        String contentType = multipartFile.getOriginalFilename();
        int n = contentType.lastIndexOf(".");
        if (n == -1) {
            throw new Exception("不支持的文件格式");
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
            throw new Exception("不允许格式[" + fileType + "]上传");
        }
        if (multipartFile.getSize() > maxFileSize) {
            throw new Exception("文件大小不能超过" + maxFileSize / 1024 / 1024 + "M");
        }

        FileInputStream fis = null;

        try {
            ftp = new FTPClient();
            try {
                ftp.connect(ftpURL, ftpPort);
                if (!ftp.login(ftpAdmin, ftpPassword)) {
                    throw new Exception("FTP连接异常");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 设置上传目录
            ftp.changeWorkingDirectory(this.ftpRootPath);
            ftp.setBufferSize(1024);
            ftp.setControlEncoding(HttpConstant.DEFAULT_CHARSET);
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");
            ftp.enterLocalPassiveMode();
            ftp.setFileType(2);
            fileName = createFileName(multipartFile.getOriginalFilename());
            ftp.storeFile(fileName, multipartFile.getInputStream());
            try {
                ftp.logout();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ftp.isConnected()) {
                        ftp.disconnect();
                    }
                } catch (IOException e) {
                    throw new Exception("关闭FTP连接发生异常！", e);
                }
            }
            return fileName;
        } catch (IOException e) {
            throw new Exception("文件上传异常", e);
        } finally {
            IOUtils.closeQuietly(fis);
        }
    }

    public void downloadFileToFtpServer(String path, OutputStream os) throws Exception {
        String arrays[];
        arrays = getFileName(path);
        if (arrays == null)
            throw new Exception("文件目录或文件不存在");
        try {
            ftp = new FTPClient();
            try {
                ftp.connect(ftpURL, ftpPort);
                if (!ftp.login(ftpAdmin, ftpPassword)) {
                    throw new Exception("FTP连接异常");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
                ftp.disconnect();
            ftp.changeWorkingDirectory(this.ftpRootPath);

            ftp.setBufferSize(1024);
            ftp.setControlEncoding(HttpConstant.DEFAULT_CHARSET);
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");
            ftp.enterLocalPassiveMode();
            ftp.setFileType(2);

            FTPFile fs[] = ftp.listFiles();
            FTPFile arr$[] = fs;
            int len$ = arr$.length;
            int i$ = 0;
            do {
                if (i$ >= len$) break;
                FTPFile ff = arr$[i$];
                if (ff.getName().equals(arrays[1])) {
                    ftp.retrieveFile(ff.getName(), os);
                    break;
                }
                i$++;
            } while (true);

            try {
                ftp.logout();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ftp.isConnected()) {
                        ftp.disconnect();
                    }
                } catch (IOException e) {
                    throw new Exception("关闭FTP连接发生异常！", e);
                }
            }
        } catch (IOException e) {
            throw new Exception("连错误的Unicode字符串!");
        }
    }

    public void openFtpConnect() throws Exception {
        ftp = new FTPClient();
        try {
            ftp.connect(ftpURL, ftpPort);
            if (!ftp.login(ftpAdmin, ftpPassword)) {
                throw new Exception("FTP连接异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeFtpConnect() throws Exception {
        try {
            ftp.logout();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftp.isConnected()) {
                    ftp.disconnect();
                }
            } catch (IOException e) {
                throw new Exception("关闭FTP连接发生异常！", e);
            }
        }
    }

    private String createFileName(String fileName) throws Exception {
        int n = fileName.lastIndexOf(".");
        if (n == -1) {
            throw new Exception("文件:[" + fileName + "]格式不符");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileType = fileName.substring(n + 1);
        return format.format(new Date()) + "." + fileType;
    }

    private String[] getFileName(String path) {
        if (path.startsWith("/upload/files/")) {
            path = path.substring("/upload/files/".length());
            String arrays[] = path.split("/");
            path = "";
            for (int i = 0; i < arrays.length - 1; i++)
                path = (new StringBuilder()).append(path).append("/").append(arrays[i]).toString();

            return (new String[]{
                    path, arrays[arrays.length - 1]
            });
        } else {
            return null;
        }
    }
}

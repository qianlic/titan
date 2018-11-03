package com.cjwx.titan.engine.config;

import com.cjwx.titan.engine.util.file.FileTool;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月02日 16:38
 */
@Data
@Configuration
@PropertySource("classpath:config/sys.properties")
@ConfigurationProperties("ftp")
public class FileToolConfiguration {

    private String ftpURL;
    private Integer ftpPort;
    private String ftpAdmin;
    private String ftpPassword;
    private String ftpRootPath;
    private Long maxFileSize;
    private String fileFormat;

    @Bean
    public FileTool fileTool() {
        FileTool fileTool = new FileTool();
        fileTool.setFtpURL(ftpURL);
        fileTool.setFtpPort(ftpPort);
        fileTool.setFtpAdmin(ftpAdmin);
        fileTool.setFtpPassword(ftpPassword);
        fileTool.setFtpRootPath(ftpRootPath);
        fileTool.setMaxFileSize(maxFileSize);
        fileTool.setFileFormat(fileFormat);
        return fileTool;
    }

}

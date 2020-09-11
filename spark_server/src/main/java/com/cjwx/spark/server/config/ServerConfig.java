package com.cjwx.spark.server.config;

import com.cjwx.spark.engine.config.ApplicationConfig;
import com.cjwx.spark.engine.config.WebMvcConfig;
import com.cjwx.spark.engine.util.file.FileTool;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月03日 10:20
 */
@Data
@Component
@Configuration
@ConfigurationProperties("common.ftp")
@Import({ApplicationConfig.class, WebMvcConfig.class})
@ComponentScan("com.cjwx.spark.server.zuul")
public class ServerConfig {

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

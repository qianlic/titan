package com.cjwx.titan.server.config;

import com.cjwx.titan.engine.config.ApplicationConfiguration;
import com.cjwx.titan.engine.config.WebMvcConfiguration;
import com.cjwx.titan.engine.util.file.FileTool;
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
@Import({ApplicationConfiguration.class, WebMvcConfiguration.class})
@ComponentScan("com.cjwx.titan.server.zuul")
public class ServerConfiguration {

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

package com.cjwx.titan.boot.config;

import com.cjwx.titan.engine.util.file.FileTool;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;

/**
 * @Description: 核心功能引入
 * @Author: qian li
 * @Date: 2018年10月30日 15:58
 */
@Data
@Configuration
@Import({HibernateConfiguration.class, RedisConfiguration.class})
@ImportResource({"classpath:spring-quartz.xml", "classpath:spring-server.xml"})
@ConfigurationProperties("ftp")
@PropertySource("classpath:config/sys.properties")
@ComponentScan({"com.cjwx.titan.engine.core", "com.cjwx.titan.server", "com.cjwx.titan.crawler", "com.cjwx.titan.monitor"})
public class ApplicationConfiguration {

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

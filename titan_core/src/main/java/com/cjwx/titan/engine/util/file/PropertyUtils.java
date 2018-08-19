package com.cjwx.titan.engine.util.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

import java.util.*;

/**
 * @Description: 配置文件参数操作
 * @Author: qian li
 * @Date: 2018年03月29日 13:18
 */
@Slf4j
public class PropertyUtils extends PropertyPlaceholderConfigurer {

    private static Map<String, String> ctxPropertiesMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) {
        super.processProperties(beanFactoryToProcess, props);
        ctxPropertiesMap = new HashMap<>();
        props.forEach((k, v) -> ctxPropertiesMap.put(k.toString(), v.toString()));
    }

    @Override
    public void setLocations(Resource[] locations) {
        super.setLocations(locations);
        Arrays.asList(locations).forEach((location) -> log.debug("正在装载配置文件【" + location.getFilename() + "】"));
    }

    /**
     * 获取参数值
     */
    public static String getProperty(String key) {
        return ctxPropertiesMap.get(key);
    }

    /**
     * 设置参数值
     */
    public synchronized void setProperty(String key, String value) {
        ctxPropertiesMap.put(key, value);
    }

}
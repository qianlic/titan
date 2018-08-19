package com.cjwx.titan.engine.core.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: 应用环境操作助手
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Slf4j
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static AutowireCapableBeanFactory getAutowireCapableBeanFactory() {
        return applicationContext.getAutowireCapableBeanFactory();
    }

}

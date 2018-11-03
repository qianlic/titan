package com.cjwx.titan.quartz;

import com.cjwx.titan.engine.web.helper.ApplicationContextHelper;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * @Description: 用于对Job注入ApplicationContext等.
 * @Author: qian li
 * @Date: 2018年03月29日 19:09
 */
public class JobFactory extends SpringBeanJobFactory {

    /**
     * 创建JOB实例, 并注解相关内容等
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object instance = super.createJobInstance(bundle);
        ApplicationContextHelper.getAutowireCapableBeanFactory().autowireBean(instance);
        return instance;
    }

}
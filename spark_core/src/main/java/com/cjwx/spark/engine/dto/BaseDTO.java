package com.cjwx.spark.engine.dto;

import com.cjwx.spark.engine.entity.AbstractEntity;
import org.apache.commons.beanutils.BeanUtils;


/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月11日 16:57
 */
public class BaseDTO {

    public static BaseDTO forEntity(AbstractEntity entity) throws Exception {
        BaseDTO dto = new BaseDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}

package com.cjwx.spark.engine.dto;

import com.cjwx.spark.engine.entity.BaseEntity;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;

/**
 * @Description: dto
 * @Author: qian li
 * @Date: 2020年09月11日 16:57
 */
@Data
public class BaseDTO implements Serializable {

    private Long id;

    private Boolean status;

    public static BaseDTO forEntity(BaseEntity entity) throws Exception {
        BaseDTO dto = new BaseDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}

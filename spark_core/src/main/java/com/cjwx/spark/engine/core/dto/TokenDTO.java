package com.cjwx.spark.engine.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: JWT TOKEN
 * @Author: qian li
 * @Date: 2018年03月31日 16:20
 */
@Data
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = -9017410322938026917L;

    private String userCode;

    private String userName;

    private String sex;

    private String imgUrl;

    private String token;

    private List<String> promises;

    public void addPromise(String promise) {
        if (promises == null) {
            promises = new ArrayList<>();
        }
        promises.add(promise);
    }

}

package com.cjwx.titan.engine.core.base.handler;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.core.web.http.Result;
import com.cjwx.titan.engine.core.web.http.ResultStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 默认返回信息
 * @Author: qian li
 * @Date: 2018年08月18日 11:57
 */
@RestController
public class DefaultHandler {

    @RequestMapping(value = HttpConstant.DEFAULT_FIX)
    public Result error() {
        return new Result(ResultStatus.STATUS_1, "远程服务未被发现！");
    }

}

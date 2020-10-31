package com.cjwx.spark.server.handler;

import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.ResultUtils;
import com.cjwx.spark.server.dto.MemoryDTO;
import com.cjwx.spark.server.helper.MemoryHelper;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import io.swagger.annotations.Api;

/**
 * @Author: qian li
 * @Date: 2018年08月18日 5:39
 */
@Api(tags = "系统监控-内存管理")
@RestHandler("/memory/")
public class MemoryHandler {

    @RestMethod("info")
    public ResultDTO<MemoryDTO> info() throws Exception {
        return ResultUtils.success(MemoryHelper.getMemoryInformation());
    }

}

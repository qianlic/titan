package com.cjwx.spark.server.handler;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.ResultUtils;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.server.dto.ThreadDTO;
import com.cjwx.spark.server.helper.ThreadHelper;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: qian li
 * @Date: 2018年08月18日 5:39
 */
@Api(tags = "系统监控-线程管理")
@RestHandler("/thread/")
public class ThreadHandler {

    @RestMethod("list")
    public ResultDTO<PageDTO<ThreadDTO>> list(@RequestBody ThreadDTO thread) {
        return ResultUtils.success(ThreadHelper.findThreadList(thread, thread.getStart(), thread.getSize()));
    }

    @RestMethod("interrupt")
    public ResultDTO<Integer> interrupt(@RequestBody List<Long> ids) {
        return ResultUtils.success(ThreadHelper.interruptThread(ids));
    }

}

package com.cjwx.spark.server.handler;

import com.cjwx.spark.engine.util.PageList;
import com.cjwx.spark.engine.util.file.ExcelUtils;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.server.entity.ThreadBean;
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
    public PageList<ThreadBean> list(@RequestBody ThreadBean model) {
        return ThreadHelper.findThreadList(model.getStart(), model.getSize(),
                null
                //model.getParams()
        );
    }

    @RestMethod("download")
    public void download(@RequestBody ThreadBean model) {
        String[] title = {"线程编号", "线程名"};
        String[][] data = null;//ThreadHelper.findThreadList(model.getParams());
        ExcelUtils.download(title, data);
    }

    @RestMethod("interrupt")
    public int interrupt(@RequestBody List<Long> ids) {
        return ThreadHelper.interruptThread(ids);
    }

}

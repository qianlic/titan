package com.cjwx.titan.quartz.handler;

import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.entity.QtzTriggerEntity;
import com.cjwx.titan.quartz.service.TriggerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 定时任务-触发器管理
 * @Author: qian li
 * @Date: 2018年08月01日 16:12
 */
@RestController
@RequestMapping(value = "/system/trigger/", method = RequestMethod.POST)
public class TriggerHandler {

    @Resource
    private TriggerService triggerService;

    @RequestMapping("list")
    public PageList<QtzTriggerEntity> list(@RequestBody Model model) {
        return triggerService.getTriggerList(model.getStart(), model.getSize(), model.getParams());
    }

    @RequestMapping("create")
    public void create(@RequestBody QtzTriggerEntity tigger) {
        triggerService.createTrigger(tigger);
    }

    @RequestMapping("remove")
    public void remove(@RequestBody QtzTriggerEntity tigger) {
        triggerService.delete(tigger);
    }

    @RequestMapping("pause")
    public void pause(@RequestBody QtzTriggerEntity tigger) {
        triggerService.pause(tigger);
    }

    @RequestMapping("resume")
    public void resume(@RequestBody QtzTriggerEntity tigger) {
        triggerService.resume(tigger);
    }

}

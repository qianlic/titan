package com.cjwx.spark.activiti;

import com.cjwx.spark.activiti.service.ActivitiService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年04月27日  17:08
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ActivitiTest {

    @Autowired
    private ActivitiService activityService;

    @Resource
    private TaskService taskService;

    @Test
    public void test() {
        start();
    }

    private void query() {
        List<Task> r = taskService.createTaskQuery().list();
        r.forEach(t -> {
            activityService.setTaskAssignee(t.getId(), "asdadaada");
        });
        System.out.println(r);
    }

    private void start() {
        ProcessInstance r = activityService.startProcessInstance("test", null);
        System.out.println(r);
    }

    private void deploy() {
        activityService.deploy("process/test.bpmn");
    }

}

package com.cjwx.titan.activity;

import com.cjwx.titan.activity.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年04月27日  17:08
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ActivityTest {

    @Autowired
    private ActivityService activityService;

    @Test
    public void test() {
        activityService.deploy("process/test.bpmn");
    }

}


package com.cjwx.spark.server;

import com.cjwx.spark.server.service.ResourceService;
import com.cjwx.spark.server.service.RoleService;
import com.cjwx.spark.server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRepository {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    @Test
    public void test() throws Exception {


    }

}



package com.cjwx.spark.server;

import com.cjwx.spark.engine.entity.SysResourceEntity;
import com.cjwx.spark.engine.entity.SysUserEntity;
import com.cjwx.spark.engine.repository.ResourceRepository;
import com.cjwx.spark.server.dto.SysUserDTO;
import com.cjwx.spark.server.service.ResourceService;
import com.cjwx.spark.server.service.RoleService;
import com.cjwx.spark.server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

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
        SysUserEntity user = userService.findUserByCode("admin");

        SysUserDTO dto = (SysUserDTO) SysUserDTO.forEntity(user);

        user.setRoles(roleService.getRoleList());
        List<SysResourceEntity> s = resourceService.getResourceList(true);
        System.out.println(s);

    }

}


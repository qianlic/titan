/*
package com.cjwx.spark.monitor;

import com.cjwx.spark.engine.entity.SysUserEntity;
import com.cjwx.spark.engine.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTest {

    @Autowired
    private UserRepository userMapper;

    @Test
    public void test() {
        //SysRoleBean role = roleRepository.findById(386L).get();

        SysUserEntity user = userMapper.findByUsercode("lufei");

        //user.setRoles(Arrays.asList(role));

        //userMapper.save(user);
        System.out.println(user);
    }

}
*/

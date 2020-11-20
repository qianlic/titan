
package com.cjwx.spark.quartz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjwx.spark.quartz.entity.QtzJobKey;
import com.cjwx.spark.quartz.repository.JobRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRepository {

    @Resource
    private JobRepository jobRepository;

    @Test
    public void test() {
        QueryWrapper<QtzJobKey> wrapper = new QueryWrapper<>();
        wrapper.like("job_name", "t");
        IPage<QtzJobKey> page = jobRepository.selectPage(new Page<>(0, 10), wrapper);
        List<QtzJobKey> jobKeys = page.getRecords();
        System.out.println(jobKeys);
    }

}


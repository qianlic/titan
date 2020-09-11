package com.cjwx.spark.engine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * @Description: 审计
 * @Author: qian li
 * @Date: 2018年10月30日 15:58
 */
@EnableJpaAuditing
@Configuration
public class AuditorConfig implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.empty();
    }

}

package com.erictao.aegis.aegiscore;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author TJX
 * @Title: CacheConditionConfig
 * @date 2018/7/2919:04
 */
@Slf4j
@Configuration
public class CheckRedisCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //如果没有加入redis配置的就返回false
        String property = context.getEnvironment().getProperty("spring.redis.host");
        if (StringUtils.isEmpty(property)){
            log.warn("Need to configure redis!");
            return false ;
        }else {
            return true;
        }
    }
}

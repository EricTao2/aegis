package com.erictao.aegis.api;

import com.erictao.aegis.api.advice.processor.ModifyParameterProcessor;
import com.erictao.aegis.api.advice.processor.ReplayAttackProcessor;
import com.erictao.aegis.api.advice.processor.ReqTimeoutProcessor;
import com.erictao.aegis.api.advice.processor.impl.CheckModifyParameterProcessor;
import com.erictao.aegis.api.advice.processor.impl.CheckReqNoProcessor;
import com.erictao.aegis.api.advice.processor.impl.CheckReqTimeoutProcessor;
import com.erictao.aegis.api.localcache.CacheSet;
import com.erictao.aegis.api.properties.AegisApiProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author TJX
 * @Title: AegisApiConfigration
 * @date 2018/7/2710:18
 */
@ComponentScan
@Configuration
@EnableConfigurationProperties(AegisApiProperties.class)
public class AegisApiConfig {

    /**
     * 运行时的参数Properties对象
     */
    @Autowired
    private AegisApiProperties aegisApiProperties;


    @Bean
    @ConditionalOnMissingBean(ReplayAttackProcessor.class)
    public ReplayAttackProcessor replayAttackProcessor(@Autowired RedisTemplate<String, String> aegisRedisTemplate,
                                                       CacheSet cacheSet, ApplicationContext context) {
        boolean useRedis = false;
        //如果没有加入redis配置的就返回false
        String property = context.getEnvironment().getProperty("spring.redis.host");
        if (StringUtils.isNotBlank(property) && aegisApiProperties.isUseRedis()){
            useRedis = true ;
        }
        return new CheckReqNoProcessor(aegisRedisTemplate, cacheSet, aegisApiProperties, useRedis);
    }
    @Bean
    @ConditionalOnMissingBean(ReqTimeoutProcessor.class)
    public ReqTimeoutProcessor reqTimeoutProcessor() {
        return new CheckReqTimeoutProcessor(aegisApiProperties);
    }

    @Bean
    @ConditionalOnMissingBean(ModifyParameterProcessor.class)
    public ModifyParameterProcessor modifyParameterProcessor() {
        return new CheckModifyParameterProcessor(aegisApiProperties);
    }

    @Bean
    public CacheSet localCache(){
        return new CacheSet(aegisApiProperties.getReplayAttacks().getReqNoTimeout());
    }


}

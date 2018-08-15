package com.erictao.aegis.aegiscore;

import com.erictao.aegis.aegiscore.advice.processor.ModifyParameterProcessor;
import com.erictao.aegis.aegiscore.advice.processor.ReqTimeoutProcessor;
import com.erictao.aegis.aegiscore.advice.processor.impl.CheckModifyParameterProcessor;
import com.erictao.aegis.aegiscore.advice.processor.impl.CheckReqNoProcessor;
import com.erictao.aegis.aegiscore.advice.processor.ReplayAttackProcessor;
import com.erictao.aegis.aegiscore.advice.processor.impl.CheckReqTimeoutProcessor;
import com.erictao.aegis.aegiscore.localcache.CacheMap;
import com.erictao.aegis.aegiscore.properties.AegisApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AegisApiConfig {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**
     * 运行时的参数Properties对象
     */
    @Autowired
    private AegisApiProperties aegisApiProperties;


    @Bean
    @ConditionalOnMissingBean(ReplayAttackProcessor.class)
    public ReplayAttackProcessor replayAttackProcessor() {
        return new CheckReqNoProcessor(redisTemplate, aegisApiProperties);
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
    @Conditional(CheckRedisCondition.class)
    public CacheMap localCache(){
        return new CacheMap(aegisApiProperties.getReplayAttacks().getReqNoTimeout());
    }


}

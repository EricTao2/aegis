package com.github.erictao2.aegis.api.advice.processor.impl;

import com.github.erictao2.aegis.api.advice.processor.ReplayAttackProcessor;
import com.github.erictao2.aegis.api.annotation.DefendReplay;
import com.github.erictao2.aegis.api.exception.AegisApiException;
import com.github.erictao2.aegis.api.localcache.CacheSet;
import com.github.erictao2.aegis.api.properties.AegisApiProperties;
import com.github.erictao2.aegis.core.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author TJX
 * @Title: CheckReqNoProcessor
 * @date 2018/7/2815:34
 */
@Slf4j
public class CheckReqNoProcessor implements ReplayAttackProcessor {


    private RedisTemplate<String, String> redisTemplate;

    private CacheSet cacheSet;

    private String ReqNoName;

    private boolean useRedis = false;

    private String reqNoRedisPrefix;

    private TimeUnit timeUnit;

    private Long timeout;

    public CheckReqNoProcessor(RedisTemplate<String,String> redisTemplate, CacheSet cacheSet,
                               AegisApiProperties aegisApiProperties, boolean useRedis) {
        this.redisTemplate = redisTemplate;
        this.cacheSet = cacheSet;
        this.ReqNoName = aegisApiProperties.getReplayAttacks().getReqNoName();
        this.reqNoRedisPrefix = aegisApiProperties.getReplayAttacks().getReqNoRedisPrefix();
        if (StringUtils.equalsIgnoreCase("s", aegisApiProperties.getReplayAttacks().getTimeUnit())) {
            this.timeUnit = TimeUnit.SECONDS;
        } else {
            this.timeUnit = TimeUnit.MILLISECONDS;
        }
        this.timeout = aegisApiProperties.getReplayAttacks().getReqNoTimeout();
        this.useRedis = useRedis;
    }

    @Override
    public void process(JoinPoint joinPoint, HttpServletRequest request,
                        DefendReplay ann) {
        log.info("开始重放攻击检查: IpAdrress=" +  RequestUtils.getIpAdrress(request));

        String ReqNoName = StringUtils.isBlank(ann.name()) ? this.ReqNoName : ann.name();
        String reqNoRedisPrefix = StringUtils.isBlank(ann.prefix()) ?  this.reqNoRedisPrefix : ann.prefix();
        TimeUnit timeUnit = ann.timeUnit().length == 0 ? this.timeUnit : ann.timeUnit()[0];
        Long timeout = ann.timeout() > 0L ? ann.timeout() : this.timeout;

        String reqNo = request.getParameter(ReqNoName);
        if(StringUtils.isEmpty(reqNo)){
            log.error("缺少参数:" + ReqNoName);
            throw new AegisApiException("缺少参数:" + ReqNoName);

        }
        String cacheKey = reqNoRedisPrefix + reqNo;
        if (useRedis) {
            checkByRedis(reqNo, cacheKey);
        } else {
            checkByLocalCache(reqNo, cacheKey);
        }
    }

    private  void checkByRedis(String reqNo, String cacheKey) {
        try {

            String oldReqNo = redisTemplate.opsForValue().get(cacheKey);
            log.info("ReqNo=" + reqNo);
            if (StringUtils.isNotEmpty(oldReqNo)) {
                throw new AegisApiException("请求号重复,"+ ReqNoName +"=" + reqNo, HttpStatus.CONFLICT);
            } else {
                redisTemplate.opsForValue().set(cacheKey, reqNo, timeout, timeUnit);
            }
        } catch (RedisConnectionFailureException e){
            log.error("redis操作异常",e);
            throw new AegisApiException("need redisService", HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    private void checkByLocalCache(String reqNo, String cacheKey) {
            if (cacheSet.contains(cacheKey)) {
                throw new AegisApiException("请求号重复,"+ ReqNoName +"=" + reqNo, HttpStatus.CONFLICT);
            } else {
                cacheSet.add(cacheKey);
            }
    }

}

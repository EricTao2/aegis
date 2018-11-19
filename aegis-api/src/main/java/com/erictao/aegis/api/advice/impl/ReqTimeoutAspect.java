package com.erictao.aegis.api.advice.impl;

import com.erictao.aegis.api.advice.HttpRequestAspect;
import com.erictao.aegis.api.advice.processor.ReqTimeoutProcessor;
import com.erictao.aegis.api.annotation.DefendTimeout;
import com.erictao.aegis.core.advice.AbstractAspect;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author TJX
 * @Title: TimeoutAspect
 * @date 2018/7/3017:23
 */
@Data
@Aspect
@Component
@Order(3)
public class ReqTimeoutAspect extends AbstractAspect implements HttpRequestAspect {

    @Autowired
    protected ReqTimeoutProcessor processor;

    /**
     * 切面该注解
     */
    @Pointcut("@annotation(com.erictao.aegis.api.annotation.DefendTimeout)" +
            "||@within(com.erictao.aegis.api.annotation.DefendTimeout)")
    public void check(){
    }

    @Before("check()&& @annotation(ann)")
    public void beforeMethod(JoinPoint joinPoint, DefendTimeout ann) throws Exception {
        doProcess(joinPoint, ann);
    }

    @Before("check()&&@within(ann))")
    public void beforeClass(JoinPoint joinPoint, DefendTimeout ann) throws Exception {
        doProcess(joinPoint, ann);
    }

}

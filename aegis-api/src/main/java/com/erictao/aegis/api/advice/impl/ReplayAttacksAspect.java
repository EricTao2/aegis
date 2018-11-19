package com.erictao.aegis.api.advice.impl;

import com.erictao.aegis.api.advice.HttpRequestAspect;
import com.erictao.aegis.api.advice.processor.ReplayAttackProcessor;
import com.erictao.aegis.api.annotation.DefendReplay;
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
 * @Title: ReNoAspect
 * @date 2018/7/2714:06
 */
@Data
@Aspect
@Component
@Order(4)
public class ReplayAttacksAspect extends AbstractAspect implements HttpRequestAspect {



    @Autowired
    private ReplayAttackProcessor processor;


    /**
     * 切面该注解
     */
    @Pointcut("@annotation(com.erictao.aegis.api.annotation.DefendReplay)" +
            "||@within(com.erictao.aegis.api.annotation.DefendReplay)")
    public void check(){
    }

    @Before("check()&& @annotation(ann)")
    public void beforeMethod(JoinPoint joinPoint, DefendReplay ann) throws Exception {
        doProcess(joinPoint, ann);
    }

    @Before("check()&&@within(ann))")
    public void beforeClass(JoinPoint joinPoint, DefendReplay ann) throws Exception {
        doProcess(joinPoint, ann);
    }


}

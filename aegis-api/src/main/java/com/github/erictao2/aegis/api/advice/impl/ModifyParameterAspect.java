package com.github.erictao2.aegis.api.advice.impl;

import com.github.erictao2.aegis.api.advice.HttpRequestAspect;
import com.github.erictao2.aegis.api.advice.processor.ModifyParameterProcessor;
import com.github.erictao2.aegis.api.annotation.DefendModify;
import com.github.erictao2.aegis.core.advice.AbstractAspect;
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
@Order(2)
public class ModifyParameterAspect extends AbstractAspect implements HttpRequestAspect {

    @Autowired
    protected ModifyParameterProcessor processor;

    /**
     * 切面该注解
     */
    @Pointcut("@annotation(com.github.erictao2.aegis.api.annotation.DefendModify)" +
            "||@within(com.github.erictao2.aegis.api.annotation.DefendModify)")
    public void check(){
    }

    @Before("check()&& @annotation(ann)")
    public void beforeMethod(JoinPoint joinPoint, DefendModify ann) throws Exception {
        doProcess(joinPoint, ann);
    }

    @Before("check()&&@within(ann))")
    public void beforeClass(JoinPoint joinPoint, DefendModify ann) throws Exception {
        doProcess(joinPoint, ann);
    }
}

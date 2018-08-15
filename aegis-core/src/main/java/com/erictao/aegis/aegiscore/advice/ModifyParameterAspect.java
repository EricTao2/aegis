package com.erictao.aegis.aegiscore.advice;

import com.erictao.aegis.aegiscore.advice.processor.ModifyParameterProcessor;
import com.erictao.aegis.aegiscore.annotation.DefendModify;
import com.erictao.aegis.aegiscore.annotation.DefendTimeout;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Inherited;

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
    @Pointcut("@annotation(com.erictao.aegis.aegiscore.annotation.DefendModify)" +
            "||@within(com.erictao.aegis.aegiscore.annotation.DefendModify)")
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

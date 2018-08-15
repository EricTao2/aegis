package com.erictao.aegis.aegiscore.advice;

import com.erictao.aegis.aegiscore.advice.processor.ReqTimeoutProcessor;
import com.erictao.aegis.aegiscore.annotation.DefendReplay;
import com.erictao.aegis.aegiscore.annotation.DefendTimeout;
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
    @Pointcut("@annotation(com.erictao.aegis.aegiscore.annotation.DefendTimeout)" +
            "||@within(com.erictao.aegis.aegiscore.annotation.DefendTimeout)")
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

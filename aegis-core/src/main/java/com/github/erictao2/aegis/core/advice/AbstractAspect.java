package com.github.erictao2.aegis.core.advice;

import com.github.erictao2.aegis.core.advice.processor.AegisAdviceProcessor;
import com.github.erictao2.aegis.core.utils.RequestUtils;
import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * @author TJX
 * @Title: AbstractAspect
 * @date 2018/7/3017:28
 */

public abstract class AbstractAspect {


    protected void doProcess(JoinPoint joinPoint, Annotation ann) throws Exception {
        HttpServletRequest request = RequestUtils.getRequest(joinPoint);
        getProcessor().process(joinPoint, request, ann);
    }

    public abstract AegisAdviceProcessor getProcessor();
}

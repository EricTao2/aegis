package com.erictao.aegis.aegiscore.advice;

import com.erictao.aegis.aegiscore.Utils.RequestUtils;
import com.erictao.aegis.aegiscore.advice.processor.AegisApiProcessor;
import lombok.Data;
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

    public abstract  AegisApiProcessor getProcessor();
}

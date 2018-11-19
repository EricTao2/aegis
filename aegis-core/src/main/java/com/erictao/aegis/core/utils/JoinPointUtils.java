package com.erictao.aegis.core.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

/**
 * @author TJX
 * @Title: JoinPointUtils
 * @date 2018/7/2718:15
 */
public class JoinPointUtils {

    public static String toSignatureString(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Class[] parameterTypes = ((CodeSignature) joinPoint
                .getSignature()).getParameterTypes();
        String[] parameterNames = ((CodeSignature) joinPoint
                .getSignature()).getParameterNames();
        StringBuilder sb = new StringBuilder();
        sb.append(methodName).append("(");
        for (int i = 0; i < parameterTypes.length; i++) {
            sb.append(parameterTypes[i].getSimpleName()).append(" ").append(parameterNames[i]);
            if (i != parameterTypes.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static String toSignatureStringWithClassName(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getCanonicalName();
        String methodSignatureString =  JoinPointUtils.toSignatureString(joinPoint);
        return className + ": " + methodSignatureString;
    }
}

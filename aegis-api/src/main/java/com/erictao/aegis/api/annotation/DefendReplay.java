package com.erictao.aegis.api.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author TJX
 * @Title: DefendReplay
 * @date 2018/7/2714:17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefendReplay {


    long timeout() default -1L;

    String prefix() default "";

    String name() default "";

    TimeUnit[] timeUnit() default {};


}

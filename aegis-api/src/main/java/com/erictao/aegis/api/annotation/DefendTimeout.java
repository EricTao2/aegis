package com.erictao.aegis.api.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author TJX
 * @Title: DefendTimeout
 * @date 2018/7/3018:19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefendTimeout {

    long timeout() default -1L;

    String name() default "";

    TimeUnit[] timeUnit() default {};
}

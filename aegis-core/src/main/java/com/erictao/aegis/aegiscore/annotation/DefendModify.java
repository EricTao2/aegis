package com.erictao.aegis.aegiscore.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author TJX
 * @Title: DefendTimeout
 * @date 2018/7/3018:19
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefendModify {

    String name() default "";

    String algorithm() default "";

    String key() default "";

}

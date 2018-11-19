package com.erictao.aegis.api.annotation;

import java.lang.annotation.*;

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

package com.kunyao.logging.trace.annotation;

import com.kunyao.logging.trace.LogConstant;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LogTrace {

    String key() default LogConstant.TRACE_ID;

    String value() default "";
}

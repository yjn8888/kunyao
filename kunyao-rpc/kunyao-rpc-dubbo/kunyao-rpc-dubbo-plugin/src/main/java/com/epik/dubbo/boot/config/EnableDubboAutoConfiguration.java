package com.epik.dubbo.boot.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by wuyu on 2016/10/31.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = { DubboAutoConfiguration.class })
public @interface EnableDubboAutoConfiguration {

}

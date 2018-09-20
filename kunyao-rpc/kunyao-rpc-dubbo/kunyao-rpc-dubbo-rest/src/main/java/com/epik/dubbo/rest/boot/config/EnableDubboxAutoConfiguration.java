package com.epik.dubbo.rest.boot.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * Created by yuanjianing on 2017/02/14.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {DubboxAutoConfiguration.class,TomcatAutoConfiguration.class})
public @interface EnableDubboxAutoConfiguration {

}

package com.kunyao.core.spring.annotation;

import com.kunyao.core.spring.context.ConfigBindingsRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ConfigBindingsRegistrar.class)
public @interface EnableConfigBindings {

    EnableConfigBinding[] value();
}

package com.kunyao.core.spring.annotation;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(ConfigConfigurationSelector.class)
public @interface EnableConfig {
    boolean multiple() default true;

    Class<?> multipleClass() default Object.class;

    Class<?> singleClass() default Object.class;
}

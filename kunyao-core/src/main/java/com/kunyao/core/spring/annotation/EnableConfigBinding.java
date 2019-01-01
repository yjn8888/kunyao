package com.kunyao.core.spring.annotation;


public @interface EnableConfigBinding {
    String prefix();

    Class<?> type();

    boolean multiple() default true;
}

package com.kunyao.message.activemq.annotation;

public @interface Destination {

    String value() default "";

    String queue() default "";

    String topic() default "";
}

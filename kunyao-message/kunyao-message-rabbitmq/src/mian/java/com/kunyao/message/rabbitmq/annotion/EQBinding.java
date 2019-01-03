package com.kunyao.message.rabbitmq.annotion;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface EQBinding {
    String value() default "";

    String exchange() default "";

    String routingKey() default "";
}

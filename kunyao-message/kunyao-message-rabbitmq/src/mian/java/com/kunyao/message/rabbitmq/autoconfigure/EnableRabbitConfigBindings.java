package com.kunyao.message.rabbitmq.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RabbitConfigBindingsRegistrar.class)
public @interface EnableRabbitConfigBindings {
}

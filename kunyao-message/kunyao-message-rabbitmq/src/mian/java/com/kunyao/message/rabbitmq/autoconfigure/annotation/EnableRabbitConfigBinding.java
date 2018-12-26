package com.kunyao.message.rabbitmq.autoconfigure.annotation;

import org.springframework.amqp.core.AbstractDeclarable;

public @interface EnableRabbitConfigBinding {
    String prefix();

    Class<? extends AbstractDeclarable> type();

    boolean multiple() default true;
}

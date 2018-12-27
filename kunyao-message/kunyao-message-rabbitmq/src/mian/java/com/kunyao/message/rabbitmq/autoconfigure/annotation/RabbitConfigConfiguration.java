package com.kunyao.message.rabbitmq.autoconfigure.annotation;

import org.springframework.amqp.core.AbstractDeclarable;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.CustomExchange;

public class RabbitConfigConfiguration {

    /**
     * Multiple Rabbit {@link AbstractDeclarable Config} Bean Binding
     */
    @EnableRabbitConfigBindings({
            @EnableRabbitConfigBinding(prefix = "spring.rabbitmq.queues", type = QueueWrapper.class, multiple = true),
            @EnableRabbitConfigBinding(prefix = "spring.rabbitmq.exchanges", type = ExchangeWrapper.class, multiple = true),
            @EnableRabbitConfigBinding(prefix = "spring.rabbitmq.bindings", type = BindingWrapper.class, multiple = true)
    })
    public static class Multiple {

    }

    @EnableRabbitConfigBindings({@EnableRabbitConfigBinding(
            prefix = "spring.rabbitmq.queue",
            type = QueueWrapper.class, multiple = false
    ), @EnableRabbitConfigBinding(
            prefix = "spring.rabbitmq.exchange",
            type = ExchangeWrapper.class, multiple = false
    ), @EnableRabbitConfigBinding(
            prefix = "spring.rabbitmq.binding",
            type = BindingWrapper.class, multiple = false
    )})
    public static class Single {
        public Single() {
        }
    }
}

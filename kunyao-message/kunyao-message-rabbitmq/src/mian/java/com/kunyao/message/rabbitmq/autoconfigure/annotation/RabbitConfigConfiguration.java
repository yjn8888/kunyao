package com.kunyao.message.rabbitmq.autoconfigure.annotation;

import org.springframework.amqp.core.AbstractDeclarable;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;

public class RabbitConfigConfiguration {

    /**
     * Multiple Rabbit {@link AbstractDeclarable Config} Bean Binding
     */
    @EnableRabbitConfigBindings({
            @EnableRabbitConfigBinding(prefix = "spring.rabbitmq.queues", type = Queue.class, multiple = true),
            @EnableRabbitConfigBinding(prefix = "spring.rabbitmq.exchanges", type = CustomExchange.class, multiple = true),
            @EnableRabbitConfigBinding(prefix = "spring.rabbitmq.bindings", type = Binding.class, multiple = true)
    })
    public static class Multiple {

    }

    @EnableRabbitConfigBindings({@EnableRabbitConfigBinding(
            prefix = "spring.rabbitmq.queue",
            type = Queue.class
    ), @EnableRabbitConfigBinding(
            prefix = "spring.rabbitmq.exchange",
            type = CustomExchange.class
    ), @EnableRabbitConfigBinding(
            prefix = "spring.rabbitmq.binding",
            type = Binding.class
    )})
    public static class Single {
        public Single() {
        }
    }
}

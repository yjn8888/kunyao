package com.kunyao.message.rabbitmq.autoconfigure;

import com.kunyao.core.spring.annotation.EnableConfigBinding;
import com.kunyao.core.spring.annotation.EnableConfigBindings;

public interface RabbitConfigConfigration {
    /**
     *   Multiple Config Beans Binding
     */
    @EnableConfigBindings({
            @EnableConfigBinding(prefix = "spring.rabbitmq.queues", type = QueueWrapper.class),
            @EnableConfigBinding(prefix = "spring.rabbitmq.exchanges", type = ExchangeWrapper.class),
            @EnableConfigBinding(prefix = "spring.rabbitmq.bindings", type = BindingWrapper.class)
    })
    public static class Multiple {

    }

    @EnableConfigBindings({
            @EnableConfigBinding(prefix = "spring.rabbitmq.queue", type = QueueWrapper.class, multiple = false),
            @EnableConfigBinding(prefix = "spring.rabbitmq.exchange", type = ExchangeWrapper.class, multiple = false),
            @EnableConfigBinding(prefix = "spring.rabbitmq.binding", type = BindingWrapper.class, multiple = false)
    })
    public static class Single {
    }
}

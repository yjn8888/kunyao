package com.kunyao.message.rabbitmq.autoconfigure;

import org.springframework.amqp.core.AbstractExchange;

import java.util.Map;

public class DeclareExchange extends AbstractExchange {

    private String type;

    public DeclareExchange(String name) {
        super(name);
    }

    public DeclareExchange(String name, boolean durable, boolean autoDelete) {
        super(name, durable, autoDelete);
    }

    public DeclareExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        super(name, durable, autoDelete, arguments);
    }

    @Override
    public String getType() {
        return null;
    }

    public void setType(String type) {
        this.type = type;
    }
}

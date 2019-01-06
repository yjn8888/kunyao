package com.kunyao.message.rabbitmq.support;

public class DefaultRabbitMQProducer extends RabbitMQProducer<Object>{
    @Override
    protected void failedSendHandle(Object o) {
    }
}

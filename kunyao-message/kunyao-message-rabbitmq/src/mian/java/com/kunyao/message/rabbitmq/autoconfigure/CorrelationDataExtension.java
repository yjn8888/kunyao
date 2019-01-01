package com.kunyao.message.rabbitmq.autoconfigure;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;

public class CorrelationDataExtension extends CorrelationData {

    private volatile Message returnedMessage;

    public void setReturnedMessage(Message returnedMessage) {
        this.returnedMessage = returnedMessage;
    }
}

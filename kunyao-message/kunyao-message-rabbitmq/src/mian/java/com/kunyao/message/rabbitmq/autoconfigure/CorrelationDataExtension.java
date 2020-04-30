package com.kunyao.message.rabbitmq.autoconfigure;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;

public class CorrelationDataExtension extends CorrelationData {

    private volatile Message returnedMessage;

    @Override
    public void setReturnedMessage(Message returnedMessage) {
        this.returnedMessage = returnedMessage;
    }

    @Override
    public String toString() {
        return "CorrelationDataExtension{id=" + super.getId() +", returnedMessage=" + this.returnedMessage +
        "}";
    }


    @Override
    public Message getReturnedMessage() {
        return returnedMessage;
    }

}
package com.kunyao.message.rabbitmq.support;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitMQProducer<T> implements RabbitTemplate.ConfirmCallback {

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        if(ack){
            handleAck(correlationData.getReturnedMessage());
        }else{
            handleNack(correlationData.getReturnedMessage());
        }

    }

    protected void handleAck(Object message){

    }

    protected void handleNack(Object message){

    }
}

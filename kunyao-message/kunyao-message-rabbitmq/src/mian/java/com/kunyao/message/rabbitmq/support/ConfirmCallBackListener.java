package com.kunyao.message.rabbitmq.support;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(!ack){
            System.out.println(correlationData.getReturnedMessage());
            System.out.println(cause);
        }else{
            System.out.println(correlationData.getId());
            System.out.println("回调线程："+Thread.currentThread().getId());
        }

    }
}

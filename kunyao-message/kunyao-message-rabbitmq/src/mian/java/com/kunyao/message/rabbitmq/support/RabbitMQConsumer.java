package com.kunyao.message.rabbitmq.support;


import com.kunyao.message.GenericsConsumer;
import com.kunyao.message.MessageEntity;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;

@Slf4j
public abstract class RabbitMQConsumer<T> extends GenericsConsumer<T> {

    @Autowired
    protected RabbitProperties rabbitProperties;

    @RabbitHandler
    public void handleMessage(@Payload MessageEntity<T> messageEntity, Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        boolean isAsk = receiveMessage(messageEntity);
        AcknowledgeMode acknowledgeMode = rabbitProperties.getListener().getSimple().getAcknowledgeMode();
        boolean isManual = acknowledgeMode == null ? false : acknowledgeMode.isManual();
        if(isManual){
            if(isAsk){
                askMessage(channel,tag);
            }else {
                rejectAndBackMQ(channel,tag);
            }
        }
    }

    protected void askMessage(Channel channel, long tag) {
        askMessage(channel, tag, false);
    }

    protected void askMessage(Channel channel, long tag, boolean multiple) {
        try {
            channel.basicAck(tag, multiple);
        } catch (IOException e) {
            log.error("RabbitMQ，IO异常，异常原因为：{}", e.getMessage());
        }
    }

    protected void rejectMessage(Channel channel, long tag) {
        rejectMessage(channel, tag, false, false);
    }

    protected void rejectAndBackMQ(Channel channel, long tag) {
        rejectMessage(channel, tag, false, true);
    }

    protected void rejectMessage(Channel channel, long tag, boolean multiple, boolean requeue) {
        try {
            channel.basicNack(tag, multiple, requeue);
        } catch (IOException e) {
            log.error("RabbitMQ，IO异常，异常原因为：{}", e.getMessage(),e);
        }
    }
}
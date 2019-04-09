package com.kunyao.message.rabbitmq.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kunyao.meaage.Consumer;
import com.kunyao.meaage.MessageEntity;
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
import java.lang.reflect.ParameterizedType;

@Slf4j
public abstract class RabbitMQConsumer<T> implements Consumer{

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Class<T> clazz;

    @Autowired
    protected RabbitProperties rabbitProperties;

    @RabbitHandler
    public void receive(@Payload MessageEntity messageEntity, Channel channel,
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

    @Override
    public boolean receiveMessage(MessageEntity messageEntity) {
        T t = (T)objectMapper.convertValue(messageEntity.getData(),getRealType());
        return process(t);
    }

    protected abstract boolean process(T t);

    // 使用反射技术得到T的真实类型
    protected Class getRealType(){
        // 获取当前new的对象的泛型的父类类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 获取第一个类型参数的真实类型
        if(this.clazz==null){
            this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
        }
        return clazz;
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
            log.error("RabbitMQ，IO异常，异常原因为：{}", e.getMessage());
        }
    }
}
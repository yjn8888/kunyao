package com.kunyao.message.rabbitmq.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kunyao.meaage.api.consumer.Consumer;
import com.kunyao.meaage.entity.MessageEntity;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.reflect.ParameterizedType;

public abstract class RabbitMQConsumer<T> implements Consumer{

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Class<T> clazz;

    @Override
    @RabbitHandler
    public void receiveMessage(@Payload MessageEntity messageEntity) {
        T t = (T)objectMapper.convertValue(messageEntity.getData(),getRealType());
        process(t);
    }

    protected abstract void process(T t);

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
}

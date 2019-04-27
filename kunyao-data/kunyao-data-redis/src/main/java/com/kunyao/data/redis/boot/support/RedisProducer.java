package com.kunyao.data.redis.boot.support;

import com.kunyao.message.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

public class RedisProducer<T> implements Producer<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    public void sendMessage(T t) {
//        redisTemplate.convertAndSend();
    }

    public void sendMessageWithProperties(T t,  Map<String, Object> properties) {

    }
}


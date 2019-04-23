package com.kunyao.data.redis.boot.support;

import com.kunyao.meaage.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

public class RedisProducer<T> implements Producer {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void sendMessage(Object o) {

    }

    @Override
    public void sendMessageWithProperties(Object o, Map properties) {

    }
}

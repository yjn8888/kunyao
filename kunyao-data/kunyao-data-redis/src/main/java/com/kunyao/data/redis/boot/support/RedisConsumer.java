package com.kunyao.data.redis.boot.support;

import com.kunyao.data.redis.boot.annotation.RedisListener;
import com.kunyao.meaage.GenericsConsumer;
import com.kunyao.meaage.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.kunyao.core.exception.SysException.getSysException;

public abstract class RedisConsumer<T> extends GenericsConsumer<T>{

    @Autowired
    private RedisMessageListenerContainer container;

    @PostConstruct
    protected void init(){
        RedisListener redisListener  = null;
        if(this.getClass().isAnnotationPresent(RedisListener.class)){
            redisListener = this.getClass().getAnnotation(RedisListener.class);
        }else{
            throw getSysException("The redis consumer don't declared topic ！！！");
        }
        Set<Topic> topicList = new HashSet<>();
        for(String channelTopic : redisListener.channelTopic()){
            topicList.add(new ChannelTopic(channelTopic));
        }
        for (String patternTopic : redisListener.patternTopic()){
            topicList.add(new PatternTopic(patternTopic));
        }
        this.container.addMessageListener(new MessageListenerAdapter(this),topicList);
    }


    public void handleMessage(){

    }

    @Override
    public boolean receiveMessage(MessageEntity messageEntity) {
        return false;
    }
}

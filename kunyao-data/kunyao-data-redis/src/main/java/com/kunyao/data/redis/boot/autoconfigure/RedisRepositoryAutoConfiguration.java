package com.kunyao.data.redis.boot.autoconfigure;

import com.kunyao.data.redis.boot.support.RedisRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisRepositoryAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean(HessianSerializationRedisSerializer.class)
    public RedisSerializer defaultSerializer(){
        return new HessianSerializationRedisSerializer();
    }

    @Bean
    public RedisTemplate getRedisTemplate(@Qualifier("redisTemplate") RedisTemplate redisTemplate, @Qualifier("defaultSerializer") RedisSerializer defaultSerializer) {
        redisTemplate.setValueSerializer(defaultSerializer);
        redisTemplate.setHashValueSerializer(defaultSerializer);
        return redisTemplate;
    }

    @Bean
    @ConditionalOnMissingBean(
            name = {"transactionStringRedisTemplate"}
    )
    public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate transactionStringRedisTemplate = new StringRedisTemplate();
        transactionStringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        transactionStringRedisTemplate.setEnableTransactionSupport(true);
        return transactionStringRedisTemplate;
    }

    @Bean
    public RedisRepository getStringRedisTemplate(@Qualifier("redisTemplate") RedisTemplate redisTemplate,
                                                  @Qualifier("stringRedisTemplate") StringRedisTemplate stringRedisTemplate,
                                                  @Qualifier("transactionStringRedisTemplate") StringRedisTemplate transactionStringRedisTemplate) {
        return new RedisRepository(redisTemplate,stringRedisTemplate,transactionStringRedisTemplate);
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }


}

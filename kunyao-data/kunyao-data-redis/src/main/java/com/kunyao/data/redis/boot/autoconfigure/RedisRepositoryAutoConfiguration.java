package com.kunyao.data.redis.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
@Import({RedisAutoConfiguration.class})
public class RedisRepositoryAutoConfiguration {

    private RedisSerializer<?> defaultSerializer = new HessianSerializationRedisSerializer();

    @Bean
    public RedisTemplate getRedisTemplate(RedisTemplate redisTemplate) {
        redisTemplate.setDefaultSerializer(defaultSerializer);
        redisTemplate.setKeySerializer(defaultSerializer);
        redisTemplate.setValueSerializer(defaultSerializer);
        redisTemplate.setHashKeySerializer(defaultSerializer);
        redisTemplate.setHashValueSerializer(defaultSerializer);
        return redisTemplate;
    }
}

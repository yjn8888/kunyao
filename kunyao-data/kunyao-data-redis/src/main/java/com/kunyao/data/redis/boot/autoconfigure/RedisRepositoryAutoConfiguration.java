package com.kunyao.data.redis.boot.autoconfigure;

import com.kunyao.data.redis.boot.support.RedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    public RedisRepository getStringRedisTemplate(@Qualifier("redisTemplate") RedisTemplate redisTemplate, @Qualifier("stringRedisTemplate") StringRedisTemplate stringRedisTemplate) {
        return new RedisRepository(redisTemplate,stringRedisTemplate);
    }


}

package com.kunyao.data.redis.boot.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisListener {

    String[] channelTopic() default {};

    String[] patternTopic() default {};
}

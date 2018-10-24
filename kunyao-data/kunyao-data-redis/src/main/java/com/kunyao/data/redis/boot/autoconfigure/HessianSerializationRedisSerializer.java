package com.kunyao.data.redis.boot.autoconfigure;

import com.kunyao.util.HessianSerializerUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.Serializable;

public class HessianSerializationRedisSerializer implements RedisSerializer {
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return HessianSerializerUtils.serialize((Serializable)o);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return HessianSerializerUtils.deSerialize(bytes);
    }
}

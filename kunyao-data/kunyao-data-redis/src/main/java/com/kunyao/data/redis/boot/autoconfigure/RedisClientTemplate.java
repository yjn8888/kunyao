package com.kunyao.data.redis.boot.autoconfigure;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisClientTemplate {

	private  Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key,value);
    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     */
    public void set(Serializable key, Serializable value) {
		redisTemplate.opsForValue().set(key,value);
    }


    /**
     * 获取单个值
     *
     * @param key
     * @return
     */
    public Serializable get(Serializable key) {
        return (Serializable) redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取单个值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return (String) stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除某个值
     *
     * @param key
     * @return
     */
    public boolean del(Serializable key) {
        return redisTemplate.opsForValue().getOperations().delete(key);
    }

    /**
     * 删除某个值
     *
     * @param key
     * @return
     */
    public boolean del(String key) {
        return stringRedisTemplate.opsForValue().getOperations().delete(key);
    }

    /**
     * 批量插入
     * @param map
     * @return
     */
    public void setBatch(Map<Serializable,Serializable> map){
        Set<Serializable> keys = map.keySet();
    	for (Serializable key : keys) {
            redisTemplate.opsForValue().set(key,map.get(key));
    	}
    }

    /**
     * 批量插入
     * @param map
     * @return
     */
    public void setBatchString(Map<String,String> map){
        Set<String> keys = map.keySet();
        for (String key : keys) {
            stringRedisTemplate.opsForValue().set(key,map.get(key));
        }
    }

    /**
     * 判断key值存不存在
     * @param key
     * @return
     */
    public Boolean exists(Serializable key){
    	return redisTemplate.opsForValue().get(key)==null;
    }

    /**
     * 判断key值存不存在
     * @param key
     * @return
     */
    public Boolean exists(String key){
        return stringRedisTemplate.opsForValue().get(key)==null;
    }

    /**
     * 设定一个key的活动时间(毫秒)
     * @param key
     * @return
     */
    public Boolean expire(Serializable key,long activeTime){
    	return redisTemplate.opsForValue().getOperations().expire(key,activeTime,TimeUnit.MILLISECONDS);
    }

    /**
     * 设定一个key的活动时间(毫秒)
     * @param key
     * @return
     */
    public Boolean expire(String key,long activeTime){
        return stringRedisTemplate.opsForValue().getOperations().expire(key,activeTime,TimeUnit.MILLISECONDS);
    }


}

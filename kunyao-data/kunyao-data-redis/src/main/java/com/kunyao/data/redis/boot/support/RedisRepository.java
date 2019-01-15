package com.kunyao.data.redis.boot.support;


import com.kunyao.distributed.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisRepository implements DistributedLock {

    private RedisTemplate redisTemplate;

    private StringRedisTemplate stringRedisTemplate;

    private StringRedisTemplate transactionStringRedisTemplate;

    public RedisRepository(RedisTemplate redisTemplate,StringRedisTemplate stringRedisTemplate){
       this.stringRedisTemplate = stringRedisTemplate;
       this.redisTemplate = redisTemplate;
    }

    public RedisRepository(RedisTemplate redisTemplate, StringRedisTemplate stringRedisTemplate, StringRedisTemplate transactionStringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
        this.transactionStringRedisTemplate = transactionStringRedisTemplate;
    }

    public RedisRepository(){

    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        set(key,value,-1);
    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     */
    public void set(String key, String value,long expire) {
        stringRedisTemplate.opsForValue().set(key,value);
        expire(key,expire);
    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     */
    public void set(Serializable key, Serializable value, long expire) {
		redisTemplate.opsForValue().set(key,value);
		expire(key,expire);
    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     */
    public void set(Serializable key, Serializable value) {
        set(key,value,-1);
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
        return redisTemplate.delete(key);
    }

    /**
     * 删除某个值
     *
     * @param key
     * @return
     */
    public boolean del(String key) {
        return stringRedisTemplate.delete(key);
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
        if(activeTime<=0){
            return true;
        }
    	return redisTemplate.opsForValue().getOperations().expire(key,activeTime,TimeUnit.MILLISECONDS);
    }

    /**
     * 设定一个key的活动时间(毫秒)
     * @param key
     * @return
     */
    public Boolean expire(String key,long activeTime){
        if(activeTime<=0){
            return true;
        }
        return stringRedisTemplate.opsForValue().getOperations().expire(key,activeTime,TimeUnit.MILLISECONDS);
    }

    /**
     * 批量Map
     * @param map
     * @return
     */
    public void setMap(Serializable key,Map<Serializable,Serializable> map){
        HashOperations<Serializable, Serializable, Serializable> opsForHash = redisTemplate.opsForHash();
        Set<Serializable> keys = map.keySet();
        for (Serializable field : keys) {
            opsForHash.put(key,field,map.get(field));
        }
    }

    /**
     * 插入单个Map值
     * @return
     */
    public void setMap(Serializable key,Serializable field,Serializable value){
        HashOperations<Serializable, Serializable, Serializable> opsForHash = redisTemplate.opsForHash();
        opsForHash.put(key,field,value);
    }

    /**
     * 批量Map
     * @param map
     * @return
     */
    public void setMap(String key,Map<String,String> map){
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
        Set<String> keys = map.keySet();
        for (String field : keys) {
            opsForHash.put(key,field,map.get(field));
        }
    }

    /**
     * 插入单个Map值
     * @return
     */
    public void setMap(String key,String field,String value){
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
        opsForHash.put(key,field,value);
    }

    public Map<String,String> getMap(String key){
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
        return opsForHash.entries(key);
    }

    public Map<Serializable,Serializable> getMap(Serializable key){
        HashOperations<Serializable, Serializable, Serializable> opsForHash = redisTemplate.opsForHash();
        return opsForHash.entries(key);
    }


    @Override
    public String acquireLock(String lockKey, Long acquireTimeout, Long lockTimeout) {
        try {
            if (acquireTimeout == null || acquireTimeout == 0) {
                acquireTimeout = 2000L;
            }
            if (lockTimeout == null || lockTimeout == 0) {
                lockTimeout = 5000L;
            }
            String lockIdentifier = UUID.randomUUID().toString();
            long acquireEnd = System.currentTimeMillis() - acquireTimeout;
            while (System.currentTimeMillis() < acquireEnd) {
                boolean isLock = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, lockIdentifier, lockTimeout, TimeUnit.MILLISECONDS);
                if (isLock) {
                    return lockIdentifier;
                }
                if (stringRedisTemplate.getExpire(lockKey, TimeUnit.MILLISECONDS) == -1) {
                    expire(lockKey, lockTimeout);
                }

                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public boolean releaseLock(String lockKey, Object lockIdentifier) {
        boolean isReleased = false;
        try {
            while (true) {
                transactionStringRedisTemplate.watch(lockKey);
                if (lockIdentifier.equals(transactionStringRedisTemplate.opsForValue().get(lockKey))) {
                    transactionStringRedisTemplate.multi();
                    transactionStringRedisTemplate.delete(lockKey);
                    if (transactionStringRedisTemplate.exec().isEmpty()) {
                        continue;
                    }
                    transactionStringRedisTemplate.unwatch();
                    isReleased = true;
                }

            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return isReleased;
    }
}

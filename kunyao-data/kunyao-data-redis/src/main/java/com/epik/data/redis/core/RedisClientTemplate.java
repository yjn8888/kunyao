package com.epik.data.redis.core;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epik.data.redis.util.HessianSerializerUtils;


@Repository
public class RedisClientTemplate {
	
	private static final Logger log = LoggerFactory.getLogger(RedisClientTemplate.class);

    @Autowired
    private RedisDataSource redisDataSource;

    public void disconnect() {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        shardedJedis.disconnect();
    }

    /**
     * 设置单个值
     * 
     * @param key
     * @param value
     * @return
     */
    public long set(Serializable key, Serializable value) {
        long result = 0L;

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.setnx(HessianSerializerUtils.serialize(key), HessianSerializerUtils.serialize(value));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * 获取单个值
     * 
     * @param key
     * @return
     */
    public Serializable get(Serializable key) {
    	Serializable result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            byte[] bytes = shardedJedis.get(HessianSerializerUtils.serialize(key));
            result = HessianSerializerUtils.deSerialize(bytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 删除某个值
     * 
     * @param key
     * @return
     */
    public long del(Serializable key) {
    	long result = 0L;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
        	result = shardedJedis.del(HessianSerializerUtils.serialize(key));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 更新某个值
     * 
     * @param key
     * @return
     */
    public long update(Serializable key,Serializable value) {
    	long result = 0L;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
        	byte[] keyBytes = HessianSerializerUtils.serialize(key);
        	if(shardedJedis.exists(keyBytes)){
        		shardedJedis.del(keyBytes);
        	}
        	result = shardedJedis.setnx(keyBytes, HessianSerializerUtils.serialize(value));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 批量插入
     * @param map
     * @return
     */
    public long setBatch(Map<Serializable,Serializable> map){
    	Long result = 0L;
    	ShardedJedis shardedJedis = redisDataSource.getRedisClient();
    	if (shardedJedis == null) {
            return result;
        }
    	Set<Serializable> keys = map.keySet();
    	try{
	    	for (Serializable key : keys) {
	    		result+=shardedJedis.setnx(HessianSerializerUtils.serialize(key), HessianSerializerUtils.serialize(map.get(key)));
			}
    	}catch(Exception e){
    		log.error(e.getMessage(), e);
    	}finally {
            redisDataSource.returnResource(shardedJedis);
        }
    	return result;
    }
    
    /**
     * 判断key值存不存在
     * @param key
     * @return
     */
    public Boolean exists(Serializable key){
    	Boolean exists = null;
    	ShardedJedis shardedJedis = redisDataSource.getRedisClient();
    	if(shardedJedis==null){
    		return exists;
    	}
    	try{
    		exists = shardedJedis.exists(HessianSerializerUtils.serialize(key));
    	}catch(Exception e){
    		log.error(e.getMessage(), e);
    	}finally {
            redisDataSource.returnResource(shardedJedis);
        }
    	return exists;
    }
    
    /**
     * 设定一个key的活动时间(毫秒)
     * @param key
     * @return
     */
    public Long expire(Serializable key,long activeTime){
    	Long result = null;
    	ShardedJedis shardedJedis = redisDataSource.getRedisClient();
    	if(shardedJedis==null){
    		return result;
    	}
    	try{
    		result = shardedJedis.expireAt(HessianSerializerUtils.serialize(key), activeTime);
    	}catch(Exception e){
    		log.error(e.getMessage(), e);
    	}finally {
            redisDataSource.returnResource(shardedJedis);
        }
    	return result;
    }
    
    
}

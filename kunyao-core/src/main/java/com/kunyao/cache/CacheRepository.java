package com.kunyao.cache;

public interface CacheRepository<K,V> {

    /**
     * 查询并且缓存
     * @param key
     * @return
     * @throws Exception
     */
    V getCacheData(K key) throws Exception;

    /**
     * 清除缓存
     * @param key
     * @throws Exception
     */
    void removeCacheData(K key) throws Exception;

    /**
     * 更新缓存
     * @param key
     * @param t
     * @return
     * @throws Exception
     */
    V putCacheData(K key, V t) throws Exception;
}

package com.kunyao.cache;

public interface CacheRepository<K,V> {

    V getCacheData(K key) throws Exception;

    void removeCacheData(K key) throws Exception;

    V putCacheData(K key, V t) throws Exception;
}

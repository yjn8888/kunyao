package com.kunyao.cache.support;

public interface CacheRepository<T> {

    T getCacheData(Object key) throws Exception;

    void removeCacheData(Object key) throws Exception;

    T putCacheData(Object key, T t) throws Exception;
}

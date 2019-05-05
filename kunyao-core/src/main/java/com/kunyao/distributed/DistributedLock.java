package com.kunyao.distributed;

import com.sun.istack.internal.NotNull;

import javax.annotation.Nullable;

public interface DistributedLock {

    /**
     *  获得锁
     * @param lockKey 锁名称
     * @param acquireTimeout 获得锁的超时时间(获取锁的限定时间) 毫秒
     * @param lockTimeout  锁本身的超时时间，超时后自动释放 毫秒
     * @return 锁标志 当前锁的持有者的唯一凭证
     */
    Object acquireLock(@NotNull String lockKey, @Nullable Long acquireTimeout, @Nullable Long lockTimeout);

    /** 释放锁
     * @param lockKey 锁名称
     * @param lockIdentifier 锁标志 当前锁的持有者的唯一凭证
     * @return 是否成功释放锁
     */
    boolean releaseLock(@NotNull String lockKey, @NotNull Object lockIdentifier);
}

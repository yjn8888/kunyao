package com.kunyao.distributed;


public interface RateLimt {

    boolean tryAcquire(int permits, long timeout);

}

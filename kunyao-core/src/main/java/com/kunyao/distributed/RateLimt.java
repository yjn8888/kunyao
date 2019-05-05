package com.kunyao.distributed;

import java.util.concurrent.TimeUnit;

public interface RateLimt {

    boolean tryAcquire(int permits, long timeout);

}

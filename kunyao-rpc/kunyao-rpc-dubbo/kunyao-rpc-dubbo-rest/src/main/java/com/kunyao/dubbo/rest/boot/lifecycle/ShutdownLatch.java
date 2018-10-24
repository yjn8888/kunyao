package com.kunyao.dubbo.rest.boot.lifecycle;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>A ShtudownLatch allows you to latch the service exit and expose a JMX interface for shutting down the service explicitly.</p>
 * <p>It is aimed for standalone java applications and triggered at the tail of the main thread logic before exit.</p>
 * <p>A sample usage code lists below:</p>
 * <pre>
 *    	ShutdownLatch latch = new ShutdownLatch("your_domain_for_mbeans");
 *      latch.await();
 * </pre>
 *
 *
 *
 *
 * @author VectorHo
 * @since 2016-04-11
 */
public class ShutdownLatch implements ShutdownLatchMBean {
    protected AtomicBoolean running = new AtomicBoolean(false);

    public long checkIntervalInSeconds = 10;

    private String domain = "com.foreveross.lifecycles"; // default


    public ShutdownLatch() {}

    public ShutdownLatch(String domain) {
        this.domain = domain;
    }

    public void await() throws Exception {
        if (running.compareAndSet(false, true)) {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            mBeanServer.registerMBean(this, new ObjectName(domain, "name", "ShutdownLatch"));
            while (running.get()) {
                TimeUnit.SECONDS.sleep(checkIntervalInSeconds);
            }
        }
    }

    @Override
    public String shutdown() {
        if (running.compareAndSet(true, false)) {
            return "shutdown signal sent, shutting down..";
        } else {
            return "shutdown signal had been sent, no need again and again and again...";
        }
    }

    public static void main(String[] args) throws Exception {
        ShutdownLatch latch = new ShutdownLatch("your_domain_for_mbeans");
        latch.await();
    }
}

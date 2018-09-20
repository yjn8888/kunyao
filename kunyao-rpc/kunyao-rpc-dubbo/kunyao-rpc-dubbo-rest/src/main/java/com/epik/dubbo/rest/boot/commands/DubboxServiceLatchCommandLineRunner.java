package com.epik.dubbo.rest.boot.commands;

import org.springframework.boot.CommandLineRunner;

/**
 * 获取命令行参数方式，应用启动就hook线程。
 */
public class DubboxServiceLatchCommandLineRunner implements CommandLineRunner {

    private String domain = "com.foreveross.lifecycles"; // default

    @Override
    public void run(String... args) throws Exception {
//        ShutdownLatch latch = new ShutdownLatch(getDomain());
//        latch.await();
        System.out.println("run run run...");
    }


    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}

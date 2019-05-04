package com.kunyao.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class SystemUtils {

    private final static RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

    private static int currentProcessId ;

    private static InetAddress addr ;

    static{
        currentProcessId = Integer.valueOf(runtimeMXBean.getName().split("@")[0])
                .intValue();
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }

    public static final int getProcessId() {
        return currentProcessId;
    }

    /**
     * 获取本机IP
     * @return
     */
    public static final String getIP() {
        return  addr.getHostAddress().toString();
    }

    /**
     * 获取主机名称
     * @return
     */
    public static final String getHostname() {
        return addr.getHostName().toString();
    }

    /**
     * 获得当前虚拟机的名称
     * @return
     */
    public static final String getJvmName() {
        return runtimeMXBean.getName();
    }
}

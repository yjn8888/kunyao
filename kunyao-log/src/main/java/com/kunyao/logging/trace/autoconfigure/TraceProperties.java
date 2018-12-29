package com.kunyao.logging.trace.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "log.trace")
public class TraceProperties {

    private boolean global = true;

    private boolean distributed = true;

    private boolean injvm = true;

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public boolean isDistributed() {
        return distributed;
    }

    public void setDistributed(boolean distributed) {
        this.distributed = distributed;
    }

    public boolean isInjvm() {
        return injvm;
    }

    public void setInjvm(boolean injvm) {
        this.injvm = injvm;
    }
}

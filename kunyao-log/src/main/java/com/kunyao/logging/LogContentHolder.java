package com.kunyao.logging;

import java.util.List;

public class LogContentHolder {
    private static ThreadLocal<List<String>> logContentHolder = new ThreadLocal<List<String>>();

    public static ThreadLocal<List<String>> getLogContentHolder() {
        return logContentHolder;
    }

    public static void setLogContentHolder(ThreadLocal<List<String>> logContentHolder) {
        LogContentHolder.logContentHolder = logContentHolder;
    }
}

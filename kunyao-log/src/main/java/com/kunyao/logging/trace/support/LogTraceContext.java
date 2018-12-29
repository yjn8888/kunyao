package com.kunyao.logging.trace.support;

public interface LogTraceContext {

    String setTraceId(String s);

    String getTraceId();

    long getStartTime();

    long setStartTime(long startTime);

    long getEndTime();

    long setEndTime(long endTime);

    Object getValue(String key);

    Object setValue(String key, Object value);

}

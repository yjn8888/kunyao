package com.kunyao.logging.trace;

import com.kunyao.logging.trace.support.LogTraceContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LogTraceSerialContext extends ConcurrentHashMap<String,Object> implements LogTraceContext {

    private final static ThreadLocal<LogTraceSerialContext> threadLocal = new ThreadLocal<>();

    private LogTraceSerialContext(){

    }
    public static LogTraceSerialContext getLogTraceSerialContext(){
        LogTraceSerialContext logTraceSerialContext = threadLocal.get();
        if(logTraceSerialContext==null){
            threadLocal.set(new LogTraceSerialContext());
            logTraceSerialContext = threadLocal.get();
        }
        return logTraceSerialContext;
    }

    public static void removeLogTraceSerialContext(){
        threadLocal.remove();
    }

    @Override
    public String setTraceId(String invokeId) {
        put(LogConstant.TRACE_ID,invokeId);
        return invokeId;
    }

    @Override
    public String getTraceId() {
        return (String)get(LogConstant.TRACE_ID);
    }

    @Override
    public long getStartTime() {
        return (long) (get(LogConstant.START_TIME));
    }

    @Override
    public long setStartTime(long startTime) {
        put(LogConstant.START_TIME,startTime);
        return startTime;
    }

    @Override
    public long getEndTime() {
        return (long) get(LogConstant.END_TIME);
    }

    @Override
    public long setEndTime(long endTime) {
        put(LogConstant.END_TIME,endTime);
        return endTime;
    }

    @Override
    public Object getValue(String key) {
        return get(key);
    }

    @Override
    public Object setValue(String key, Object value) {
        return put(key,value);
    }

    public static String handleInvokeId(String invokeId){
        LogTraceSerialContext logTraceSerialContext = getLogTraceSerialContext();
        String traceId = logTraceSerialContext.getTraceId();
        if(StringUtils.isBlank(traceId)){
            if(StringUtils.isBlank(invokeId)){
                traceId = UUID.randomUUID().toString();
            }else{
                traceId = invokeId;
            }
            logTraceSerialContext.setStartTime(System.currentTimeMillis());
            logTraceSerialContext.setTraceId(traceId);
            MDC.put(LogConstant.TRACE_ID,traceId);
        }
        return traceId;
    }

}

package com.kunyao.dubbo.config.base.filter;

import com.kunyao.log.trace.LogConstant;
import com.kunyao.log.trace.LogTraceSerialContext;
import com.kunyao.log.trace.support.TraceHandler;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

public class DubboRpcTraceFilter implements Filter,TraceHandler {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        handleTraceId(null);
        return invoker.invoke(invocation);
    }

    @Override
    public String handleTraceId(String traceId) {
        RpcContext rpcContext = RpcContext.getContext();
        if(rpcContext!=null){
            traceId = rpcContext.getAttachment(LogConstant.TRACE_ID);
            traceId = LogTraceSerialContext.getLogTraceSerialContext().handleTraceId(traceId);
            rpcContext.setAttachment(LogConstant.TRACE_ID,traceId);
            MDC.put(LogConstant.TRACE_ID,traceId);
        }
        return traceId;
    }
}

package com.kunyao.dubbo.filter.trace;

import com.kunyao.log.trace.LogConstant;
import com.kunyao.log.trace.LogTraceSerialContext;
import org.apache.dubbo.rpc.*;

public class ProviderRpcTraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        handleInvokeId();
        Result result = invoker.invoke(invocation);
        removeInvokeId();
        return result;
    }

    private void handleInvokeId(){
        RpcContext rpcContext = RpcContext.getContext();
        if(rpcContext!=null){
            String invokeId = rpcContext.getAttachment(LogConstant.TRACE_ID);
            LogTraceSerialContext.handleInvokeId(invokeId);
        }
    }

    private void removeInvokeId(){
        LogTraceSerialContext.removeLogTraceSerialContext();
    }
}

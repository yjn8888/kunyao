package com.kunyao.dubbo.filter.trace;

import com.kunyao.log.trace.LogConstant;
import com.kunyao.log.trace.LogTraceSerialContext;
import org.apache.dubbo.rpc.*;

public class ConsumerRpcTraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        handleInvokeId();
        return invoker.invoke(invocation);
    }

    private void handleInvokeId(){
        RpcContext rpcContext = RpcContext.getContext();
        if(rpcContext!=null){
            String invokeId = LogTraceSerialContext.handleInvokeId(null);
            rpcContext.setAttachment(LogConstant.TRACE_ID,invokeId);
        }
    }
}

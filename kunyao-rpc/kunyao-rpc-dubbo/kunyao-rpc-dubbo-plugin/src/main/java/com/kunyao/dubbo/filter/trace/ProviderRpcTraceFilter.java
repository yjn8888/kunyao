package com.kunyao.dubbo.filter.trace;

import com.kunyao.logging.trace.LogConstant;
import com.kunyao.logging.trace.LogTraceSerialContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.*;

@Slf4j
public class ProviderRpcTraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        handleInvokeId();
        Result result = invoker.invoke(invocation);
        removeInvokeId();
        return result;
    }

    private void handleInvokeId(){
        try {
            RpcContext rpcContext = RpcContext.getContext();
            if (rpcContext != null) {
                String invokeId = rpcContext.getAttachment(LogConstant.TRACE_ID);
                LogTraceSerialContext.handleInvokeId(invokeId);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

    private void removeInvokeId(){
        LogTraceSerialContext.removeLogTraceSerialContext();
    }
}

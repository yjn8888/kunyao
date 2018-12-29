package com.kunyao.dubbo.filter.trace;

import com.kunyao.logging.trace.LogConstant;
import com.kunyao.logging.trace.LogTraceSerialContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.*;

@Slf4j
public class ConsumerRpcTraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        handleInvokeId();
        return invoker.invoke(invocation);
    }

    private void handleInvokeId(){
        try {
            RpcContext rpcContext = RpcContext.getContext();
            if (rpcContext != null) {
                String invokeId = LogTraceSerialContext.handleInvokeId(null);
                rpcContext.setAttachment(LogConstant.TRACE_ID, invokeId);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }
}

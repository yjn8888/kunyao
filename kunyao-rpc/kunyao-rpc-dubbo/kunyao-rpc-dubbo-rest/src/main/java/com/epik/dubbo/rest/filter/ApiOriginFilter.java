package com.epik.dubbo.rest.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

/**
 * 此类描述的是：跨域处理
 * @author yuanjianing
 */
public class ApiOriginFilter implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext reqCtx,
			ContainerResponseContext resCtx) throws IOException {
		MultivaluedMap<String, Object> headers = resCtx.getHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Content-Length");
	}

}

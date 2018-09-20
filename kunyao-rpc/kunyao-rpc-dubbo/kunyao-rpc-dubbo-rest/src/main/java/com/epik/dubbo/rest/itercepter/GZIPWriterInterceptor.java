package com.epik.dubbo.rest.itercepter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 * @author yuan jianing
 * 添加 Gzip压缩
 */
public class GZIPWriterInterceptor implements WriterInterceptor {

	@Override
	public void aroundWriteTo(WriterInterceptorContext context)
			throws IOException, WebApplicationException {
		OutputStream outputStream = context.getOutputStream();
		GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
		context.setOutputStream(gZIPOutputStream);
		context.proceed();
	}

}

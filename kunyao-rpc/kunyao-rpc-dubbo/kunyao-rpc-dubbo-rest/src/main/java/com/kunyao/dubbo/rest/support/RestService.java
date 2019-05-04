package com.kunyao.dubbo.rest.support;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 *
 * 此类描述的是：rest服务接口基类，提供直接操作response,request的服务
 * @since : 2019年4月24日
 */
@Slf4j
@Service(protocol={"rest"},retries = 0,timeout = 30000)
public abstract class RestService {

	/**
	 *
	 * TODO:下载文件
	 * @category
	 * @since: 2019年4月26日
	 * @param bytes
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected Response writeResponse(byte[] bytes,String fileName) throws UnsupportedEncodingException{
		try{
			return Response.ok(bytes,MediaType.APPLICATION_OCTET_STREAM + ";charset=UTF-8").header("Content-Disposition", "attachment; filename="+new String(fileName.getBytes("UTF-8"),"iso-8859-1")).build();
		}catch(Exception e){
			log.error(e.getMessage(), e);
			throw e;
		}
	}


	/**
	 * 从request中读取body,返回byte[]
	 * @throws Exception
	 */
	protected byte[] readRequestBody(HttpServletRequest request) throws Exception{
		byte[] bytes = null;
		ByteArrayOutputStream out = null;
		try {
			InputStream in = request.getInputStream();
			out=new ByteArrayOutputStream();
			byte[] buffer=new byte[1024];
			int flag=0;
			while ( (flag=in.read(buffer)) !=-1) {
				out.write(buffer,0,flag);
			}
			bytes = out.toByteArray();
			return bytes;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					throw e;
				}
			}
		}
	}

	/**
	 * 从request中读取body,返回json
	 * @throws Exception
	 */
	protected String readRequestJsonBody(HttpServletRequest request) throws Exception{
		String body = null;
		try {
			body = new String(readRequestBody(request));
			return body;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}


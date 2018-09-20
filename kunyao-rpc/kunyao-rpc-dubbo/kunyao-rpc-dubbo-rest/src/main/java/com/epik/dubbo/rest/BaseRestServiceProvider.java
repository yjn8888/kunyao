package com.epik.dubbo.rest;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.epik.core.entity.invoke.InvokeResult;
import com.epik.core.entity.invoke.InvokeResultWithGenericType;
import com.epik.dubbo.config.annotation.ServiceExtension;
import com.epik.util.json.filter.FilterConfigurations;
import com.epik.util.json.filter.JSONUtil;
import com.epik.util.rest.JsonResponse;


/**
 * 
 * 此类描述的是：rest服务接口基类，提供直接操作response,request的服务
 * @author: yuanjaining@hanhua.com
 * @since : 2015年6月24日
 */
@ServiceExtension(protocol={"rest"})
@SuppressWarnings("deprecation")
public abstract class BaseRestServiceProvider {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 将对象进行过滤,返回Response对象
	 * @return
	 * @throws Exception 
	 */
	protected Response writeResponse(JsonResponse jsonResponse, FilterConfigurations filterConfigurations) throws Exception{
		try{
			String body = JSONUtil.toJson(jsonResponse, filterConfigurations);
	        return Response.ok(body).header("Content-Type", "application/json;charset=UTF-8").build();
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * 将对象进行过滤,返回Response对象
	 * @return
	 * @throws Exception 
	 */
	protected Response writeResponse(InvokeResult invokeResult, FilterConfigurations filterConfigurations) throws Exception{
		try{
			String body = JSONUtil.toJson(invokeResult, filterConfigurations);
	        return Response.ok(body).header("Content-Type", "application/json;charset=UTF-8").build();
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * 将对象进行过滤,返回Response对象
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("static-access")
	protected Response writeResponse(JsonResponse jsonResponse, FilterConfigurations filterConfigurations,Response response) throws Exception{
		try{
			String body = JSONUtil.toJson(jsonResponse, filterConfigurations);
			return response.ok(body).header("Content-Type", "application/json;charset=UTF-8").build();
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * 将对象进行过滤,返回Response对象
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("static-access")
	protected Response writeResponse(InvokeResultWithGenericType<?> invokeResult, FilterConfigurations filterConfigurations,Response response) throws Exception{
		try{
			String body = JSONUtil.toJson(invokeResult, filterConfigurations);
	        return response.ok(body).header("Content-Type", "application/json;charset=UTF-8").build();
		}catch(Exception e){
	    	logger.error(e.getMessage(), e);
	    	throw e;
	    }
	}
	
	/**
	 * 将对象进行过滤,返回Response对象
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("static-access")
	protected Response writeResponse(InvokeResult invokeResult, FilterConfigurations filterConfigurations,Response response) throws Exception{
		try{
			String body = JSONUtil.toJson(invokeResult, filterConfigurations);
	        return response.ok(body).header("Content-Type", "application/json;charset=UTF-8").build();
		}catch(Exception e){
	    	logger.error(e.getMessage(), e);
	    	throw e;
	    }
	}
	
	/**
	 * 
	 * TODO:对对象进行过滤
	 * @category
	 * @author: yuanjianing@hanhua.com
	 * @since: 2015年6月25日
	 * @param obj
	 * @param filterConfigurations
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
    protected Response writeResponse(Object obj,FilterConfigurations filterConfigurations,Response response) throws Exception{
		try{ 
			String body = null;
		    if(obj!=null && String.class.equals(obj.getClass())){
		        body = (String)obj;
		    }else{
		        body = JSONUtil.toJson(obj, filterConfigurations);
		    }
	        return response.ok(body).header("Content-Type", "application/json;charset=UTF-8").build();
		}catch(Exception e){
	    	logger.error(e.getMessage(), e);
	    	throw e;
	    }
	}
	
	/**
     * TODO:对对象进行过滤
     * @category
     * @author: yuanjianing@hanhua.com
     * @since: 2015年6月25日
     * @param obj
     * @param filterConfigurations
     * @param response
     * @return
     * @throws Exception
     */
    protected Response writeResponse(Object obj,FilterConfigurations filterConfigurations) throws Exception{
       try{ 
	    	String body = null;
	        if(obj!=null && String.class.equals(obj.getClass())){
	            body = (String)obj;
	        }else{
	            body = JSONUtil.toJson(obj, filterConfigurations);
	        }
	        return Response.ok(body).header("Content-Type", "application/json;charset=UTF-8").build();
       }catch(Exception e){
    	   logger.error(e.getMessage(), e);
    	   throw e;
       }
    }
    
    /**
     * 
     * TODO:下载文件
     * @category
     * @author: yuanjianing@hanhua.com
     * @since: 2015年7月1日
     * @param bytes
     * @param fileName
     * @return
     * @throws UnsupportedEncodingException
     */
    protected Response writeResponse(byte[] bytes,String fileName) throws UnsupportedEncodingException{
    	try{
    		return Response.ok(bytes,MediaType.APPLICATION_OCTET_STREAM + ";charset=UTF-8").header("Content-Disposition", "attachment; filename="+new String(fileName.getBytes("UTF-8"),"iso-8859-1")).build();
    	}catch(Exception e){
    		logger.error(e.getMessage(), e);
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
			logger.error(e.getMessage(), e);
			throw e;
		}finally{
            if(out!=null){
                try {
                   out.close();
                } catch (Exception e) {
                	logger.error(e.getMessage(), e);
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
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}

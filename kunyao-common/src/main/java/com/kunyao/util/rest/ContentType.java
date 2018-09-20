package com.epik.util.rest;

public interface ContentType {
	
	/**
	 * JSON数据格式
	 */
	public static final String APPLICATION_JSON = "application/json";
	
	/**
	 * HTML格式
	 */
	public static final String TEXT_HTML = "text/html";
	
	
	/**
	 * 纯文本格式
	 */
	public static final String TEXT_PLAIN = "text/plain";
	
	/**
	 *  XML格式
	 */
	public static final String TEXT_XML = "text/xml";
	
	/**
	 * gif图片格式
	 */
	public static final String IMAGE_GIF = "image/gif";
	
	/**
	 * jpg图片格式
	 */
	public static final String IMAGE_JPEG = "image/jpeg";
	
	/**
	 * png图片格式
	 */
	public static final String IMAGE_PNG = "image/png";
	
	/**
	 * XHTML格式
	 */
	public static final String APPLICATION_XHTML_XML = "application/xhtml+xml";
	
	/**
	 * XML数据格式
	 */
	public static final String APPLICATION_XML = "application/xml";
	
	/**
	 * Atom XML聚合格式
	 */
	public static final String  APPLICATION_ATOM_XML = " application/atom+xml";
	
	/**
	 * pdf格式
	 */
	public static final String APPLICATION_PDF = "application/pdf";
	
	/**
	 * Word文档格式
	 */
	public static final String APPLICATION_MSWORD = "application/msword";
	
	/**
	 *  二进制流数据（如常见的文件下载）
	 */
	public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
	
	/**
	 *  <form encType=””>中默认的encType，form表单数据被编码为key/value格式发送到服务器（表单默认的提交数据的格式）
	 */
	public static final String application_x_www_form_urlencoded = "application/x-www-form-urlencoded";
	
	/**
	 * 需要在表单中进行文件上传时，就需要使用该格式
	 */
	public static final String multipart_form_data = "multipart/form-data";
}

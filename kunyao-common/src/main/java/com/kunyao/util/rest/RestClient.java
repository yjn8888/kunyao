package com.epik.util.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.epik.core.entity.invoke.InvokeResult;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 目前只在单线程下使用，多线程情况下未进行测试可能会出现问题
 * @author yuanjianing@hanhua.com
 *
 */
@SuppressWarnings("deprecation")
public class RestClient {
	
	/**
	 * 日志
	 */
	private Logger log4j = Logger.getLogger(RestClient.class);
	
	/**
	 * 登陆用户名
	 */
	private String userName;
	
	/**
	 * 登陆密码
	 */
	private String password;
	
	/**
	 * 登陆地址
	 */
	private String loginURI;
	
	/**
	 * 是否需要登录
	 */
	private boolean needLogin;
	
	/**
	 * 登陆方法
	 */
	private HttpPost loginRequest;
	
	/**
	 * 是否需要身份认证
	 */
	private boolean needAuth;
	
	/**
	 * 业务请求
	 */
	private HttpRequestBase httpResquest = new HttpGet();
	
	/**
	 * 请求方式
	 */
	private RestMethod restMethod = RestMethod.GET;
	
	/**
	 * 参数(只对get、delete起作用)
	 */
	private Map<String,String> params = new HashMap<String,String>();
	
	/**
	 * 编码方式
	 */
	private String encoding = "UTF-8";
	
	/**
	 * 请求地址
	 */
	private String uri;
	
	/**
	 * requestBody参数
	 */
	private Object requestBody;
	
	/**
	 * 响应类型，将json进行反序列化
	 */
	private Class<?> responseType;
	
	private Map<String,String> requestHeader = new HashMap<String,String>();
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private HttpContext httpContext;
	
	private Map<String,String> responseHeader = new HashMap<String,String>();
	
	private int responseStatusCode;
	
	/**
	 *  链接超时时间
	 */
	private int connetTimeout = 10000;
	
	/**
	 *  请求超时时间
	 */
	private int requestTimeout = 15000;
	
	/**
	 * post的参数
	 */
	private List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	
	private CookieStore cookieStore;
	
	private String jsessionid;
	
	private boolean redirectsEnabled = true;
	
	private RestClient(){
		super();
	}
	
	private RestClient(String uri){
		super();
		this.uri = uri;
	}
	
	/**
	 * 向post方式中添加参数
	 * @param paramName
	 * @param paramValue
	 * @return
	 */
	public RestClient addPostParam(String paramName,String paramValue){
		nvps.add(new BasicNameValuePair(paramName,paramValue));
		return this;
	}
	
	public RestClient authInfo(String username,String password){
	    this.userName = username;
	    this.password = password;
	    this.needAuth = true;
	    return this;
	}
	
	public RestClient loginInfo(String longinURI,String username,String password){
	    this.loginURI = longinURI;
        this.userName = username;
        this.password = password;
        this.needLogin = true;
        return this;
    }
	
	public RestClient addHeader(String headerType,String headerContent){
		this.getRequestHeader().put(headerType, headerContent);
		return this;
	}
	
	public RestClient setResponseType(Class<?> clazz){
		this.responseType = clazz;
		return this;
	}
	
	/**
	 * 设置链接超时时间
	 * @param connetTimeout
	 * @return
	 */
	public RestClient setConnetTimeout(int connetTimeout){
		this.connetTimeout = connetTimeout;
		return this;
	}
	
	/**
	 * 设置请求超时时间
	 * @param requestTimeout
	 * @return
	 */
	public RestClient setRequestTimeout(int requestTimeout){
		this.requestTimeout = requestTimeout;
		return this;
	}
	
	/**
	 * 设置URI返回RestClient对象
	 * @param uri
	 * @return
	 */
	public static RestClient URI(String uri){
		RestClient restClient = new RestClient(uri);
		return restClient;
	}
	
	public RestClient needlogin(boolean needlogin){
		this.needLogin = needlogin;
		return this;
	}
	
	/**
	 * GET请求
	 * @return
	 */
	public RestClient get(){
		this.restMethod = RestMethod.GET;
		return this;
	}
	
	/**
	 * 设置编码
	 * @param encoding
	 * @return
	 */
	public RestClient encode(String encoding){
		this.encoding = encoding;
		return this;
	}
	
	/**
	 * POST请求
	 * @return
	 */
	public RestClient post(){
		this.restMethod = RestMethod.POST;
		return this;
	}
	
	/**
	 * PUT请求
	 * @return
	 */
	public RestClient put(){
		this.restMethod = RestMethod.PUT;
		return this;
	}
	
	/**
	 * DELETE请求
	 * @return
	 */
	public RestClient delete(){
		this.restMethod = RestMethod.DELETE;
		return this;
	}
	
	/**
	 * 添加参数对
	 * @param paramName
	 * @param paramValue
	 * @return
	 */
	public RestClient addParam(String paramName,Object paramValue){
		this.params.put(paramName, String.valueOf(paramValue));
		return this;
	}
	
	
	/**
	 * 一般性适用于各种情况发送请求
	 * 如果未设置反序列化类型，直接返回json
	 * @return
	 * @throws Exception
	 */
	public Object sendRequest() throws Exception {
		requestPrepare();
		CloseableHttpClient httpClient = null;
		if(needLogin){
		    httpClient = login();
		}
		if(needAuth){
		    httpClient = auth(); 
		}
		String rpcId = "【"+UUID.randomUUID().toString();
		log4j.info(rpcId+"请求URL】:"+this.uri);
		if(CollectionUtils.isNotEmpty(nvps)){
			log4j.info(rpcId+"请求参数】:" + mapper.writeValueAsString(this.nvps));
		}
		if(requestBody!=null){
			log4j.info(rpcId+"请求JOSN】:" + mapper.writeValueAsString(this.requestBody));
		}
		String json = response(httpClient);
		log4j.info(rpcId+"响应结果】:"+json);
		if(responseType==null){
			return json;
		}
		return mapper.readValue(json, responseType);
	}
	
	/**
	 * 向担保业务系统请求
	 * @return
	 * @throws Exception
	 */
	public JsonResponse sendRequestToCredit() throws Exception {
		requestPrepare();
		String json = response(login());
		JsonResponse jsonResponse = mapper.readValue(json, JsonResponse.class);
		if(responseType==null){
			return jsonResponse;
		}
		JsonNode root = mapper.readTree(json);
		JsonNode resultNode = root.get("result");
		Object result = mapper.readValue(resultNode.toString(),responseType);
		jsonResponse.setResult(result);
		return jsonResponse;
	}
	
	/**
	 * 向epik系统请求
	 * @return
	 * @throws Exception
	 */
	public InvokeResult sendRequestToEpik() throws Exception {
		requestPrepare();
		String json = response(null);
		InvokeResult invokeResult = mapper.readValue(json, InvokeResult.class);
		if(responseType==null){
			return invokeResult;
		}
		List<Object> list = new ArrayList<Object>();
		JsonNode root = mapper.readTree(json);
		JsonNode dataList = root.get("dataList");
		Iterator<JsonNode> iterator = dataList.elements();
		while(iterator.hasNext()){
			JsonNode node = iterator.next();
			Object obj = mapper.readValue(node.toString(), responseType);
			list.add(obj);
		}
		invokeResult.setData(list);
		return invokeResult;
	}
	
	/**
	 * 发送请求前准备工作，比如设置消息头
	 * @throws Exception
	 */
	private void requestPrepare() throws Exception{
		switch (restMethod) {
		case GET:
			contactURI();
			if(this.httpResquest == null){
				this.httpResquest = new HttpGet(uri);
			}else{
				this.httpResquest.setURI(new URI(uri));
			}
			break;
		case PUT:
			this.httpResquest = setBody(new HttpPut(this.uri),requestBody,mapper);
			break;
		case POST:
			this.httpResquest = setBody(new HttpPost(this.uri),requestBody,mapper);
			break;
		case DELETE:
			contactURI();
			this.httpResquest = new HttpDelete(this.uri);
			break;
		default:
			break;
		}
		//设置消息头
		setHeader();
	}
	
	/**
	 * 
	 * TODO:认证
	 * @category
	 * @author: yuanjianing@hanhua.com
	 * @since: 2015年7月8日
	 * @return
	 * @throws Exception 
	 */
	private CloseableHttpClient auth(){
	    String address = uri.substring(uri.indexOf("//")+2, uri.indexOf(":",5));
        String port = uri.substring(uri.indexOf(":",5)+1,uri.indexOf("/", uri.indexOf(":",5)+1));
	    HttpHost target = new HttpHost(address, Integer.valueOf(port),"http");
	    CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials(userName, password));
	    CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        return httpClient;
        
	}
	
	/**
	 * 登陆
	 * @return
	 * @throws Exception
	 */
	private CloseableHttpClient login() throws Exception{
		CloseableHttpClient httpClient = null;
		try{
		    httpClient = HttpClients.createDefault();
    		if(loginURI==null){
    		    loginURI = uri; 
    		}
    		if(getCookieStore()==null){
    			this.cookieStore = new BasicCookieStore();
    		}
    		setHttpContext(new BasicHttpContext());
    		getHttpContext().setAttribute(HttpClientContext.COOKIE_STORE, getCookieStore());
    		loginRequest = new HttpPost(loginURI);
    		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
    	    nvps.add(new BasicNameValuePair("username", getUserName()));  
    	    nvps.add(new BasicNameValuePair("password", getPassword()));
    	    loginRequest.setEntity(new UrlEncodedFormEntity(nvps,encoding));
    	    httpClient.execute(loginRequest,getHttpContext());
		}catch(Exception e){
		    throw e;
		}finally{
		    loginRequest.releaseConnection();  
		}
		return httpClient;
	}
	
	
	/**
	 * 得到响应JSON信息
	 * @param httpClient
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private String response(CloseableHttpClient httpClient) throws ClientProtocolException, IOException{
		if(httpClient==null){
			httpClient = HttpClients.createDefault();
		}
		String content = null;
		try{
		    RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(requestTimeout).setConnectTimeout(connetTimeout).setRedirectsEnabled(redirectsEnabled).build();  
		    httpResquest.setConfig(requestConfig);
		    HttpResponse response;
		    if(httpContext==null){
		    	httpContext = HttpClientContext.create();
		    	if(getCookieStore()!=null){
		    		((HttpClientContext)httpContext).setCookieStore(getCookieStore());  
		    	}
		    }
		    response = httpClient.execute(httpResquest,httpContext);
		    Header setCookieHeader = response.getFirstHeader("Set-Cookie");
		    if(setCookieHeader!=null){
		    	String setCookie = setCookieHeader.getValue();
		    	if(StringUtils.isNotBlank(setCookie)){
			    	jsessionid = setCookie.substring("JSESSIONID=".length(),setCookie.indexOf(";"));  
		    	}
		    }
		    Header[] headers = response.getAllHeaders();
		    for (Header header : headers) {
				responseHeader.put(header.getName(), header.getValue());
			}
		    responseStatusCode=response.getStatusLine().getStatusCode();
    		content = EntityUtils.toString(response.getEntity(),encoding);
		}catch(ClientProtocolException ce){
		    throw ce;
		}catch(IOException e){
            throw e;
        }
		finally{
		    httpResquest.releaseConnection();
		    httpClient.close();
		}
		return content;
	}
	
	/**
	 * 设置消息头
	 */
	private void setHeader(){
		if(!getRequestHeader().isEmpty()){
			Set<String> keySet = getRequestHeader().keySet();
			for (String key : keySet) {
				this.httpResquest.addHeader(key, getRequestHeader().get(key));
			}
		}
	}
	
	/**
	 * 向URL后边拼接参数
	 * @throws UnsupportedEncodingException
	 */
	public void contactURI() throws UnsupportedEncodingException{
		if(!params.isEmpty()){
			Set<String> keySet = params.keySet();
			StringBuilder url = new StringBuilder(uri).append("?");
			for (String key : keySet) {
				url.append(URLEncoder.encode(key,encoding)).append("=").append(URLEncoder.encode(params.get(key),encoding)).append("&");
			}
			url.setLength(url.length()-1);
			this.uri = url.toString();
		}
	}
	
	/**
	 * 设置请求参数
	 * @param httpEntityRequest
	 * @param requestBody
	 * @param mapper
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private HttpEntityEnclosingRequestBase setBody(HttpEntityEnclosingRequestBase httpEntityRequest,Object requestBody,ObjectMapper mapper) throws JsonGenerationException, JsonMappingException, IOException{
		String body = null;
	    if(requestBody!=null){
	    	if(String.class.equals(requestBody.getClass())){
		        body = (String)requestBody;
			}else{
			    body = mapper.writeValueAsString(requestBody);
			}
	    	httpEntityRequest.setEntity(new StringEntity(body,encoding));
	    }else{
	    	httpEntityRequest.setEntity(new UrlEncodedFormEntity(nvps,encoding));
	    }
		return httpEntityRequest;
	}
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public RestMethod getRestMethod() {
		return restMethod;
	}

	public Class<?> getResponseType() {
		return responseType;
	}

	public Object getRequestBody() {
		return requestBody;
	}

	public RestClient setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
		return this;
	}

	public Map<String,String> getParams() {
		return params;
	}

	public RestClient setParams(Map<String,String> params) {
		this.params = params;
		return this;
	}
	
	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<NameValuePair> getNvps() {
		return nvps;
	}

	public RestClient setNvps(List<NameValuePair> nvps) {
		this.nvps = nvps;
		return this;
	}
	
	public int getConnectTimeout(){
		return this.connetTimeout;
	}
	
	public int getRequestTimeout(){
		return this.requestTimeout;
	}

	public HttpContext getHttpContext() {
		return httpContext;
	}

	public RestClient setHttpContext(HttpContext httpContext) {
		this.httpContext = httpContext;
		return this;
	}

	public Map<String,String> getResponseHeader() {
		return responseHeader;
	}
	
	public String getResponseHeaderValue(String headerName) {
		return responseHeader.get(headerName);
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}
	public String getJsessionid() {
		return jsessionid;
	}
	public Map<String,String> getRequestHeader() {
		return requestHeader;
	}

	public RestClient setRequestHeader(Map<String,String> requestHeader) {
		this.requestHeader = requestHeader;
		return this;
	}
	public int getResponseStatusCode() {
		return responseStatusCode;
	}

	public boolean isRedirectsEnabled() {
		return redirectsEnabled;
	}

	public RestClient setRedirectsEnabled(boolean redirectsEnabled) {
		this.redirectsEnabled = redirectsEnabled;
		return this;
	}

	/**
	 * rest 方法
	 * @author thinkpad
	 *
	 */
	private enum RestMethod {
		GET,POST,PUT,DELETE,PATCH,OPTIONS,HEAD,TRACE,CONNECT
	}
}

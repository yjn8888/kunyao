package com.epik.util.json.filter;

import javax.servlet.http.HttpServlet;

/**
 * 
 * 此类描述的是：jsonfilter工具初始化
 * @author: yuanjaining@hanhua.com
 * @since : 2015年6月24日
 */
public class JsonFilterInitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2580697677726528012L;
	
	private final static String[] packageName = {"com.hanhua","com.epik"};
	static{
		JSONUtil.initMapper(null);
        JsonFilterConfig.scanJsonFilter(packageName);
        JsonFilterConfig.initSimpleFilterProviderPool();
	}

}

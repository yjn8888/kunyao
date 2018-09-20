package com.kunyao.core.entity.factory;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.kunyao.core.entity.access.BaseAccessInfo;
import com.kunyao.core.entity.access.BaseInvokeInfo;
import com.kunyao.core.entity.access.BaseRequestInfo;
import com.kunyao.core.entity.access.BaseSourceInfo;
import com.kunyao.core.entity.access.BaseUserInfo;


/**
 * 此类描述的是：访问信息值对象初始化:
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年7月24日
 */
public class AccessInfoFactory {
    
    /**
     * 访问信息值对象初始化
     * @category
     * @author: zhangrongbinbj@hanhua.com
     * @since: 2015年7月24日
     * @param request
     * @return
     * @throws IllegalArgumentException
     */
    public static BaseAccessInfo initAccessInfoFromHttpRequestHeader(HttpServletRequest request) throws IllegalArgumentException {
        String invokeId = request.getHeader("invokeId");
        String accessToken = request.getHeader("accessToken");
        String appUserId = request.getHeader("appUserId");
        String appUserName = request.getHeader("appUserName");
        String sourceSystem = request.getHeader("sourceSystem");
        String sourceIp = request.getRemoteAddr();
        String requestUrl = request.getLocalAddr() + ":" + request.getServerPort() + request.getRequestURI();
        String requestMethod = request.getMethod();
        BaseInvokeInfo invokeInfo = new BaseInvokeInfo(invokeId, accessToken);
        BaseSourceInfo sourceInfo = new BaseSourceInfo(sourceSystem, sourceIp);
        BaseUserInfo userInfo = new BaseUserInfo(appUserId, appUserName);
        BaseRequestInfo requestInfo = new BaseRequestInfo(requestUrl, requestMethod);
        return  new BaseAccessInfo(invokeInfo, sourceInfo, userInfo, requestInfo);
    }
    
    
    /**
     * 从Httprequest初始化accessinfo到Map
     * @category
     * @author: zhangrongbinbj@hanhua.com
     * @since: 2015年7月28日
     * @param request
     * @return
     * @throws IllegalArgumentException
     */
    public static Map<String, String> initAccessInfoFromHttpRequestHeaderToMap(HttpServletRequest request) throws IllegalArgumentException {
        Map<String, String> map = new HashMap<String, String>();
        String invokeId = request.getHeader("invokeId");
        String accessToken = request.getHeader("accessToken");
        String appUserId = request.getHeader("appUserId");
        String appUserName = request.getHeader("appUserName");
        String sourceSystem = request.getHeader("sourceSystem");
        String sourceIp = request.getRemoteAddr();
        String requestUrl = request.getLocalAddr() + ":" + request.getServerPort() + request.getRequestURI();
        String requestMethod = request.getMethod();
        map.put("invokeId", invokeId);
        map.put("accessToken", accessToken);
        map.put("appUserId", appUserId);
        map.put("appUserName", appUserName);
        map.put("sourceSystem", sourceSystem);
        map.put("sourceIp", sourceIp);
        map.put("requestUrl", requestUrl);
        map.put("requestMethod", requestMethod);
//        BaseInvokeInfo invokeInfo = new BaseInvokeInfo(invokeId, accessToken);
//        BaseSourceInfo sourceInfo = new BaseSourceInfo(sourceSystem, sourceIp);
//        BaseUserInfo userInfo = new BaseUserInfo(appUserId, appUserName);
//        BaseRequestInfo requestInfo = new BaseRequestInfo(requestUrl, requestMethod);
        return  map;
    }
  
    /**
     * 从Map初始化accessinfo
     * @category
     * @author: zhangrongbinbj@hanhua.com
     * @since: 2015年7月28日
     * @param map
     * @return
     */
    public static BaseAccessInfo initAccessInfoFromMap(Map<String, String> map) {
        String invokeId = (String) map.get("invokeId");
        String accessToken = (String) map.get("accessToken");
        String ssoToken = (String) map.get("ssoToken");
        String appUserId = (String) map.get("appUserId");
        String appUserName = (String) map.get("appUserName");
        String sourceSystem = (String) map.get("sourceSystem");
        String sourceIp =  (String) map.get("sourceIp");
        String requestUrl =  (String) map.get("requestUrl");
        String requestMethod =  (String) map.get("requestMethod");
        BaseInvokeInfo invokeInfo = new BaseInvokeInfo(invokeId, accessToken);
        BaseSourceInfo sourceInfo = new BaseSourceInfo(sourceSystem, sourceIp);
        BaseUserInfo userInfo = new BaseUserInfo(appUserId, appUserName, ssoToken);
        BaseRequestInfo requestInfo = new BaseRequestInfo(requestUrl, requestMethod);
        return  new BaseAccessInfo(invokeInfo, sourceInfo, userInfo, requestInfo);
    }
}

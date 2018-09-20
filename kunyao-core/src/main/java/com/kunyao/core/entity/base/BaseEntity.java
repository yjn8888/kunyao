package com.kunyao.core.entity.base;

import java.io.Serializable;

import com.kunyao.core.entity.access.BaseAccessInfo;


/**
 * 
 * 此类描述的是：kunyao服务domain基类，所有domain应该继承此类
 * @author: yuanjaining@hanhua.com
 * @since : 2015年6月24日
 */
public abstract class BaseEntity implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1411481996191670750L;
	
	
	
	
	/**
	 * @author: zhangrongbinbj@hanhua.com
	 * @category accessInfo 框架会反射得到相应访问信息放到这个变量当中
	 * @since: 2015年7月24日
	 */
	protected BaseAccessInfo accessInfo;
}

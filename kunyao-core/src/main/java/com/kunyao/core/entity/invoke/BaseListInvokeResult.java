package com.kunyao.core.entity.invoke;


/**
 * 此类描述的是：BaseListInvokeResult
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年8月6日
 */
public class BaseListInvokeResult<E> extends BaseInvokeResult<E>{
	private Long itemCount;

	public Long getItemCount() {
		return itemCount;
	}

	public void setItemCount(Long itemCount) {
		this.itemCount = itemCount;
	}
}

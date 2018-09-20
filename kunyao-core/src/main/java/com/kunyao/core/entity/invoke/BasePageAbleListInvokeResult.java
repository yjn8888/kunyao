package com.kunyao.core.entity.invoke;



/**
 * 此类描述的是：BasePageAbleListInvokeResult
 * @author: zhangrongbinbj@hanhua.com
 * @since : 2015年8月6日
 */
public class BasePageAbleListInvokeResult<E> extends BaseInvokeResult<E>{
	
	/**
	 * 每页最多记录数
	 */
	private int pageSize;
	
	/**
	 * 页码总数
	 */
	private int pageCount;
	
	/**
	 * 当前页码
	 */
	private int pageNumber;
	
	/**
	 * 总记录数
	 */
	private int recordCount;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
}

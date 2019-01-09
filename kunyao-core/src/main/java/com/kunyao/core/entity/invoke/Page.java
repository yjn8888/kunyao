package com.kunyao.core.entity.invoke;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 此类描述的是：Page
 */
public class Page<T> implements Serializable{
	/**
	 * 
	 */
//	private static final long serialVersionUID = -3349291869076816256L;
	
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
	
	/**
	 * 当前页的记录列表
	 */
	private List<T> resultList;

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

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
}

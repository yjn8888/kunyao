package com.kunyao.core.entity.invoke;

import io.swagger.annotations.ApiModelProperty;

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
	private static final long serialVersionUID = -3349291869076816256L;
	
	/**
	 * 每页最多记录数
	 */
    @ApiModelProperty(
            value = "每页最多记录数", 
            example = "20", 
            required = false)
	private int pageSize;
	
	/**
	 * 页码总数
	 */
    @ApiModelProperty(
            value = "页码总数", 
            example = "5", 
            required = false)
	private int pageCount;
	
	/**
	 * 当前页码
	 */
    @ApiModelProperty(
            value = "当前页码", 
            example = "3", 
            required = false)
	private int pageNumber;
	
	/**
	 * 总记录数
	 */
    @ApiModelProperty(
            value = "总记录数", 
            example = "100", 
            required = false)
	private int recordCount;
	
	/**
	 * 当前页的记录列表
	 */
    @ApiModelProperty(
            value = "当前页的数据记录列表", 
            required = false)
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
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
    public int getRecordCount() {
        return recordCount;
    }
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
	
}

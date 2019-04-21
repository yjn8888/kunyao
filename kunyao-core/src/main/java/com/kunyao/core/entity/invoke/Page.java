package com.kunyao.core.entity.invoke;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 此类描述的是：Page
 */
@Data
public class Page<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3349291869076816256L;
	
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
}

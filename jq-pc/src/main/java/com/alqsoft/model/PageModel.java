package com.alqsoft.model;

import java.io.Serializable;

public class PageModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageNo;
	private int pageSize;
	private int rowCount;
	private int pageCount;
	private int startIndex;
	private String search;
	
	

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if(pageNo!=0){
			startIndex=pageNo*this.pageSize;
		}
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		// 计算总页数
		this.pageCount = (this.rowCount % this.pageSize == 0) ? (this.rowCount / this.pageSize)
				: (this.rowCount / this.pageSize + 1);
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return pageNo * pageSize;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}

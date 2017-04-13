package com.yst.web.utils;
/**
 * bootstrap分页数据对象模板
 * @author lenovo
 *
 */
public class BootStrapResult<T> {
	
	private String result;//返回状态说明
	
	private T data;//返回数据
	
	private Integer recordsFiltered;//返回总条数
	
	private Integer recordsTotal;//返回总条数
	
	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
/*
	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}*/
	
	
}

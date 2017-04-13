package com.alqsoft.entity;

import java.io.Serializable;
import java.util.Map;

public class AppResult implements Serializable{
	private static final long serialVersionUID = 1L;
	//成功失败返回字符串
	public static final String SUCCESS ="SUCCESS";//成功
	public static final String FAILED ="FAILED";//失败
	public static final String NO_IMAGE ="NO_IMAGE";//无图片参数
	
	public static final Integer VERSION =1;//无图片参数
	/**
	 * 用户状态     已删除
	 */
	public static final Integer STATUS_DISABLED = 1;
	
	/**
	 * 用户状态  未删除
	 */
	public static final Integer STATUS_ENABLED = 0;
	private Integer version;
	private String result;
	private String error_info;
	private Object data;
	
	//上传返回参数
	private String img_url;
	private String img_path;
	private Map<String,String> img_urls;
	
	//分页返回参数
	private Integer start;
	private Integer length;
	private Integer page;
	private Integer size;
	private Integer merchant_id;
	private Integer customer_id;
	private Integer doctor_id;
	private Integer recordsTotal;
	private Integer recordsFiltered;
	private Integer currPage;
	private Integer totalPage;
	private PageModel page_model;
	private String uri;
	
	
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public PageModel getPage_model() {
		return page_model;
	}
	public void setPage_model(PageModel page_model) {
		this.page_model = page_model;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}
	public Integer getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
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
	
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getError_info() {
		return error_info;
	}
	public void setError_info(String error_info) {
		this.error_info = error_info;
	}
	public Map<String, String> getImg_urls() {
		return img_urls;
	}
	public void setImg_urls(Map<String, String> img_urls) {
		this.img_urls = img_urls;
	}
	
}

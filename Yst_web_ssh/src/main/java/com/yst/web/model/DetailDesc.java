package com.yst.web.model;

import java.io.Serializable;

public class DetailDesc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer desc_id;
	private Integer main_id;
	private Integer desc_for;
	private Integer desc_type;
	private String desc_content;
	private Integer desc_index;
	
	private String order;
	
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Integer getDesc_id() {
		return desc_id;
	}
	public void setDesc_id(Integer desc_id) {
		this.desc_id = desc_id;
	}
	public Integer getDesc_for() {
		return desc_for;
	}
	public void setDesc_for(Integer desc_for) {
		this.desc_for = desc_for;
	}
	public Integer getDesc_type() {
		return desc_type;
	}
	public void setDesc_type(Integer desc_type) {
		this.desc_type = desc_type;
	}
	public String getDesc_content() {
		return desc_content;
	}
	public void setDesc_content(String desc_content) {
		this.desc_content = desc_content;
	}
	
	public Integer getDesc_index() {
		return desc_index;
	}
	public void setDesc_index(Integer desc_index) {
		this.desc_index = desc_index;
	}
	public Integer getMain_id() {
		return main_id;
	}
	public void setMain_id(Integer main_id) {
		this.main_id = main_id;
	}
	
	
}

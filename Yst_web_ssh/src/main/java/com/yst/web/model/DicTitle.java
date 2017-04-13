package com.yst.web.model;

import java.io.Serializable;

public class DicTitle implements Serializable {
	private static final long serialVersionUID = -3045380927584738272L;
	private Integer title_id;
	private String name;
	private String des;
	
	
	
	public Integer getTitle_id() {
		return title_id;
	}
	public void setTitle_id(Integer title_id) {
		this.title_id = title_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	
	
}

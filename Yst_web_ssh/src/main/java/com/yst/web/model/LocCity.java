package com.yst.web.model;

import java.io.Serializable;
public class LocCity implements Serializable{

	private static final long serialVersionUID = -245629232648739142L;
	private Integer city_id;
	private transient LocProv locProv;//所属省的id
	private String name;//名称
	
	public LocProv getLocProv() {
		return locProv;
	}
	public void setLocProv(LocProv locProv) {
		this.locProv = locProv;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCity_id() {
		return city_id;
	}
	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}
	
	
}

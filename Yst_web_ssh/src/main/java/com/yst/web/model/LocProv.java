package com.yst.web.model;

import java.io.Serializable;
import java.util.Set;


public class LocProv implements Serializable{

	private static final long serialVersionUID = -2361257180488742680L;
	private Integer prov_id;
	private String name;//名称
	private Set<LocCity> locCitys;
	
	public Set<LocCity> getLocCitys() {
		return locCitys;
	}
	public void setLocCitys(Set<LocCity> locCitys) {
		this.locCitys = locCitys;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProv_id() {
		return prov_id;
	}
	public void setProv_id(Integer prov_id) {
		this.prov_id = prov_id;
	}
	
}

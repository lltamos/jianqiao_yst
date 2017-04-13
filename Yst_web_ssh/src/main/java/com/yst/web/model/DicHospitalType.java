package com.yst.web.model;

import java.io.Serializable;

public class DicHospitalType implements Serializable {
	
	
	private static final long serialVersionUID = -6357919930591935083L;
	
	private Integer hospital_type_id;
	private String name;
	
	
	public Integer getHospital_type_id() {
		return hospital_type_id;
	}
	public void setHospital_type_id(Integer hospital_type_id) {
		this.hospital_type_id = hospital_type_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

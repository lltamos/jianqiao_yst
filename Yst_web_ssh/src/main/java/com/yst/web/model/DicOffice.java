package com.yst.web.model;

public class DicOffice implements java.io.Serializable{
	
	private static final long serialVersionUID = -5439923380818325324L;
	
	private Integer office_id;
	private String name;
	private String des;
	
	
	public Integer getOffice_id() {
		return office_id;
	}
	public void setOffice_id(Integer office_id) {
		this.office_id = office_id;
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

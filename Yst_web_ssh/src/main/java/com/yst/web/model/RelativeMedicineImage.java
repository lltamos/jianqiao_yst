package com.yst.web.model;

import java.io.Serializable;

public class RelativeMedicineImage implements Serializable{

	private static final long serialVersionUID = 4243145212019718464L;

	
	private Integer image_id;
	private Integer record_id;
	private String image_url;
	//private RelativeMedicineRecord relativeMedicineRecord;
	
	
	
	public Integer getImage_id() {
		return image_id;
	}
	public void setImage_id(Integer image_id) {
		this.image_id = image_id;
	}
	public Integer getRecord_id() {
		return record_id;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
}

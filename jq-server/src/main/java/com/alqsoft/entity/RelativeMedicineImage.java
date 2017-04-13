package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "relative_medicine_image")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class RelativeMedicineImage extends IdEntity implements Serializable{

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

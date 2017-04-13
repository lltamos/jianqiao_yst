package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "doctor_image")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class DoctorImage extends IdEntity implements Serializable{
	
	private static final long serialVersionUID = -1149753782412512834L;


	private Long doctorId;//医生的ID
	private String des;//图片描述
	private String image;//图片的位置
	private Long attachmentId;//图片表id
	//private transient Doctors doctor;
	
	
	private String doctorName;
	
	private String name;//未使用
	
	
	
	
	public Long getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	@ForeignKey(name="")
	public Doctors getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctors doctor) {
		this.doctor = doctor;
	}*/
	
	
	

	public String getDes() {
		return des;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}

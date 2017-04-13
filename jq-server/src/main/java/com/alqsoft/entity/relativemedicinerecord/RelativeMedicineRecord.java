package com.alqsoft.entity.relativemedicinerecord;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.alqsoft.entity.customerrelative.CustomerRelative;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 用药记录
 * @author zj
 *
 */
@Entity
@Table(name = "relative_medicine_record")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class RelativeMedicineRecord extends IdEntity implements Serializable{

	private static final long serialVersionUID = -883497390677667742L;
	
	private Long relativeId;//相关人员id
	private String des;//描述
	private String doctorName;//医生名称
	private String officeName;//科室
	private Integer during;//用药时长
	private Date startDate;//开始服用时间
	private String imageMedicineBox;//药盒照片
	private String imageRecipe;//处方照片
	private transient CustomerRelative customerRelative;
	//private transient Set<RelativeMedicineImage> relativeMedicineImageSet;

	
	private String relativeName;




	public Long getRelativeId() {
		return relativeId;
	}


	public void setRelativeId(Long relativeId) {
		this.relativeId = relativeId;
	}


	public String getDes() {
		return des;
	}


	public void setDes(String des) {
		this.des = des;
	}


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public String getOfficeName() {
		return officeName;
	}


	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}


	public Integer getDuring() {
		return during;
	}


	public void setDuring(Integer during) {
		this.during = during;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public String getImageMedicineBox() {
		return imageMedicineBox;
	}


	public void setImageMedicineBox(String imageMedicineBox) {
		this.imageMedicineBox = imageMedicineBox;
	}


	public String getImageRecipe() {
		return imageRecipe;
	}


	public void setImageRecipe(String imageRecipe) {
		this.imageRecipe = imageRecipe;
	}


	public CustomerRelative getCustomerRelative() {
		return customerRelative;
	}


	public void setCustomerRelative(CustomerRelative customerRelative) {
		this.customerRelative = customerRelative;
	}

	public String getRelativeName() {
		return relativeName;
	}


	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	

}

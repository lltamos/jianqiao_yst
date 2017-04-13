package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;

import com.yst.web.annotations.Field;
/**
 * 
 * RelativeMedicineRecord.java
 * @Description: 健桥-用药
 * @author 朱军
 * @copyright 2015-2016
 * @create-time 2016年11月21日 下午5:43:54
 *
 */
public class RelativeMedicineRecord implements Serializable{

	private static final long serialVersionUID = -883497390677667742L;
	
	private Integer record_id;//病例id
	private Integer relative_id;//相关人员id
	@Field
	private String des;//描述
	private String doctor_name;//医生名称
	private String office_name;//科室
	@Field
	private Integer during;//用药时长
	private Date start_date;//开始服用时间
	private String image_medicine_box;//药盒照片
	private String image_recipe;//处方照片
	private transient CustomerRelative customerRelative;
	//private transient Set<RelativeMedicineImage> relativeMedicineImageSet;

	
	private String str_start_date;
	private String str_relative;
	

	public CustomerRelative getCustomerRelative() {
		return customerRelative;
	}

	public void setCustomerRelative(CustomerRelative customerRelative) {
		this.customerRelative = customerRelative;
	}

	public String getStr_relative() {
		return str_relative;
	}

	public void setStr_relative(String str_relative) {
		this.str_relative = str_relative;
	}

	public String getStr_start_date() {
		return str_start_date;
	}

	public void setStr_start_date(String str_start_date) {
		this.str_start_date = str_start_date;
	}

	public Integer getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}

	public Integer getRelative_id() {
		return relative_id;
	}

	public void setRelative_id(Integer relative_id) {
		this.relative_id = relative_id;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getOffice_name() {
		return office_name;
	}

	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}

	public Integer getDuring() {
		return during;
	}

	public void setDuring(Integer during) {
		this.during = during;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public String getImage_medicine_box() {
		return image_medicine_box;
	}

	public void setImage_medicine_box(String image_medicine_box) {
		this.image_medicine_box = image_medicine_box;
	}

	public String getImage_recipe() {
		return image_recipe;
	}

	public void setImage_recipe(String image_recipe) {
		this.image_recipe = image_recipe;
	}

}

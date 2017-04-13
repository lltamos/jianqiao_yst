package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class CustomerRelative implements Serializable{


	private static final long serialVersionUID = 1457461845835427891L;
	
	private Integer relative_id;
	private String name;
	private String phone;
	private String image;
	private String prov;
	private String city;
	private Integer sex;
	private Date birth_date;
	private Integer customer_id;
	private Integer relation_id;
	private Integer patient_id;
	private transient Set<CustomerIllnessRecord> customerIllnessRecord;
	private transient Set<RelativeMedicineRecord> relativeMedicineRecord;
	private transient Customer customer;
	
	
	
	public Integer getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}
	public Set<RelativeMedicineRecord> getRelativeMedicineRecord() {
		return relativeMedicineRecord;
	}
	public void setRelativeMedicineRecord(
			Set<RelativeMedicineRecord> relativeMedicineRecord) {
		this.relativeMedicineRecord = relativeMedicineRecord;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Set<CustomerIllnessRecord> getCustomerIllnessRecord() {
		return customerIllnessRecord;
	}
	public void setCustomerIllnessRecord(
			Set<CustomerIllnessRecord> customerIllnessRecord) {
		this.customerIllnessRecord = customerIllnessRecord;
	}
	public Integer getRelative_id() {
		return relative_id;
	}
	public void setRelative_id(Integer relative_id) {
		this.relative_id = relative_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getRelation_id() {
		return relation_id;
	}
	public void setRelation_id(Integer relation_id) {
		this.relation_id = relation_id;
	}
	
	
	
}

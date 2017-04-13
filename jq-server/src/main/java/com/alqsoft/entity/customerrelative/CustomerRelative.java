package com.alqsoft.entity.customerrelative;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.alqsoft.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用户关系表
 * @author zj
 *
 */
@Entity
@Table(name = "customer_relative")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class CustomerRelative extends IdEntity implements Serializable{


	private static final long serialVersionUID = 1457461845835427891L;
	
	private Long relativeId;
	private String name;
	private String phone;
	private String image;
	private String prov;
	private String city;
	private Integer sex;
	private Date birthDate;
	private Integer customerId;
	private Integer relationId;
	private Integer patient_id;
	/*private transient Set<CustomerIllnessRecord> customerIllnessRecord;
	private transient Set<RelativeMedicineRecord> relativeMedicineRecord;*/
	private transient Customer customer;
	
	
	
	public Integer getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	
	public Long getRelativeId() {
		return relativeId;
	}
	public void setRelativeId(Long relativeId) {
		this.relativeId = relativeId;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	
	
	
}

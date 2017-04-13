package com.yst.web.entity.patient;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 患者
*   
* 项目名称：jianqiao  
* 类名称：Patient  
* 类描述：  
* 创建人：whl  
* 创建时间：2016年2月22日 下午3:46:29  
* 修改人：whl  
* 修改时间：2016年2月22日 下午3:46:29  
* 修改备注：  
* @version   
*
 */
@Entity
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class Patient extends IdEntity{
	
	private String patientName;//患者姓名
	
	private String patientPhone;//患者电话
	
	private String address;//患者地址
	
//	private Customer customer;//患者推荐人
	
//	private Customer expertCustomer;//专家推荐人
	
	private Integer sex;//性别
	
	private String relationId;//关系字段表

	private Integer custmoerId;//用户
	
	public Integer getCustmoerId() {
		return custmoerId;
	}
	public void setCustmoerId(Integer custmoerId) {
		this.custmoerId = custmoerId;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/*@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	@ForeignKey(name="")
	public Customer getCustomer() {
		return customer;
	}*/
	/*public void setCustomer(Customer customer) {
		this.customer = customer;
	}*/
	/*@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "expert_customer_id")
	@ForeignKey(name="")
	public Customer getExpertCustomer() {
		return expertCustomer;
	}
	public void setExpertCustomer(Customer expertCustomer) {
		this.expertCustomer = expertCustomer;
	}*/
}

package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
* @ClassName: Store 
* @Description: 分院实体
* @date 2017年3月31日 上午9:25:50 
*
 */
@Entity
@Table(name = "store")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Store extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;//名称
	
	private String des;//介绍
	
	private String storePhone;//电话
	
	private String province;//省
	
	private String city;//市
	
	private String address;//详细地址
	
	private String latitude;//经度
	
	private String longitude;//纬度

	private Integer merchantId;//总院id
	private String merchantName;//总院名称
	private Integer deleted;//1为删除，0为未删除
	
	private Long hospitalTypeId;//医院等级id
	private String hospitalTypeName;//医院等级名称
	
	
	public Long getHospitalTypeId() {
		return hospitalTypeId;
	}
	public void setHospitalTypeId(Long hospitalTypeId) {
		this.hospitalTypeId = hospitalTypeId;
	}
	public String getHospitalTypeName() {
		return hospitalTypeName;
	}
	public void setHospitalTypeName(String hospitalTypeName) {
		this.hospitalTypeName = hospitalTypeName;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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
	public String getStorePhone() {
		return storePhone;
	}
	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
}

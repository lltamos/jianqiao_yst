package com.yst.web.entity.doctor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年6月20日 上午11:11:02
 * 
 */
@Entity
@Table(name = "doctor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Doctors {
	
	@Id
	private Integer doctor_id;
	
	private String name;
	
	private Integer office_id;
	
	private Integer title_id;
	
	private Integer spec_id;
	
	private Integer customer_id;
	
	private Integer type;
	
	private String des;
	
	private String image_header;
	
	private String image_work_1;
	
	private String image_work_2;
	
	private Integer hospitalypeId;
	
	private String hospital;
	
	private String address;
	
	private String latitude;
	
	private String longitude;
	
	private Integer deleted;
	
	private Integer verify;
	
	private String reject_reason;
	
	private Integer product_type_id;
	
	private Integer merchant_id;
	
	private Integer veiw_count;
	
	private String online_time;
	
	private String updateTime;
	
	private Integer apply_status;//审核状态
	
	//接口返回数据
	@Transient
	private String officeName;
	@Transient
	private String titleName;
	@Transient
	private String specName;
	
	@Transient
	public String getOfficeName() {
		return officeName;
	}

	public String getHospital() {
		return hospital;
	}

	@Transient
	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	@Transient
	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Integer getApply_status() {
		return apply_status;
	}

	public void setApply_status(Integer apply_status) {
		this.apply_status = apply_status;
	}

	public Integer getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(Integer product_type_id) {
		this.product_type_id = product_type_id;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Integer getHospitalypeId() {
		return hospitalypeId;
	}

	public void setHospitalypeId(Integer hospitalypeId) {
		this.hospitalypeId = hospitalypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOffice_id() {
		return office_id;
	}

	public void setOffice_id(Integer office_id) {
		this.office_id = office_id;
	}

	public Integer getTitle_id() {
		return title_id;
	}

	public void setTitle_id(Integer title_id) {
		this.title_id = title_id;
	}

	public Integer getSpec_id() {
		return spec_id;
	}

	public void setSpec_id(Integer spec_id) {
		this.spec_id = spec_id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getVerify() {
		return verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
	}

	public Integer getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getImage_header() {
		return image_header;
	}

	public void setImage_header(String image_header) {
		this.image_header = image_header;
	}

	public String getImage_work_1() {
		return image_work_1;
	}

	public void setImage_work_1(String image_work_1) {
		this.image_work_1 = image_work_1;
	}

	public String getImage_work_2() {
		return image_work_2;
	}

	public void setImage_work_2(String image_work_2) {
		this.image_work_2 = image_work_2;
	}

	public String getReject_reason() {
		return reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	public Integer getVeiw_count() {
		return veiw_count;
	}

	public void setVeiw_count(Integer veiw_count) {
		this.veiw_count = veiw_count;
	}

	public String getOnline_time() {
		return online_time;
	}

	public void setOnline_time(String online_time) {
		this.online_time = online_time;
	}

}

package com.alqsoft.entity.doctor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

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
public class Doctors  extends IdEntity{
	
	private static final long serialVersionUID = 1L;

	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "doctor_id", unique = true, nullable = false)
	private Long id;*/
	@NotBlank(message = "医生姓名不能为空",groups={doctorValidation.class})
	private String name;
	//医生科室ID
	private Long officeId;
	//医生职称ID
	private Long titleId;
	//医生专长ID
	private Long specId;
	//医院类型ID
	private Long hospitalypeId;
	//所属城市ID
	private Long cityId;
	private String hospitalypeName;
	
	private String officeName;
	
	private String titleName;
	
	private String specName;
	
	private String merchantName;
	
	private Long customerId;
	
	private String customerPhone;//手机号
	//代表医生类型，1 为家庭顾问，2为疑难杂症专家
	private Integer type;
	
	private String des;
	
	private String image_header;
	
	private String imageWork1;
	
	private String imageWork2;
	
	private String imageWork3;
	
	//医院名称
	private String hospital;
	
	private String address;
	
	private String latitude;
	
	private String longitude;
	
	private Integer deleted;
	
	private Integer verify;
	
	private String rejectReason;
	
	private Integer productTypeId;
	
	private Long merchantId;
	
	private Integer veiwCount;
	
	private String onlineTime;
	
	private Integer applyStatus;//审核状态
	//是否认证:认证为1，拒绝为2 等待验证为0
	private String imageCode;
	
	private String reason;//审核备注
	//private DicOffice  dicOffice;
	
	
	
	public String getOfficeName() {
		return officeName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getImageWork3() {
		return imageWork3;
	}

	public void setImageWork3(String imageWork3) {
		this.imageWork3 = imageWork3;
	}

	@Transient
	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getHospital() {
		return hospital;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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


	public String getImage_header() {
		return image_header;
	}

	public void setImage_header(String image_header) {
		this.image_header = image_header;
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getImageWork1() {
		return imageWork1;
	}

	public void setImageWork1(String imageWork1) {
		this.imageWork1 = imageWork1;
	}

	public String getImageWork2() {
		return imageWork2;
	}

	public void setImageWork2(String imageWork2) {
		this.imageWork2 = imageWork2;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}


	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getVeiwCount() {
		return veiwCount;
	}

	public void setVeiwCount(Integer veiwCount) {
		this.veiwCount = veiwCount;
	}

	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	public interface doctorValidation{
		
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}

	public Long getSpecId() {
		return specId;
	}

	public void setSpecId(Long specId) {
		this.specId = specId;
	}

	public Long getHospitalypeId() {
		return hospitalypeId;
	}

	public void setHospitalypeId(Long hospitalypeId) {
		this.hospitalypeId = hospitalypeId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getHospitalypeName() {
		return hospitalypeName;
	}

	public void setHospitalypeName(String hospitalypeName) {
		this.hospitalypeName = hospitalypeName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
	
	

	
 	/*@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "office_id")
	@ForeignKey(name="")
	public DicOffice getDicOffice() {
		return dicOffice;
	}

	public void setDicOffice(DicOffice dicOffice) {
		this.dicOffice = dicOffice;
	}*/
	 
}

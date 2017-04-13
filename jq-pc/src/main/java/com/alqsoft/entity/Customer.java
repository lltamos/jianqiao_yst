package com.alqsoft.entity;

import java.io.Serializable;
import java.util.Date;
import org.alqframework.orm.hibernate.IdEntity;
import com.alqsoft.entity.Balance;

public class Customer extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	
	private String phone;
	
	private Integer sex;
	
	private Integer age;
	
	private String IDNumber;//身份证号
	
	

	private String nickName;//昵称
	
	private String birthDay;
	private Integer deleted;
	private Date lastLoginTime;
	private Integer isLock;
	private Integer lastFailedTime;
	private String image;
	private String payPassword;
	//传入参数
	private String clientType;
	private Integer version;
	private String code;
	private String password;
	private String newPassword;
	private String data;
	
	private String huanxinId;
	private String huanxinPassword;
	private Balance balance;
	private Integer addressId;
	private Integer type; //0普通用户，1医生，2商户，3患者 
	private String address;//家庭住址
	private Long doctorId; //医生id
	private Long merchantId;//总院id
	private Long customerId;//推荐人id
	
	private Long isRecommender;//是否推荐人 null 0 不是   1是	
	private Long isMerchant;//是否总院 null 0 不是   1是	
	private Integer bankStatus; //是否绑定银行卡  0 未绑定  1已绑定
	private Integer nameAuthentication; //是实名认证  0 未认证  1已认证

	public Long getIsMerchant() {
		return isMerchant;
	}
	public void setIsMerchant(Long isMerchant) {
		this.isMerchant = isMerchant;
	}
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Long getIsRecommender() {
		return isRecommender;

	}
	public void setIsRecommender(Long isRecommender) {
		this.isRecommender = isRecommender;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	private String imageCode;
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Balance getBalance() {
		return balance;
	}
	public void setBalance(Balance balance) {
		this.balance = balance;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public Integer getIsLock() {
		return isLock;
	}
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getLastFailedTime() {
		return lastFailedTime;
	}
	public void setLastFailedTime(Integer lastFailedTime) {
		this.lastFailedTime = lastFailedTime;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getHuanxinId() {
		return huanxinId;
	}
	public void setHuanxinId(String huanxinId) {
		this.huanxinId = huanxinId;
	}
	public String getHuanxinPassword() {
		return huanxinPassword;
	}
	public void setHuanxinPassword(String huanxinPassword) {
		this.huanxinPassword = huanxinPassword;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Integer getBankStatus() {
		return bankStatus;
	}
	public void setBankStatus(Integer bankStatus) {
		this.bankStatus = bankStatus;
	}
	public Integer getNameAuthentication() {
		return nameAuthentication;
	}
	public void setNameAuthentication(Integer nameAuthentication) {
		this.nameAuthentication = nameAuthentication;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
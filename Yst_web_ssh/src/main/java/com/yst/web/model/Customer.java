package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

import com.yst.web.annotations.Field;

public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer customer_id;
	@Field
	private String name;
	@Field
	private String phone;
	@Field
	private Integer sex;
	
	private Integer age;
	@Field
	private String birth_day;
	private Integer deleted;
	private Date update_time;
	private Date delete_time;
	private Date create_time;
	private Date last_login_time;
	private Integer isLock;
	private Integer last_failed_time;
	private String image;
	private String pay_password;
	//传入参数
	private String client_type;
	private Integer version;
	private String code;
	private String password;
	private String new_password;
	private String data;
	
	private String huanxin_id;
	private String huanxin_password;
	private Balance balance;
	private transient Merchant merchant;
	private transient Doctor doctor;
	private Integer address_id;
	private Integer type; //0普通用户，1医生，2商户，3患者
	private Customer recomm_customer;//推荐人
	private String address;//家庭住址
	
	public Customer getRecomm_customer() {
		return recomm_customer;
	}
	public void setRecomm_customer(Customer recomm_customer) {
		this.recomm_customer = recomm_customer;
	}
	@Transient
	private Integer doctor_id;
	
	@Transient
	public Integer getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
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
	public Integer getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	public String getPay_password() {
		return pay_password;
	}
	public void setPay_password(String pay_password) {
		this.pay_password = pay_password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getHuanxin_id() {
		return huanxin_id;
	}
	public void setHuanxin_id(String huanxin_id) {
		this.huanxin_id = huanxin_id;
	}
	public String getHuanxin_password() {
		return huanxin_password;
	}
	public void setHuanxin_password(String huanxin_password) {
		this.huanxin_password = huanxin_password;
	}
	public Balance getBalance() {
		return balance;
	}
	public void setBalance(Balance balance) {
		this.balance = balance;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
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
	public String getClient_type() {
		return client_type;
	}
	public void setClient_type(String client_type) {
		this.client_type = client_type;
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
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Date getDelete_time() {
		return delete_time;
	}
	public void setDelete_time(Date delete_time) {
		this.delete_time = delete_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public Integer getIsLock() {
		return isLock;
	}
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
	public Integer getLast_failed_time() {
		return last_failed_time;
	}
	public void setLast_failed_time(Integer last_failed_time) {
		this.last_failed_time = last_failed_time;
	}
	public String getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(String birth_day) {
		this.birth_day = birth_day;
	}
	
	
}
package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;

import com.yst.web.annotations.Field;


public class Doctor implements Serializable{
	
	private static final long serialVersionUID = 5297495970499686653L;
	@Field
	private Integer doctor_id;
	@Field
	private String name;
	private Integer office_id;
	private Integer spec_id;
	private Integer title_id;
	private Integer customer_id;//用户id
	private Integer merchant_id;//商家id
	private Integer doctor_master_type_id;//服务包类型id
	private Integer type;//代表医生类型，1 为家庭顾问，2为疑难杂症专家
	private Integer veiw_count;//浏览量
	private String des;
	private String image_header;
	private String image_work_1;
	private String image_work_2;
	private Integer hospital_type_id;//医院类型
	@Field
	private String hospital;
	private String address;
	private String latitude;//纬度
	private String longitude;//经度
	private Date create_date;
	private Date update_date;
	private Date delete_date;
	private Integer deleted;
	private String online_time;
	private Integer verify;//是否认证，认证为1，拒绝为2 等待验证为0
	@Field
	private String reject_reason;
	private transient Customer customer;
	private transient DicOffice dicOffice;
	private DicTitle dicTitle;//医生的抬头
	private transient DicSpec dicSpec;//医生专长字典表
	private transient DicHospitalType dicHospitalType;
	private transient DoctorService doctorService;
	private transient ProductType productType;
	private Merchant merchant;
	//医生可咨询星期（比如 周一，周二等）
	private String can_consult_week;
	//可咨询开始时间
	private Date can_consult_starttime;
	//可咨询结束时间
	private Date can_consult_endtime;
	
	
	//接收的参数
	private Integer order_type;//0或不传为默认，1为最近距离,2为人气最高，3为病症分类
	private Double customer_latitude;//纬度
	private Double customer_longitude;//经度 
	
	
	//返回
	private String phone;
	private String str_last_login_time;
	@Field(name="dicOffice.name")
	private String str_office;//科室
	private String str_spec;//专长
	private String str_title;//职称
	private String str_dicHospitalType;//医院类型
	private double distance;//返回经纬度距离
	private String huanxin_id;//环信账号
	private String huanxin_password;
	
	
	private Integer apply_status;//审核状态
	
	


	public Integer getApply_status() {
		return apply_status;
	}

	public void setApply_status(Integer apply_status) {
		this.apply_status = apply_status;
	}

	public String getOnline_time() {
		return online_time;
	}

	public void setOnline_time(String online_time) {
		this.online_time = online_time;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Integer getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}

	public Double getCustomer_latitude() {
		return customer_latitude;
	}

	public void setCustomer_latitude(Double customer_latitude) {
		this.customer_latitude = customer_latitude;
	}

	public Double getCustomer_longitude() {
		return customer_longitude;
	}

	public void setCustomer_longitude(Double customer_longitude) {
		this.customer_longitude = customer_longitude;
	}

	public Integer getOrder_type() {
		return order_type;
	}

	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}

	public Integer getVeiw_count() {
		return veiw_count;
	}

	public void setVeiw_count(Integer veiw_count) {
		this.veiw_count = veiw_count;
	}

	public Integer getDoctor_master_type_id() {
		return doctor_master_type_id;
	}

	public void setDoctor_master_type_id(Integer doctor_master_type_id) {
		this.doctor_master_type_id = doctor_master_type_id;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getHuanxin_password() {
		return huanxin_password;
	}

	public void setHuanxin_password(String huanxin_password) {
		this.huanxin_password = huanxin_password;
	}

	public DoctorService getDoctorService() {
		return doctorService;
	}

	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	public Integer getHospital_type_id() {
		return hospital_type_id;
	}

	public void setHospital_type_id(Integer hospital_type_id) {
		this.hospital_type_id = hospital_type_id;
	}

	public String getStr_dicHospitalType() {
		return str_dicHospitalType;
	}

	public void setStr_dicHospitalType(String str_dicHospitalType) {
		this.str_dicHospitalType = str_dicHospitalType;
	}

	public DicHospitalType getDicHospitalType() {
		return dicHospitalType;
	}

	public void setDicHospitalType(DicHospitalType dicHospitalType) {
		this.dicHospitalType = dicHospitalType;
	}

	public String getHuanxin_id() {
		return huanxin_id;
	}

	public void setHuanxin_id(String huanxin_id) {
		this.huanxin_id = huanxin_id;
	}

	public DicSpec getDicSpec() {
		return dicSpec;
	}

	public void setDicSpec(DicSpec dicSpec) {
		this.dicSpec = dicSpec;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getStr_office() {
		return str_office;
	}

	public void setStr_office(String str_office) {
		this.str_office = str_office;
	}

	public String getStr_spec() {
		return str_spec;
	}

	public void setStr_spec(String str_spec) {
		this.str_spec = str_spec;
	}

	public String getStr_title() {
		return str_title;
	}

	public void setStr_title(String str_title) {
		this.str_title = str_title;
	}

	public DicOffice getDicOffice() {
		return dicOffice;
	}

	public void setDicOffice(DicOffice dicOffice) {
		this.dicOffice = dicOffice;
	}

	public String getStr_last_login_time() {
		return str_last_login_time;
	}

	public void setStr_last_login_time(String str_last_login_time) {
		this.str_last_login_time = str_last_login_time;
	}

	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
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

	public Integer getSpec_id() {
		return spec_id;
	}

	public void setSpec_id(Integer spec_id) {
		this.spec_id = spec_id;
	}

	
	
	public Integer getTitle_id() {
		return title_id;
	}

	public void setTitle_id(Integer title_id) {
		this.title_id = title_id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
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
	
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Date getDelete_date() {
		return delete_date;
	}

	public void setDelete_date(Date delete_date) {
		this.delete_date = delete_date;
	}

	public String getReject_reason() {
		return reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	public DicTitle getDicTitle() {
		return dicTitle;
	}

	public void setDicTitle(DicTitle dicTitle) {
		this.dicTitle = dicTitle;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCan_consult_week() {
		return can_consult_week;
	}

	public void setCan_consult_week(String can_consult_week) {
		this.can_consult_week = can_consult_week;
	}

	public Date getCan_consult_starttime() {
		return can_consult_starttime;
	}

	public void setCan_consult_starttime(Date can_consult_starttime) {
		this.can_consult_starttime = can_consult_starttime;
	}

	public Date getCan_consult_endtime() {
		return can_consult_endtime;
	}

	public void setCan_consult_endtime(Date can_consult_endtime) {
		this.can_consult_endtime = can_consult_endtime;
	}
	
	
	
}

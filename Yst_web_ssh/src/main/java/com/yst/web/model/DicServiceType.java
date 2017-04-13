package com.yst.web.model;

import java.io.Serializable;
/**
 * 
 * DicServiceType.java
 * @Description: 医生服务字典信息
 * @author 朱军
 * @copyright 2015-2016
 * @create-time 2016年11月24日 下午4:17:04
 *
 */
public class DicServiceType implements Serializable{

	private static final long serialVersionUID = -3359510753459467692L;

	private Integer service_type_id;
	private String name;
	
	private String str_service_type_name;
	
	
	
	public String getStr_service_type_name() {
		return str_service_type_name;
	}
	public void setStr_service_type_name(String str_service_type_name) {
		this.str_service_type_name = str_service_type_name;
	}
	public Integer getService_type_id() {
		return service_type_id;
	}
	public void setService_type_id(Integer service_type_id) {
		this.service_type_id = service_type_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

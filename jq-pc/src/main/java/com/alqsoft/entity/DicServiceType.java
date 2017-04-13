package com.alqsoft.entity;

import org.alqframework.orm.hibernate.IdEntity;

/**
 * 字典:服务类型
 * @Description: TODO
 * @author Shen.joe
 * @e-mail sudiluo_java@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月9日 下午6:03:04
 * 
 */
public class DicServiceType extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String departmentName;//科室名称

	private String serviceName;//服务名称

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}

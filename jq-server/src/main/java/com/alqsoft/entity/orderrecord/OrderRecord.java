/**
 * Project Name:Yst_web_ssh
 * File Name:OrderRecord.java
 * Package Name:com.yst.web.entity.orderrecord
 * Date:2016年3月16日下午11:16:28
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.alqsoft.entity.orderrecord;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ForeignKey;

import com.alqsoft.entity.order.PatientOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ClassName:OrderRecord <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月16日 下午11:16:28 <br/>
 * @author   张灿
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Entity
@Table(name = "order_record")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class OrderRecord extends IdEntity{
	
	private PatientOrder patientOrder;//订单表
	
	private String describer;//描述
	
	private String name;//操作者名称
	
	private Integer type;//0未支付 1已签约 2已支付 3治疗中 4完成；

	@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_order_id")
	@ForeignKey(name="")
	public PatientOrder getPatientOrder() {
		return patientOrder;
	}

	public void setPatientOrder(PatientOrder patientOrder) {
		this.patientOrder = patientOrder;
	}

	public String getDescriber() {
		return describer;
	}

	public void setDescriber(String describer) {
		this.describer = describer;
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
	
}


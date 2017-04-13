/**
 * Project Name:xyt_ydg
 * File Name:PlatformInfo.java
 * Package Name:com.ydg.web.entity.platforminfo
 * Date:2015年12月28日下午5:08:47
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.alqsoft.entity.platforminfo;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**平台信息表
 * ClassName:PlatformInfo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年12月28日 下午5:08:47 <br/>
 * @author   张灿
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Entity
@Table(name = "platform_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PlatformInfo extends IdEntity{
	
	private Integer fee;//平台结算总金额，记录我们平台需要结算的总金额
	
	private Integer totalAmountFenRun;//分润总金额

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Integer getTotalAmountFenRun() {
		return totalAmountFenRun;
	}

	public void setTotalAmountFenRun(Integer totalAmountFenRun) {
		this.totalAmountFenRun = totalAmountFenRun;
	}
	
	
}


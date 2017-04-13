/**
 * Project Name:Yst_web_ssh
 * File Name:SatisfactionPraiseTread.java
 * Package Name:com.yst.web.entity.satisfactionpraisetread
 * Date:2016年1月6日上午12:07:34
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.alqsoft.entity.satisfactionpraisetread;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ClassName:SatisfactionPraiseTread <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年1月6日 上午12:07:34 <br/>
 * @author   张灿
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Entity
@Table(name = "satisfaction_praise_tread")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class SatisfactionPraiseTread extends IdEntity{
	
	private String name;//赞美的内容
	
	private Integer isDel;//0 未删除 1已删除
	
	private Integer type;//赞和踩区分 0赞 1踩

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}


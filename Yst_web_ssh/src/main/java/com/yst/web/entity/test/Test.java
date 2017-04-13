/**
 * Project Name:Yst_web_ssh
 * File Name:Test.java
 * Package Name:com.yst.web.entity.test
 * Date:2016年1月5日上午9:54:20
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.yst.web.entity.test;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ClassName:Test <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年1月5日 上午9:54:20 <br/>
 * @author   张灿
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Entity
@Table(name = "test")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class Test extends IdEntity{
	
	private String des;//描述字段

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
}


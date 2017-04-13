package com.alqsoft.entity.satisfactionpraisetread;


import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 上午11:02:26
 * 
 */
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

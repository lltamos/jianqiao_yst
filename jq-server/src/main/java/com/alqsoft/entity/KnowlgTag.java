package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 *知识库标签表
 */
@Entity
@Table(name = "Knowlg_tag")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class KnowlgTag extends IdEntity implements Serializable{

	private static final long serialVersionUID = 2101954523334796655L;
	
	private String name;//标签名称
	private Integer hot;//用户的实用频率，用于排序，默认为1 ，越大表明越受欢迎
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	
}

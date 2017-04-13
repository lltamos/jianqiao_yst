package com.yst.web.model;

import java.io.Serializable;
/**
 *知识库标签表
 */
public class KnowlgTag implements Serializable{

	private static final long serialVersionUID = 2101954523334796655L;
	
	private Integer id;//主键
	private String name;//标签名称
	private Integer hot;//用户的实用频率，用于排序，默认为1 ，越大表明越受欢迎
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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

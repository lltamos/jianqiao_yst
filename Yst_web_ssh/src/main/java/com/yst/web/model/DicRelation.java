package com.yst.web.model;

import java.io.Serializable;
/**
 * 关系字典
 * @author admin
 *
 */
public class DicRelation implements Serializable{

	private static final long serialVersionUID = 6260006397327080597L;

	private Integer relation_id;
	private String name;

	public Integer getRelation_id() {
		return relation_id;
	}
	public void setRelation_id(Integer relation_id) {
		this.relation_id = relation_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

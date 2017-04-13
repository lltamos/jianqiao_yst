package com.yst.web.model;

import java.io.Serializable;
/**
 * 专长字典
 * @author admin
 *
 */
public class DicSpec implements Serializable{

	private static final long serialVersionUID = 6561606244810098395L;
	
	
	private Integer spec_id;
	private Integer parent_spec_id;
	private String name;
	
	public Integer getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(Integer spec_id) {
		this.spec_id = spec_id;
	}
	public Integer getParent_spec_id() {
		return parent_spec_id;
	}
	public void setParent_spec_id(Integer parent_spec_id) {
		this.parent_spec_id = parent_spec_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

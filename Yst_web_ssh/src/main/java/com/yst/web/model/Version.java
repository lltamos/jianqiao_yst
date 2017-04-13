package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;

public class Version implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer version_id;
	private String platform;
	private String app_name;
	private String version;
	private String url;
	private String des;
	private Date update_time;
	private String image;
	
	
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public Integer getVersion_id() {
		return version_id;
	}
	public void setVersion_id(Integer version_id) {
		this.version_id = version_id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}

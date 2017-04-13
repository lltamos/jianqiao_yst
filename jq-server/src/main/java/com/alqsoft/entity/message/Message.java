package com.alqsoft.entity.message;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
    * @ClassName: Mssage  
    * @Description: 健桥资讯
    * @author 腾卉  
    * @date 2017年3月22日  
    *
 */
@Entity
@Table(name = "message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Message extends IdEntity{

	/**  
	* @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
	*/
	private static final long serialVersionUID = 1L;
	private String title;//标题名称
	private String des;//文字描述
	private String image;//图片
	private String address;//跳转地址
	private Integer status;//状态 1删除 0存在
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}

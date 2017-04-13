package com.alqsoft.entity.message;


import org.alqframework.orm.hibernate.IdEntity;
/**
 * 
    * @ClassName: Message  
    * @Description: 健桥资讯
    * @author 腾卉  
    * @date 2017年3月22日  
    *
 */
public class Message extends IdEntity{
	
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

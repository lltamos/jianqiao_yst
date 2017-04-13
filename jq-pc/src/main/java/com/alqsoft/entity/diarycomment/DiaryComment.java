package com.alqsoft.entity.diarycomment;


import org.alqframework.orm.hibernate.IdEntity;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.diary.Diary;


/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 上午10:32:09
 * 
 */
public class DiaryComment extends IdEntity{
	
	private String content;//评论内容；
	
	private Long customerId;//用户
	
	private String customerName;//用户姓名
	
	private String customerHandImg;//用户头像
	
	private Long diaryId;//日记
	
	private Integer anonymous;//
	
	public Integer getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Integer anonymous) {
		this.anonymous = anonymous;
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerHandImg() {
		return customerHandImg;
	}

	public void setCustomerHandImg(String customerHandImg) {
		this.customerHandImg = customerHandImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Long diaryId) {
		this.diaryId = diaryId;
	}

	
	
}

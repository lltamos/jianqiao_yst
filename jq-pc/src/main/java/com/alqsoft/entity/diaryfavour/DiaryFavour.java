package com.alqsoft.entity.diaryfavour;


import org.alqframework.orm.hibernate.IdEntity;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 上午11:00:24
 * 
 */
public class DiaryFavour extends IdEntity{
	
	private Long diaryId;//
	
	private Long customerId;//
	
	public Long getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Long diaryId) {
		this.diaryId = diaryId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	
}

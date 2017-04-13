
package com.yst.web.entity.diaryfavour;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Date:     2016年11月19日 上午10:32:20 <br/>
 * @author   zhangcan
 * @version  
 * @since    JDK 1.8
 * @see 	 笔记模块 用户点赞表
 */
@Entity
@Table(name = "diary_favour")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class DiaryFavour extends IdEntity{
	
	private Integer diaryId;//
	
	private Integer customerId;//

	public Integer getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Integer diaryId) {
		this.diaryId = diaryId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
}


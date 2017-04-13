/**
 * Project Name:Yst_web_ssh
 * File Name:Diary.java
 * Package Name:com.yst.web.entity.diarybookpraisetread
 * Date:2016年1月6日上午1:28:08
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.yst.web.entity.diarybookpraisetread;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yst.web.entity.diary.Diary;
import com.yst.web.entity.diarybook.DiaryBook;
import com.yst.web.entity.satisfactionpraisetread.SatisfactionPraiseTread;
import com.yst.web.model.Customer;

/**
 * ClassName:Diary <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年1月6日 上午1:28:08 <br/>
 * @author   张灿
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Entity
@Table(name = "diary_book_praise_tread")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class DiaryBookPraisetread extends IdEntity{
	
	private DiaryBook dairyBook;//日记本
	
	private SatisfactionPraiseTread satisfactionPraiseTread;//日记赞与踩
	
	private Customer customer;//用户
	
	@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dairy_book_id")
	@ForeignKey(name="")
	public DiaryBook getDairyBook() {
		return dairyBook;
	}

	public void setDairyBook(DiaryBook dairyBook) {
		this.dairyBook = dairyBook;
	}

	@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "satisfaction_praise_tread_id")
	@ForeignKey(name="")
	public SatisfactionPraiseTread getSatisfactionPraiseTread() {
		return satisfactionPraiseTread;
	}

	public void setSatisfactionPraiseTread(
			SatisfactionPraiseTread satisfactionPraiseTread) {
		this.satisfactionPraiseTread = satisfactionPraiseTread;
	}
	@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	@ForeignKey(name="")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}


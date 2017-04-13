package com.alqsoft.entity.diaryBookPraisetread;

import org.alqframework.orm.hibernate.IdEntity;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.diarybook.DiaryBook;
import com.alqsoft.entity.satisfactionpraisetread.SatisfactionPraiseTread;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 上午10:59:05
 * 
 */
public class DiaryBookPraisetread extends IdEntity{
	
	private DiaryBook dairyBook;//日记本
	
	private SatisfactionPraiseTread satisfactionPraiseTread;//日记赞与踩
	
	private Customer customer;//用户
	
	public DiaryBook getDairyBook() {
		return dairyBook;
	}

	public void setDairyBook(DiaryBook dairyBook) {
		this.dairyBook = dairyBook;
	}

	public SatisfactionPraiseTread getSatisfactionPraiseTread() {
		return satisfactionPraiseTread;
	}

	public void setSatisfactionPraiseTread(
			SatisfactionPraiseTread satisfactionPraiseTread) {
		this.satisfactionPraiseTread = satisfactionPraiseTread;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}

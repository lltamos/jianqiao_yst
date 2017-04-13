package com.yst.web.service.diary;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.yst.web.entity.diarybook.DiaryBook;

/**
 * 
 * @Description: TODO
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午6:02:26
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface DiaryBookService extends BaseService<DiaryBook>{

	/**
	 * 添加
	 * @param diaryBook
	 * @return
	 */
	Result saveDiaryBook(DiaryBook diaryBook,String satisfactionPraiseTreadId);
	/**
	 * 查询单个
	 * @param dairyBookId
	 * @return
	 */
	Result findDiaryBookByBookId(Integer dairyBookId);
	/**
	 * 查询用户日记本列表
	 * @param customer_id
	 * @return
	 */
	Result queryDiaryBookList(Integer customer_id,Integer page,Integer rows);
	/**
	 * 获取写分享页面信息
	 * @param product_id
	 * @return
	 */
	Result selectWriteDiaryBookPageInfo(Integer product_id);
	
}

package com.yst.web.service.diary;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.yst.web.entity.diary.Diary;

/**
 * 
 * @Description: TODO
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午6:00:23
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface DiaryService extends BaseService<Diary>{
	
	/**
	 * 日记列表
	 * @param page
	 * @param rows
	 * @param customerId
	 * @return
	 */
	public Result getDiaryList(Integer page,Integer rows,Integer customerId);

	/**
	 * 跳转更新日记页接口
	 * @param diaryBookId
	 * @return
	 */
	public Result getDiaryListByDiaryBookId(Integer diaryBookId,Integer page, Integer rows);
	
	/**
	 * 添加日记
	 * @param productId
	 * @param content
	 * @param imageId
	 * @return
	 */
	public Result saveDiary(Integer productId, String content, String imageId,Integer diaryBookId,Integer customerId);

	/**
	 * 所有日记
	 * @param diaryId
	 * @return
	 */
	public Result finaAllDiaryByDiaryBookId(Integer diaryId,Integer page, Integer rows);
	
	/**
	 * 根据类型id查询该类别下的所有日记
	 * @param productTypeId
	 * @param page
	 * @param rows
	 * @return
	 */
	public Result finaDiaryByProductTypeId(Integer productTypeId, Integer page, Integer rows);
}

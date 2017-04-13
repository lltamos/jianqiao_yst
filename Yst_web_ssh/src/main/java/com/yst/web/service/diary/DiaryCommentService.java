package com.yst.web.service.diary;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.yst.web.entity.dairycomment.DiaryComment;

/**
 * 
 * @Description: TODO
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午5:58:49
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface DiaryCommentService extends BaseService<DiaryComment>{

	/**
	 *  日记详情页
	 * @param page
	 * @param rows
	 * @param dairyId
	 * @return
	 */
	public Result getDiaryCommentParticulars(Integer dairyId,Integer customerId,Integer page,Integer rows);
	
	/**
	 * 添加评论
	 * @param dairyId
	 * @param content
	 * @param customerId
	 * @return
	 */
	public Result saveDiaryComment(Integer dairyId, String content,Integer customerId);
}

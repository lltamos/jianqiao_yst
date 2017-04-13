package com.yst.web.controller.mobile.view.diaryComment;
import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.service.diary.DiaryCommentService;
/**
 * 
 * @Description: TODO
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午5:22:41
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@RequestMapping("/ydmvc/mobile/view/diaryComment")
@Controller
public class DiaryCommentController {

	@Autowired
	private DiaryCommentService diaryCommentService;
	
	/**
	 * 添加评论
	 * @param dairyId
	 * @param content
	 * @param customerId
	 * @return
	 */
	@RequestMapping("saveDiaryComment.do")
	@ResponseBody
	public Result saveDiaryComment(
			@RequestParam(value="dairyId",required=false)Integer dairyId,
			@RequestParam(value="content",required=false)String content ,
			@RequestParam(value="customerId",required=false)Integer customerId){
		return diaryCommentService.saveDiaryComment(dairyId, content, customerId);
	}
	
	/**
	 * 评价详情
	 * @param dairyId
	 * @param customerId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("getDiaryCommentParticulars.do")
	@ResponseBody
	public Result getDiaryCommentParticulars(
			@RequestParam(value="dairyId",required=false)Integer dairyId,
			@RequestParam(value="customerId",required=false)Integer customerId,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="9")Integer rows){
		return diaryCommentService.getDiaryCommentParticulars(dairyId, customerId, page, rows);
	}
}

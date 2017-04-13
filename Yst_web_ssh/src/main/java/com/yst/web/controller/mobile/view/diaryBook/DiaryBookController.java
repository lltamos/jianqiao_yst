package com.yst.web.controller.mobile.view.diaryBook;

import javax.validation.Valid;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.entity.diarybook.DiaryBook;
import com.yst.web.service.diary.DiaryBookService;

/**
 * 
 * @Description: TODO
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午4:39:58
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@RequestMapping("/ydmvc/mobile/view/diaryBook")
@Controller
public class DiaryBookController {
	
	@Autowired
	private DiaryBookService diaryBookService;
	
	/**
	 * 跳转写分享页面接口
	 * @param product_id
	 * @return
	 */
	@RequestMapping("toWriteDiaryBookPage.do")
	@ResponseBody
	public Result toWriteDiaryBookPage(@RequestParam(value="product_id",required=false)Integer product_id){
		return diaryBookService.selectWriteDiaryBookPageInfo(product_id);
	}
	
	/**
	 * 跳转写分享页面接口
	 * @param dairyBookId
	 * @return
	 */
	@RequestMapping("toDiaryBookModifyPage.do")
	@ResponseBody
	public Result toDiaryBookModifyPage(@RequestParam(value="dairyBookId",required=false)Integer dairyBookId){
		return diaryBookService.findDiaryBookByBookId(dairyBookId);
	}
	
	/**
	 * 写分享接口
	 * @param diaryBook
	 * @param resust
	 * @return
	 */
	@RequestMapping("writeDiaryBook.do")
	@ResponseBody
	public Result writeDiaryBook(@Valid DiaryBook diaryBook,@RequestParam(value="satisfactionPraiseTreadId",required=false)String satisfactionPraiseTreadId,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return ResultUtils.returnError(bindingResult);
		}else{
			return diaryBookService.saveDiaryBook(diaryBook,satisfactionPraiseTreadId);
		}
	}
	
	
	/**
	 * 获取日记本列表接口
	 * @param customer_id
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("getDiaryBookList.do")
	@ResponseBody
	public Result diaryBookList(
			@RequestParam(value="customer_id",required=false)Integer customer_id,
			@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value="rows",required=false)Integer rows
			){
		return diaryBookService.queryDiaryBookList(customer_id, page, rows);
	}
	
}

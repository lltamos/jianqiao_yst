package com.yst.web.controller.mobile.view.diary;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.service.diary.DiaryService;

/**
 * 日记
 * @Description: TODO
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午4:42:08
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@RequestMapping("/ydmvc/mobile/view/diary")
@Controller
public class DiaryController {
	
	@Autowired
	private DiaryService diaryService;
	
	/**
	 * 日记列表接口（分享列表）
	 * @param customerId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("getDiaryList.do")
	@ResponseBody
	public Result getDiaryList(@RequestParam(value="customerId",required=false)Integer customerId,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="9")Integer rows
			){
		return diaryService.getDiaryList(page, rows,customerId);
	}
	
	/**
	 * 跳转到更新日记页接口
	 * @param DiaryBookId
	 * @return
	 */
	@RequestMapping("getDiaryListByDiaryBookId.do")
	@ResponseBody
	public Result getDiaryListByDiaryBookId(
			@RequestParam(value="diaryBookId",required=false)Integer diaryBookId,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="9")Integer rows
			){
		return diaryService.getDiaryListByDiaryBookId(diaryBookId,page,rows);
	}
	
	/**
	 * 添加日记
	 * @param productId
	 * @return
	 */
	@RequestMapping("addDiary.do")
	@ResponseBody
	public Result addDiary(
			@RequestParam(value="productId",required=false)Integer productId,
			@RequestParam(value="content",required=false)String content,
			@RequestParam(value="imageId",required=false)String imageId,
			@RequestParam(value="diaryBookId",required=false)Integer diaryBookId,
			@RequestParam(value="customerId",required=false)Integer customerId
			){
		return diaryService.saveDiary(productId,content,imageId,diaryBookId,customerId);
	}
	/**
	 * 添加日记
	 * @param productId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("addDiaryios.do")
	@ResponseBody
	public Result addDiaryios(
			@RequestParam(value="productId",required=false)Integer productId,
			@RequestParam(value="content",required=false)String content,
			@RequestParam(value="imageId",required=false)String imageId,
			@RequestParam(value="diaryBookId",required=false)Integer diaryBookId,
			@RequestParam(value="customerId",required=false)Integer customerId
			) throws UnsupportedEncodingException{
		content = URLDecoder.decode(content,"UTF-8");
		return diaryService.saveDiary(productId,content,imageId,diaryBookId,customerId);
	}
	
	/**
	 * 所有日记
	 * @param diaryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("finaAllDiaryByDiaryBookId.do")
	@ResponseBody
	public Result finaAllDiaryByDiaryBookId(@RequestParam(value="diaryId",required=false)Integer diaryId,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="9")Integer rows){
		return diaryService.finaAllDiaryByDiaryBookId(diaryId,page,rows);
	}
	
	/**
	 * 根据类型id查询该类型下的所有日记
	 * @param productTypeId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("finaDiaryByProductTypeId.do")
	@ResponseBody
	public Result finaDiaryByProductTypeId(@RequestParam(value="productTypeId",required=false)Integer productTypeId,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="9")Integer rows){
		return diaryService.finaDiaryByProductTypeId(productTypeId, page, rows);
	}
}

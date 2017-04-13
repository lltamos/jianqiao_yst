package com.alqsoft.controller.pc.after.diarycomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alqsoft.service.diarycomment.DiaryCommentService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 下午5:29:10
 * 
 */
@Controller
public class DiaryCommentController {
	
	@Autowired
	private DiaryCommentService diaryCommentService;
}

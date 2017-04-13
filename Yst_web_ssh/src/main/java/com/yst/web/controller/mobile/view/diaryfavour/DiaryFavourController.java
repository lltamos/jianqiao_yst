package com.yst.web.controller.mobile.view.diaryfavour;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.service.diaryfavour.DiaryFavourService;

@RequestMapping("/ydmvc/mobile/view/diaryFavour")
@Controller
public class DiaryFavourController {

	@Autowired
	private DiaryFavourService diaryFavourService;
	
	/**
	 * 日记详情-点赞
	 * @param diaryId
	 * @param customerId
	 * @return
	 */
	@RequestMapping("customerDiaryFavour.do")
	@ResponseBody
	public Result customerDiaryFavour(@RequestParam(value="diaryId",required=false)Integer diaryId,
			@RequestParam(value="customerId",required=false)Integer customerId){
		Result result = diaryFavourService.customerDiaryFavour(diaryId,customerId);
		return result;
	}
}

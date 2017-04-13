package com.yst.web.controller.mobile.view.homePage;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.model.AppResult;
import com.yst.web.service.DoctorService;
import com.yst.web.service.ProductTypeService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年6月18日 下午3:37:33
 * 
 */
@RequestMapping("/ydmvc/mobile/view/homePage")
@Controller
public class HomePage {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	//首页疑难杂症
	@RequestMapping("findProductTypeAll.do")
	@ResponseBody
	public Result findProductTypeAll(
			@RequestParam(value="page_no",defaultValue="0")Integer page_no,
			@RequestParam(value="page_size",defaultValue="5")Integer page_size){
		return productTypeService.findProductTypeAll(page_no-1,page_size);
	}
	
	//首页医生
	@RequestMapping("findDoctorAll.do")
	@ResponseBody
	public Result findDoctorAll(){
		return this.doctorService.findDoctorAll();
	}
}

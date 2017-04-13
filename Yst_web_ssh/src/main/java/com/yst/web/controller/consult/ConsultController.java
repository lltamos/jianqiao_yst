package com.yst.web.controller.consult;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.service.consult.ConsultService;
/**
 * 
 * ConsultController.java
 * @Description: 健桥- 健桥资讯
 * @author 朱军
 * @copyright 2015-2016
 * @create-time 2016年11月22日 下午5:13:40
 *
 */
@RequestMapping("ydmvc/consult")
@Controller
public class ConsultController {

	@Autowired
	private ConsultService consultService;
	
	/**
	 * 首页健桥资讯
	 * @return
	 */
	@RequestMapping("findConsultList.do")
	@ResponseBody
	public Result findConsultList(){
		Result result = consultService.findConsultList();
		return result;
	}
}

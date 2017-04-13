package com.yst.web.controller.mobile.view.dicrelation;

import java.util.List;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.model.DicRelation;
import com.yst.web.service.DicRelationService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年11月18日 上午10:48:35
 * 
 */
@RequestMapping("/ydmvc/mobile/view/dicRelation")
@Controller
public class DicRelationController {

	@Autowired
	public DicRelationService dicRelationService;
	
	
	@RequestMapping("getdicRelation.do")
	@ResponseBody
	public Result getdicRelation(){
		List<DicRelation> list = this.dicRelationService.queryList();
		return ResultUtils.returnSuccess("获取成功", list);
	}
}

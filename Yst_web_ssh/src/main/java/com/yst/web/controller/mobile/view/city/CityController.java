package com.yst.web.controller.mobile.view.city;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.service.city.CityService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年5月5日 上午10:42:36
 * 
 */
@RequestMapping("/ydmvc/mobile/view/city")
@Controller
public class CityController {

	@Autowired
	private CityService cityService;
	
	/**
	 * 查询省份列表
	 * @return
	 */
	@RequestMapping("getProvList.do")
	@ResponseBody
	public Result getProvList(){
		return cityService.getProv();
	}
	
	/**
	 * 根据pid查询某省下面的所有市
	 * @param pid
	 * @return
	 */
	@RequestMapping("getCityList.do")
	@ResponseBody
	public Result getCityList(@RequestParam(value="id",required=false)Integer id){
		Integer pid = id;
		return cityService.getCityByProvId(pid);
	}
	
	/**
	 * 判断城市与省份是否匹配
	 * @param id
	 * @param provId
	 * @return
	 */
	@RequestMapping("findCityWhetherOrNotProv.do")
	@ResponseBody
	public Result findCityWhetherOrNotProv(@RequestParam(value="id",required=false)Integer id,
			@RequestParam(value="provId",required=false)Integer provId){
				return cityService.findCityWhetherOrNotProv(provId,id);
	}
}

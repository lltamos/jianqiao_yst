package com.alqsoft.controller.pc.view.attachment;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alqsoft.service.banner.BannerService;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月29日 下午5:09:39
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */

@Controller
@RequestMapping("/pc/view/banner")
public class BannerController {
	@Resource
	private BannerService bannerService;

	// 获取首页大图
	@RequestMapping("fecthbanner")
	@ResponseBody
	public Result fecthBanner() {
		List<Map<String, Object>> banner = bannerService.fetchBanner(5);
		System.out.println(JSON.toJSONString(banner));
		return ResultUtils.returnSuccess("banner", banner);
	}
}

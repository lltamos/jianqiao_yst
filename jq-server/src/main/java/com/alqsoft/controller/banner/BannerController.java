package com.alqsoft.controller.banner;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.model.Permission;
import com.alqsoft.service.attachment.AttachmentService;
import com.alqsoft.utils.SystemRole;

/**
 * 
 * @Description: TODO
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月23日 下午3:19:19 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
	@Autowired
	AttachmentService attachmentService;

	@RequestMapping("to-bannerlist-page")
	@Permission(SystemRole.ADMIN)
	public String toBannerPage() {

		return "/banner/banner-list";
	}

	@RequestMapping(value = "get-bannerlist", method = RequestMethod.POST)
	@Permission(SystemRole.ADMIN)
	@ResponseBody
	public Map<String, List<Attachment>> getBannerList(@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length, HttpServletRequest request) {

		List<Attachment> attachmentsByIsBanner = attachmentService.getAttachmentsByIsBanner(start, length, request);

		Map<String, List<Attachment>> map = new HashMap<String, List<Attachment>>();

		map.put("data", attachmentsByIsBanner);
	
		return map;
	}

	@RequestMapping(value = "to-banneradd", method = RequestMethod.GET)
	@Permission(SystemRole.ADMIN)
	public String toBannerAdd(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		System.out.println(id);

		if (id != 0) {
			Attachment attachment = attachmentService.getOneById(id);
			
			model.addAttribute("model", attachment);
		}

		return "/banner/banner-add";

	}

	/**
	 * 
	 * @param id
	 *            附件id
	 * @param type
	 *            类型0 设置为banner属性true， 1 修改描述
	 * @return
	 */
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@Permission(SystemRole.ADMIN)
	@ResponseBody
	public Result modifyAttachment(@RequestParam(value = "id", required = true) Long id, @RequestParam("type") int type,
			@RequestParam(value = "descs", defaultValue = "") String descs,
			@RequestParam(value = "address", defaultValue = "") String address) {

	
		Attachment attachment = attachmentService.get(id);
		if (type == 0) {
			attachment.setIsbanner(true);
			attachment.setDescs(descs);
			if (StringUtils.isNotBlank(address)) {
				
				attachment.setAddress(address);
				attachment.setUpdateTime(new Date());
			}

		}

		if (attachmentService.saveAndModify(attachment) != null) {
			return ResultUtils.returnSuccess("修改成功！");
		} else {
			return ResultUtils.returnError("修改失败！");
		}

	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	@Permission(SystemRole.ADMIN)

	public String deleteBanner(@RequestParam(value = "id", required = true) Long id) {

		if (id != null) {
			attachmentService.delete(id);
		}

		return "/banner/banner-list";

	}

}

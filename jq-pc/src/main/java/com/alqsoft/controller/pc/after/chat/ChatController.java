package com.alqsoft.controller.pc.after.chat;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alqsoft.entity.ImLog;
import com.alqsoft.service.imlog.ImlogCusService;
import com.alqsoft.service.imlog.ImlogService;
import com.alqsoft.utils.CustomerUtils;

/**
 * 
 * @Description: TODO
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月19日 下午5:00:45 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Controller
@RequestMapping("/pc/after/chat")
public class ChatController {

	@Resource
	private ImlogCusService imlogCusService;

	// 跳转聊天记录页面
	@RequestMapping(value = "to-chatrecord-page", method = RequestMethod.GET)
	public String pageToChatRecord(

			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "2") Integer rows, Model model, HttpSession session) {
		model.addAttribute("modeltype", 1);

		Long id = CustomerUtils.getCustomerId(session);

		if (id == null) {
			return "redirect:/pc/view/customer/to-login";
		}
		
	  

		List<Map<String, Object>> findAllByCustomerId = imlogCusService.findAllByCustomerId(id, page, rows);

		Integer totalPage = 0;
		totalPage = imlogCusService.getCountByCustomerId(id);
		double totalPage2 = Math.ceil(totalPage / rows.doubleValue());// 向上取整
		Integer totalPage1 = (new Double(totalPage2)).intValue();
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage1);
		model.addAttribute("list", findAllByCustomerId);
		System.out.println(findAllByCustomerId);
		return "after/usercenter/chatrecord";
	}

}

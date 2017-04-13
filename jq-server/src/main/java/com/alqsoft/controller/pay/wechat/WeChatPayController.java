package com.alqsoft.controller.pay.wechat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alqsoft.service.wechatpay.WeChatPayReturnService;

/**
 * 微信支付回调控制层
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月13日 下午1:41:56
 * 
 */
@Controller
@RequestMapping("pay/wechat/wechatpay")
public class WeChatPayController {
	
	@Autowired
	private WeChatPayReturnService weChatPayService;
	
	/**
	 * 
	 * 微信支付回调
	 */
	@RequestMapping("notity")
	public void weChatCodePayNotity(HttpServletRequest request, HttpServletResponse response) {
	    
		System.out.println("===开始执行回调!==============================================================");
		weChatPayService.weChatCodePayNotity(request, response);
		System.out.println("============回调结束!!!=======================!!!!!!!!!!!!!!!!!!");
	}
	
}

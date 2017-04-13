package com.alqsoft.rpc.pay.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.rpc.pay.RpcWeChatPayService;
import com.alqsoft.service.wechatpay.WeChatPayService;

/**
 * 微信支付接口
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月12日 下午6:29:53
 * 
 */
@Service
@Transactional(readOnly=true)
@Component
public class RpcWeChatPayServiceImpl implements RpcWeChatPayService{
	private static Log logger = LogFactory.getLog(RpcWeChatPayServiceImpl.class);

	@Autowired
	private WeChatPayService weChatPayService;
	@Override
	public String sendCodeUnifiedOrder(String orderId, String describe, String totalFee,
			String tradeType, String ip, int formOrder) {
		String url = weChatPayService.sendCodeUnifiedOrder(orderId, describe, totalFee, tradeType, ip, formOrder);
		return url;
	}

	@Override
	public void weChatCodePayNotity(HttpServletRequest request, HttpServletResponse response,
			Map<String, String> parms) {
		weChatPayService.weChatCodePayNotity(request, response, parms);
		
	}

}

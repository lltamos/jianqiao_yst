package com.alqsoft.service.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信支付回调服务层
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月13日 下午1:50:07
 * 
 */
public interface WeChatPayService {
    
	/**
	 * 微信支付回调方法
	 * @author Yaowei
	 * @param  
	 * @return void
	 * @Time 2017年3月13日
	 */
	public void weChatCodePayNotity(HttpServletRequest request, HttpServletResponse response);
}

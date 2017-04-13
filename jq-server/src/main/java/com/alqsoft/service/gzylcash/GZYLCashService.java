package com.alqsoft.service.gzylcash;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;

/**
 * 提现回调参数解密验证
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年2月15日 下午5:01:05
 * 
 */
public interface GZYLCashService {

	public Result validation(HttpServletRequest request, String encReq);

}

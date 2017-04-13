package com.yst.web.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;

import com.yst.web.model.Alipay;
import com.yst.web.model.AppResult;

public interface AlipayService {

	void alipayNotify(Alipay alipay, HttpServletRequest request,
			HttpServletResponse response, Map session) throws Exception;

	void alipayReturn(Alipay alipay, HttpServletRequest request,
			HttpServletResponse response, Map session) throws Exception;

	public AppResult addAlipayNO(AppResult appResult, String out_trade_no,
			String body, Integer total_fee, Integer alipay_act,
			HttpServletRequest request);
	/**
	 * 申请退款回调
	 * @param alipay
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void alipayRefundNotify(Alipay alipay, HttpServletRequest request, HttpServletResponse response) throws Exception;
	/**
	 * 申请退款
	 * @param order_num
	 * @param money
	 * @return
	 */
	public Result addRefundNo(String order_num,Double money);
}

package com.yst.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;

import com.yst.web.model.AppResult;
import com.yst.web.model.ExpressOrderInfo;
import com.yst.web.model.ProductOrder;
import com.yst.web.model.User;
import com.yst.web.utils.BootStrapResult;

public interface ProductOrderService {
	/**
	 * 退款
	 * @param po
	 * @param 期数
	 * @return
	 */
	public Result getRefundProductOrder(ProductOrder po,Integer num,User user);
	/**
	 * 确认付款
	 * @param po
	 * @param num
	 * @return
	 */
	public Result getConfirmProductOrder(ProductOrder po,Integer num,User user);
	/**
	 * 退款回调
	 * @param result_details 格式：交易号^退款金额^处理结果
	 */
	public void alipayRefundNotify(String result_details);
	/**
	 * 获取需要分润订单
	 * @return
	 */
	public Result getProdecOrderFeeStatus();
	public ProductOrder findById(int id);
	public List<ProductOrder> selectAll();
	public void add(ProductOrder productOrder);
	public void deleteById(int id);
	public void update(ProductOrder productOrder);
	public AppResult addOrder(ProductOrder productOrder,HttpServletRequest request);
	public AppResult getMerchantList(ProductOrder productOrder);
	public AppResult getInfo(ProductOrder productOrder);
	public void alipayNotify(String out_trade_no,Date gmt_payment,Integer pay_type);
	public AppResult updatePay(ProductOrder productOrder);
	public List<ProductOrder> selectAllByPage();
	public AppResult updateSendGoods(ExpressOrderInfo express);
	public AppResult getExpressInfo(ExpressOrderInfo express);
	public Result updateProductOrderStatus(ProductOrder po,HttpServletRequest request);
	
	public BootStrapResult<List<Map<String, Object>>> findProductOrderList(HttpServletRequest request);
	/**
	 * 推广人分润
	 * @param searchParams
	 * @param start
	 * @param pageSize
	 * @param request
	 * @return
	 */
	public BootStrapResult<List<ProductOrder>> findPushShareMoneyPage(Map<String, Object> searchParams, Integer start, Integer pageSize,HttpServletRequest request);
}

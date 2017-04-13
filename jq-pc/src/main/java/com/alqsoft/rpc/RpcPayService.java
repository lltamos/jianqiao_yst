package com.alqsoft.rpc;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import org.alqframework.result.Result;

import com.alqsoft.entity.WeCatPay;

/**
 * 支付,代付
 * @author Shen.joe
 */
public interface RpcPayService {
	
	/**
	 * 代付回调方法
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public Result verifySingNotify(String out_trade_no,String trade_status);

	
	/**
	 * 同步提现订单
	 * @param order_num
	 * @return
	 */
	public Map<String,String> syncCash(String order_num);
	

	/**
	 * 存储异步返回报文信息
	 * @author Yaowei
	 * @param  
	 * @return void
	 * @Time 2017年3月8日
	 */
	public void savePayTradeMsgInfo(String xml,String tradeNber);
	
	
	/**
	 * 调取微信同意下单接口,生成code_url
	 * @author Yaowei
	 * @param  
	 * @return Result
	 * @Time 2017年3月12日        orderId: 订单号   describe:商品描述   totalFee:交易金额,单位为分   tradeType:NATIVE  ip: 请求来源ip   formOrder:订单种类  1 在线咨询  2 服务包定金
	 */
	public Result sendCodeUnifiedOrder(String orderId,String describe,String totalFee,String tradeType,String ip,int formOrder);
	/**
	 * 微信支付回调接口
	 * @author Yaowei
	 * @param  
	 * @return void
	 * @Time 2017年3月12日
	 */
	public String weChatCodePayNotity(SortedMap<Object, Object> packageParams);
	
	/**
	 * 微信充值接口,根据交易号获取支付记录
	 * @author Yaowei
	 * @param  
	 * @return void
	 * @Time 2017年3月12日
	 */
	public WeCatPay getWeChatpayByTradeNo(String tradeNo);
	
	/**
	 * 微信充值接口,保存交易记录
	 * @author Yaowei
	 * @param  
	 * @return WeCatPay
	 * @Time 2017年3月12日
	 */
	public void saveAndModifyWeCatPay(WeCatPay weCatPay);
}

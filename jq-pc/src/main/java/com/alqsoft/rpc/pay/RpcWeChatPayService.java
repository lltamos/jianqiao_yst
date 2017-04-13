package com.alqsoft.rpc.pay;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;

import com.alqsoft.entity.WeCatPay;


/**
 * 
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月12日 下午6:29:09
 * 
 */
public interface RpcWeChatPayService {

	/**
	 * 调取微信同意下单接口,生成code_url
	 * @author Yaowei
	 * @param  
	 * @return Result
	 * @Time 2017年3月12日    orderId: 订单号   describe:商品描述   totalFee:交易金额,单位为分   tradeType:NATIVE  ip: 请求来源ip   formOrder:订单种类  1 在线咨询  2 服务包定金
	 */
	public String sendCodeUnifiedOrder(String orderId,String describe,String totalFee,String tradeType,String ip,int formOrder);
	/**
	 * 微信支付回调接口
	 * @author Yaowei
	 * @param  
	 * @return void
	 * @Time 2017年3月12日
	 */
	public void weChatCodePayNotity(HttpServletRequest request, HttpServletResponse response);
	
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
	public void saveAndModify(WeCatPay weCatPay);
}

package com.alqsoft.rpc.pay;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;

import com.alibaba.fastjson.JSONObject;
import com.alqsoft.entity.cashreceivestation.CashReceiveStation;
import com.alqsoft.entity.thirdpay.ThirdPay;
import com.alqsoft.entity.wecatpay.WeCatPay;
import com.alqsoft.model.JdpayModel;
import com.alqsoft.model.YLpayModel;
/**
 * 支付,代付
 * @author Shen.joe
 */
public interface RpcPayService {
	/**
	 * 对代付请求进行处理
	 * 在数据库中生成，cash_ora, cash_receive_station 记录
	 * @param crs
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public Result cashService(CashReceiveStation crs,Map<String,String> requestMap);
	/**
	 * 代付回调方法
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public Result verifySingNotify(String out_trade_no,String trade_status);
	/**
	 * 初始化充值参数
	 * @param jdpayModel
	 * @param request
	 * @return
	 */
	public JdpayModel initJDpayParamsDirect(JdpayModel jdpayModel, Map<String,String> requestMap);
	/**
	 * 充值回调方法
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public JdpayModel getReturnMsgDirect(HttpServletRequest request, HttpServletResponse response) throws IOException;

	/**
	 * 易联充值初始化参数
	 * @param lpayModel
	 * @param requestMap
	 * @return
	 */
	public Map<String, String> initYLpayParamsDirect(YLpayModel lpayModel, Map<String,String> requestMap);
	
	/**
	 * 本地报单保存thirdpay
	 * @param code
	 * @param merchentorderno
	 */
	public void saveBDBDThirdpay(String code, String merchentorderno);
	
	/**
	 * 同步充值订单
	 * @param order_num
	 * @return
	 */
	public JSONObject syncThirdpay(String order_num);
	
	/**
	 * 同步提现订单
	 * @param order_num
	 * @return
	 */
	public Map<String,String> syncCash(String order_num);
	
	/**
	 * 保存thirdpay
	 */
	public void saveThirdpay(ThirdPay thirdPay);
	
	/**
	 * 保存CashReceiveStation
	 */
	public void saveCashReceiveStation(CashReceiveStation cashReceiveStation);
	
	/**
	 * 易联充值支付回调信息解密
	 * @param text
	 * @return
	 */
	public String getMiWeXml(String response_text);
	/**
	 * 存储异步返回报文信息
	 * @author Yaowei
	 * @param  
	 * @return void
	 * @Time 2017年3月8日
	 */
	public void savePayTradeMsgInfo(String xml,String tradeNber);
	
	public String getAllXml(String text);

	/**
	 * 微信确认支付后回调接口
	 * @author Yaowei
	 * @param  
	 * @return void
	 * @Time 2017年3月10日
	 */
	public String weChatCodePayNotity(SortedMap<Object,Object> parms);
	/**
	 * 调取微信同意下单接口,生成code_url  formOrder订单类型  1 在线咨询  2 服务包定金
	 * @author Yaowei
	 * @param  
	 * @return Result
	 * @Time 2017年3月12日
	 */
	public Result sendCodeUnifiedOrder(String orderId,String describe,String totalFee,String tradeType,String ip,int formOrder);
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

	/**
	 * 验证贵州银联提现回调
	 * @param retSign 签名
	 * @param respJson 需要验证的数据
	 * @return
	 */
	public Result verifyGZYLCashCall(String retSign,JSONObject respJson);
	/**
	 * 保存易联充值回调报文
	 * @param retSign 签名
	 * @param respJson 需要验证的数据
	 * @return
	 */
	public Result saveYLPayToPayContext(String xml);
}

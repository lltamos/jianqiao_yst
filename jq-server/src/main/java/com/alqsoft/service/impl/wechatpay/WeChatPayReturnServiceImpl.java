package com.alqsoft.service.impl.wechatpay;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alqsoft.entity.DoctorServiceOrder;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.wecatpay.WeCatPay;
import com.alqsoft.rpc.RpcProductOrderService;
import com.alqsoft.rpc.pay.RpcPayService;
import com.alqsoft.service.order.FWBOderService;
import com.alqsoft.service.order.ZXZXOrderService;
import com.alqsoft.service.wechatpay.WeChatPayReturnService;
import com.alqsoft.utils.UtilDate;
import com.alqsoft.utils.wechat.WeCatUtils;

/**
 * 
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月13日 下午1:52:11
 * 
 * 
 * 
 */
@Service
@Transactional(readOnly=true)
public class WeChatPayReturnServiceImpl implements WeChatPayReturnService{

	private static Logger logger = LoggerFactory.getLogger(WeChatPayReturnServiceImpl.class);

	
	@Autowired
	private RpcPayService rpcPayService;
	
	@Autowired
	private RpcProductOrderService RpcProductOrderService;
	
	@Autowired
	private ZXZXOrderService zXZXOrderService;
	@Autowired
	private FWBOderService fWBOderService;
	
	/**
	 * 
	 * 微信支付回调
	 */
	@Transactional(readOnly=false)
	@SuppressWarnings("unchecked")
	public void weChatCodePayNotity(HttpServletRequest request, HttpServletResponse response) {

		InputStream inputStream=null;
		BufferedReader in = null;
		 try {	
		inputStream =request.getInputStream();//获取回调的参数
			if(inputStream==null||"".equals(inputStream)){
				logger.error("获取微信回调参数失败为空");
				return;
			}
			in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			logger.info("微信回调获取的原始参数，未处理过"+in);
			  StringBuffer sb = new StringBuffer();
		        String s;
		        while ((s = in.readLine()) != null){
		            sb.append(s);  
		        }
		      //解析xml成map  
		             
		        //String ss="<xml><appid><![CDATA[wx818bb37d056e42de]]></appid><bank_type><![CDATA[CFT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[N]]></is_subscribe><mch_id><![CDATA[1253259001]]></mch_id><nonce_str><![CDATA[R6vWwKDYratiuFwOoCubVi1Rr8QvalAJ]]></nonce_str><openid><![CDATA[ozHztvz3xzqTZ3cs4qr9oIFjXEro]]></openid><out_trade_no><![CDATA[201703241425234105]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[BAEA380F4BF631DE54747016E8A8274F]]></sign><time_end><![CDATA[20170324142550]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[NATIVE]]></trade_type><transaction_id><![CDATA[4007662001201703244470230436]]></transaction_id></xml>";
					Map<String, String> m = new HashMap<String, String>();
					System.out.println("转换前"+sb);
					logger.info("微信回调获取的原始参数，未处理过"+sb);
					m = WeCatUtils.doXMLParse(sb.toString());
					//m = WeCatUtils.doXMLParse(ss);
					
					logger.info("微信回调获取的xml参数"+m);
					//过滤空 设置 TreeMap  
					SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();      
					Iterator it = m.keySet().iterator();  
					while (it.hasNext()) {  
					    String parameter = (String) it.next();  
					    String parameterValue = m.get(parameter);  
					      
					    String v = "";  
					    if(null != parameterValue) {  
					        v = parameterValue.trim();   
					    }  
					    packageParams.put(parameter, v);  
					}
					//判断签名是否正确  
					String resXml ="";  
					String err_code=(String)packageParams.get("err_code");		//错误代码
					String err_code_des=(String)packageParams.get("err_code_des");//err_code_des
			        String out_trade_no=(String)packageParams.get("out_trade_no");//订单号
			        //logger.info("微信回调参数:错误代码:"+err_code+"错误描述:"+err_code_des+"订单号:"+out_trade_no);
					try {
						//TOGO
						logger.info("调取pay工程验证参数开始..........");
						String resul = rpcPayService.weChatCodePayNotity(packageParams);
						logger.info("调取pay工程验证参数开始.........."+resul);
						logger.info("调取pay工程查询订单信息开始..........");
						WeCatPay weChatpayByTradeNo = rpcPayService.getWeChatpayByTradeNo(out_trade_no);
						logger.info("调取pay工程查询订单信息开始.........."+weChatpayByTradeNo);
						int goods_tag = 0;
						if(weChatpayByTradeNo!=null){
							 goods_tag = weChatpayByTradeNo.getGoods_tag();
						}
						if(resul!=null){
							if ("SUCCESS".equals(resul)) {
								
								logger.info("订单类型:"+goods_tag+"订单号:"+out_trade_no+"支付成功");
								resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
										+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> "; 
								try {
									Long id = null;
									if(weChatpayByTradeNo!=null&&goods_tag==1){
										DoctorServiceOrder doctorServiceOrderByorderNo = zXZXOrderService.getDoctorServiceOrderByorderNo(out_trade_no);
										if(doctorServiceOrderByorderNo!=null){
											 doctorServiceOrderByorderNo.setPayStatus(1); //已支付
											 doctorServiceOrderByorderNo.setPay_time(new Date());//支付时间
											 Date afterweekOfDate;
											try {
												afterweekOfDate = UtilDate.getAfterweekOfDate();
												doctorServiceOrderByorderNo.setPay_timeout(afterweekOfDate);//失效时间
											} catch (Exception e) {
												logger.info("JQServece时间工具类转换出错");
												e.printStackTrace();
											}
											 DoctorServiceOrder modify = zXZXOrderService.saveAndModify(doctorServiceOrderByorderNo);
											 if (modify!=null) {
													logger.info("服务包微信支付回调[修改]订单状态完成,交易订单号:"+out_trade_no+"订单状态:"+modify.getPayStatus());
											}else {
												logger.info("服务包微信支付回调[修改]订单状态失败,交易订单号:"+out_trade_no+"订单类型:"+goods_tag);
											}
										}else {
											logger.info("服务包微信支付回调[查询]订单状态失败,交易订单号:"+out_trade_no+"订单类型:"+goods_tag);
										}
									}
									if(weChatpayByTradeNo!=null&&goods_tag==2){
										 ProductOrder productOrder = fWBOderService.getDoctorServiceOrderByorderNo(out_trade_no);
										if(productOrder!=null){
											productOrder.setPayStatus(1); //已支付
											//productOrder.gei
											ProductOrder saveAndModify = fWBOderService.saveAndModify(productOrder);
											if (saveAndModify!=null) {
												logger.info("服务包微信支付回调[修改]订单状态完成,交易订单号:"+out_trade_no+"订单状态:"+saveAndModify.getPayStatus());
											}else {
												logger.info("服务包微信支付回调[修改]订单状态失败,交易订单号:"+out_trade_no);
											}
										}else {
											logger.info("服务包微信支付回调[查询]订单状态失败,交易订单号:"+out_trade_no+"订单类型:"+goods_tag);
										}
									}
								} catch (Exception e) {
									logger.error("包微信支付修改订单状态异常   交易订单号:"+out_trade_no);
									e.printStackTrace();
								}
							}
							if ("FAIL".equals(resul)) {
								logger.info("订单类型:"+goods_tag+"支付失败,错误信息：" +"---err_code"+err_code +"--err_code_des"+err_code_des);  
								resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
										+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
							}
						}
						//支付成功result_code为SUCCESS
						//通知微信后台已经收到通知结果	
					} catch (Exception e) {
						e.printStackTrace();
					}
						BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
					    out.write(resXml.getBytes());  //给微信后台返回接收结果通知
					    out.flush();  
					    out.close();
				} catch (Exception e) {
					 logger.error("解析微信回调参数失败"+e.toString());
					e.printStackTrace();
				}  
		finally{
			try {
				if(in!=null){
					in.close();
				}
				if(inputStream!=null){
				 inputStream.close();  
				}
			} catch (IOException e) {
				logger.error("解析微信回调参数失败"+e.toString());
				e.printStackTrace();
			}  
		}
     }
}

package com.alqsoft.service.impl.wechat;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import com.alqsoft.entity.WeCatPay;
import com.alqsoft.rpc.RpcPayService;
import com.alqsoft.rpc.pay.RpcWeChatPayReturnService;
import com.alqsoft.service.wechat.WeChatPayService;
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
 */
public class WeChatPayServiceImpl implements WeChatPayService{

	private static Logger logger = LoggerFactory.getLogger(WeChatPayServiceImpl.class);

	
	@Autowired
	private RpcWeChatPayReturnService rpcWeChatPayReturn;
	
	
	/**
	 * 
	 * 微信支付回调
	 */
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
		       
		        String requeststr = sb.toString();
					
		        String resXml = null;
				try {
					resXml = rpcWeChatPayReturn.weChatCodePayNotity(requeststr);
					logger.info("对接第三方的报文:"+resXml);
				} catch (Exception e) {
					logger.error("微信回调 jq pc------server 失败");
					e.printStackTrace();
				}
						
				logger.info("response 返回"+resXml);
						BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
					    out.write(resXml.getBytes());  //给微信后台返回接收结果通知
					    out.flush();  
					    out.close();
				} catch (Exception e) {
					 logger.error("解析微信回调参数失败"+e.getMessage());
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
				// TODO Auto-generated catch block
				logger.error("解析微信回调参数失败"+e.getMessage());
				e.printStackTrace();
			}  
		}
     }
}

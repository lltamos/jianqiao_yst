package com.alqsoft.utils;


import org.alqframework.net.html.HttpClientUtils;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alqsoft.init.InitParamjm;

@Component
public class MobileCheck {

	private static Log logger = LogFactory.getLog(MobileCheck.class);
	@Autowired
	private InitParamjm initParam;
	
	public Result sendMsg(String phone, String codeType){
		Result result = new Result();
		try {
			String msg_url = this.initParam.getProperties().getProperty("send_msg_url");//发送短信路径
			String url = msg_url+"?phone="+phone+"&codeType="+codeType;
			String sendGet = HttpClientUtils.getHttpsToGet(url);
			JSONObject json = JSON.parseObject(sendGet);
			result.setCode(Integer.parseInt(json.get("code").toString()));
			result.setMsg(json.get("msg").toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("调用发送验证码接口异常"+e.getMessage());
			return ResultUtils.returnError("发送失败");
		}
	}
	
	public Result checkMsg(String phone, String code, String codeType) {
		Result result = new Result();
		try {
			String msg_url = this.initParam.getProperties().getProperty("check_msg_url");//发送短信路径
			String url = msg_url+"?phone="+phone+"&code="+code+"&codeType="+codeType;
			String sendGet = HttpClientUtils.getHttpsToGet(url);
			JSONObject json = JSON.parseObject(sendGet);
			result.setCode(Integer.parseInt(json.get("code").toString()));
			result.setMsg(json.get("msg").toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("调用验证验证码接口异常"+e.getMessage());
			return ResultUtils.returnError("调用验证验证码接口异常");
		}
	}
}

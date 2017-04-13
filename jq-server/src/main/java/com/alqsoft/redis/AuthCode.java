package com.alqsoft.redis;

import java.util.concurrent.TimeUnit;

import org.alqframework.net.html.HttpClientUtils;
import org.alqframework.result.Result;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alqsoft.init.InitParam;
import com.alqsoft.init.InitParams;

/**
 * @Title: AuthCode.java
 * @Description: 验证码保存及发送 
 * @author 陈振兵
 * @e-mail chenzhenbing@139.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年4月16日 下午10:20:46
 * Copyright © 2013 厦门卓讯信息技术有限公司 All rights reserved.
 * 
 */
@Component
public class AuthCode {
	
	private static Log logger = LogFactory.getLog(AuthCode.class);
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private InitParam initParam;
	@Autowired
	private InitParams initParams;
	
	private Result result = new Result();
	
	/**
	 * 验证码保存 类似格式 yzm_123@qq.com_Regist
	 * @Title: getRedis
	 * @Description: TODO
	 * @param: @param memberName  用户名 用来拼接
	 * @param: @param codeType  验证码类型 用来拼接
	 * @return: boolean
	 * @throws
	 */
	/**
	 * @param memberName
	 * @param codeType
	 * @return
	 */
	public Result getRedis(String memberName,String codeType){
		try
		{
			String code = RandomStringUtils.randomNumeric(6);//随机验证码
			
			StringBuffer key = new StringBuffer("yzm_").append(memberName).append(codeType);
			//向redis设置值
			redisTemplate.opsForValue().set(key.toString(), code);
			Long time = 6000L;//默认值
			time=Long.parseLong(this.initParam.getConstantMap().get("INFO_TIME_OUT"));
			
			//设置过期时间 
			redisTemplate.expire(key.toString(), time, TimeUnit.SECONDS);//单位：s
			
			String appkey = this.initParams.getProperties().getProperty("appkey");//appkey
			String msg_url = this.initParams.getProperties().getProperty("msg_url");//短信验证路径
			String content = this.getContent(codeType, code);
			
			String url = msg_url+"?mobile="+memberName+"&content="+content+"&appkey="+appkey;
			logger.info("手机号为"+memberName+"的用户准备调用第三方短信接口");
			return this.toSendMsg(url);
			
		}catch(Exception e)
		{
			logger.info("手机号为"+memberName+"的用户设置短信接口参数异常"+e.getMessage());
			result.setCode(0);
			result.setMsg("短信验证发送失败");
			return result;
		}
	}
	
	/**
	 * 获取短信验证模板
	 * @param codeType
	 * @param code
	 * @return
	 */
	private String getContent(String codeType, String code) {
		
		String content = "";
		
		if(codeType.equals("1")){//注册短信验证模板
			content = "您的验证码是："+code+"，欢迎注册普惠电商。【普惠电商】";
		}else if(codeType.equals("2")){//修改短信验证模板
			content = "您的验证码是："+code+"。【普惠电商】";
		}
		
		return content;
	}

	/**
	 * 验证码验证操作
	 * @Title: checkRedis
	 * @Description: TODO
	 * @param: @param memberName
	 * @param: @param codeType
	 * @param: @param code  传过来的验证码值
	 * @return: boolean
	 * @throws
	 */
	public boolean checkRedis(String memberName,String codeType,String code)
	{
		try
		{
			StringBuffer key = new StringBuffer("yzm_").append(memberName).append(codeType);
			String realCode = redisTemplate.opsForValue().get(key.toString());
			if(StringUtils.isNotBlank(realCode))
			{
				if(realCode.equals(code))
				{
					logger.info("phoneNum"+memberName+":pass validation");
					return true;
				}
			}
			logger.info("phoneNum"+memberName+":validation failed");
			return false;
		}catch(Exception e)
		{
			logger.info("phoneNum"+memberName+":validation exception"+e.getMessage());
			return false;
		}
	}
	
	/**
	 * 异步发送短信
	 * @param url
	 * @return
	 */
	@Async
	private Result toSendMsg(String url){
		
		String sendGet = HttpClientUtils.getHttpsToGet(url);
		
		JSONObject json = JSON.parseObject(sendGet);
		
		logger.info("第三方短信接口返回信息"+json);
		
		int status = Integer.parseInt(json.get("status").toString());
		
		if(status == 0){
			logger.info("短信验证发送成功");
			result.setCode(1);
			result.setMsg("发送成功");
		}else if(status == 210){
			logger.info(json.get("msg").toString());
			result.setCode(0);
			result.setMsg("操作次数过多，请稍后再试");
		}else if(status == 202){
			logger.info(json.get("msg").toString());
			result.setCode(0);
			result.setMsg(json.get("msg").toString());
		}else{
			logger.info("短信验证发送失败");
			result.setCode(0);
			result.setMsg("短信验证发送失败");	
		}
		return result;
	}
	
}

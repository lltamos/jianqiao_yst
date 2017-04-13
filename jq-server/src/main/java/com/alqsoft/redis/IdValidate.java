package com.alqsoft.redis;

import org.alqframework.net.html.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alqsoft.init.InitParams;

/**
 * @author cjd
 * @Description: 身份证实名认证 
 * 2016年10月13日 上午11:38:08
 *
 */
@Component
public class IdValidate {

	@Autowired
	private InitParams initParams;
	
	public boolean checkIdCard(String IdNum, String name){
		
		try {
			String appkey = initParams.getProperties().getProperty("appkey");//appkey
			String idcard_check_url = initParams.getProperties().getProperty("idcard_check_url");//身份证验证路径
			String url = idcard_check_url+"?idcard="+IdNum+"&realname="+name+"&appkey="+appkey;
			
			String sendGet = HttpClientUtils.getHttpsToGet(url);
			
			JSONObject json = JSON.parseObject(sendGet);
			int status = Integer.parseInt(json.get("status").toString());
			
			if(status == 0){
				JSONObject result = JSON.parseObject(json.get("result").toString());
				int verifystatus = Integer.parseInt(result.get("verifystatus").toString());
				if(verifystatus == 0){
					return true;
				}
				return false;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
}

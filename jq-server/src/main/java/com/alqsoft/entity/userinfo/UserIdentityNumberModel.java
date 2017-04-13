package com.alqsoft.entity.userinfo;

import java.util.Date;

/**
 * 身份信息pojo
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午5:16:36
 * 
 */
public class UserIdentityNumberModel {

	private String name; //姓名必填
	
	private String identityNB; //身份证号  必填
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityNB() {
		return identityNB;
	}

	public void setIdentityNB(String identityNB) {
		this.identityNB = identityNB;
	}

	
}

package com.alqsoft.service.userinfo;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.userinfo.UserBankCard;

/**
 * 用户银行卡业务层
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午4:06:28
 * 
 */
public interface UserBankCardService  extends BaseService<UserBankCard>{

	public UserBankCard findUserBankCardByUserId (long userId);
	
	public UserBankCard findUserBankCardByPhone(String phone);
	
}

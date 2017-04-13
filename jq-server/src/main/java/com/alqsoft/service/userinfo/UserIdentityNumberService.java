package com.alqsoft.service.userinfo;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.userinfo.UserBankCard;
import com.alqsoft.entity.userinfo.UserIdentityNumber;

/**
 * 用户身份信息业务层
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午4:05:42
 * 
 */
public interface UserIdentityNumberService extends BaseService<UserIdentityNumber>{
    
	/**
	 * 根据用户iD查询用户用户身份信息
	 * @author Yaowei
	 * @param  
	 * @return UserBankCard
	 * @Time 2017年3月15日
	 */
    public UserIdentityNumber findUserIdentityNumberByUserId (String userId);
    
    
	//public UserBankCard findUserBankCardByPhone(String phone);
}

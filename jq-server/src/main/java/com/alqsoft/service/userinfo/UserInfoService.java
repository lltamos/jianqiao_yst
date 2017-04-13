package com.alqsoft.service.userinfo;

import org.alqframework.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.userinfo.UserBankCardModel;
import com.alqsoft.entity.userinfo.UserIdentityNumberModel;

import redis.clients.jedis.JedisCluster.Reset;

/**
 * 用户信息验证
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午4:57:28
 * 
 */
@Service
@Transactional
public interface UserInfoService {
    /**
     * 判断是否身份认证
     * @author Yaowei
     * @param  
     * @return boolean
     * @Time 2017年3月15日
     */
	public boolean isIdentityAuthentication(String userid);
	/**
	 * 判断是否绑定银行卡
	 * @author Yaowei
	 * @param  
	 * @return boolean
	 * @Time 2017年3月15日
	 */
	public boolean isBankAuthentication(String userid);
	/**
	 * 绑定或修改银行卡
	 * @author Yaowei
	 * @param  
	 * @return boolean
	 * @Time 2017年3月15日
	 */
	public Result saveAndModifyBankInfo(String userids,UserBankCardModel bankModel );
	
	/**
	 * 进行身份认证
	 * @author Yaowei
	 * @param  
	 * @return boolean
	 * @Time 2017年3月15日
	 */
	//public Result saveIdentityInfo(String userid,UserIdentityNumberModel IdentityModel);
}

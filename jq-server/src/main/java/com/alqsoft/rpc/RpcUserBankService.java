package com.alqsoft.rpc;


import org.alqframework.result.Result;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.userinfo.UserBankCard;

/**
 * 用户银行业务层
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月18日 下午2:12:58
 * 
 */
public interface RpcUserBankService {
    /**
     * 绑定银行卡信息
     * @author Yaowei
     * @param  
     * @return String
     * @Time 2017年3月18日
     */
    public Result saveBankCard(Customer customer,UserBankCard card);
	
	/**
	 * 根据用户id查询银行卡信息
	 * @author Yaowei
	 * @param  
	 * @return Result
	 * @Time 2017年3月18日
	 */
	public Result findUserBankInfo(String userid);
}

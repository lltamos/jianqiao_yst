package com.alqsoft.service.order;

import org.alqframework.result.Result;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.order.UserBankCardModel;

/**
 * 用户银行绑卡业务层
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月18日 下午4:18:43
 * 
 */
public interface UserBankService {
    /**
     * 绑定银行信息
     * @author Yaowei
     * @param  
     * @return String
     * @Time 2017年3月18日
     */
	public Result saveBankInfo(Customer customer,UserBankCardModel userBank) throws Exception;
	/**
	 * 银行卡信息修改
	 * @author Yaowei
	 * @param  
	 * @return String\106
	 * @Time 2017年3月18日
	 */
	public Result updateBankInfo(Customer customer,UserBankCardModel userBank)throws Exception;
	

}

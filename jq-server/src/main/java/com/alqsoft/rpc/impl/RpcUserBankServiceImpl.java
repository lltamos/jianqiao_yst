 package com.alqsoft.rpc.impl;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.userinfo.UserBankCard;
import com.alqsoft.rpc.RpcUserBankService;
import com.alqsoft.service.userinfo.UserBankCardService;
import com.alqsoft.service.userinfo.UserIdentityNumberService;
import com.alqsoft.utils.wechat.BeanUtils;

/**
 * 用户银行卡实现类
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月18日 下午2:15:12
 * 
 */
@Service
@Transactional
public class RpcUserBankServiceImpl implements RpcUserBankService{
    
	@Autowired
	private UserBankCardService bankCard;
	
	@Override
	public Result saveBankCard(Customer customer, UserBankCard card) {
		Long userid = customer.getId();
		if ((userid==null||"".equals(userid))||BeanUtils.checkFieldValueNull(card)) {
			return ResultUtils.returnError("信息异常,请重新确认");
		}
		UserBankCard saveAndModify = bankCard.saveAndModify(card);
		if (BeanUtils.checkFieldValueNull(saveAndModify)) {
			return ResultUtils.returnError("银行卡绑定失败! userid:"+userid);
		}
		return ResultUtils.returnSuccess("银行卡绑定成功  userid:"+userid);
	}

	
	@Override
	public Result findUserBankInfo(String userid){
		if (userid==null||"".equals(userid)) {
			return ResultUtils.returnError("用户信息不能为空,请重新确认");
		}
		UserBankCard bank = bankCard.findUserBankCardByUserId(Long.parseLong(userid));
		if (BeanUtils.checkFieldValueNull(bank)) {
			return ResultUtils.returnError("没有对应的银行卡信息或用户id错误");
		}
		return ResultUtils.returnSuccess("SUCCESS", bank);
		
	}
	
	

}

package com.alqsoft.service.impl.userinfo;

import java.util.Date;
import java.util.Map;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.payandcash.TakeOut;
import com.alqsoft.entity.userinfo.UserBankCard;
import com.alqsoft.entity.userinfo.UserBankCardModel;
import com.alqsoft.entity.userinfo.UserIdentityNumber;
import com.alqsoft.entity.userinfo.UserIdentityNumberModel;
import com.alqsoft.rpc.RpcCashGZYLService;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.service.userinfo.UserBankCardService;
import com.alqsoft.service.userinfo.UserIdentityNumberService;
import com.alqsoft.service.userinfo.UserInfoService;
import com.alqsoft.utils.wechat.BeanUtils;

/**
 * 
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午5:24:41
 * 
 */
@Service
@Transactional
public class UserInfoServiceImpl  implements UserInfoService{
	private static Logger logger=LoggerFactory.getLogger(UserInfoServiceImpl.class);
	@Autowired
	private UserBankCardService userBankCardService;
	
	@Autowired
	private UserIdentityNumberService userIdentityNumberService;
	@Autowired
	private CustomerService customerService;
	
	@Autowired 
	private RpcCashGZYLService rpcCashGZYLService;
	@Override
	public boolean isIdentityAuthentication(String userid) {
		 UserIdentityNumber UserIdentity = userIdentityNumberService.findUserIdentityNumberByUserId(userid);
		if (UserIdentity!=null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isBankAuthentication(String userid) {
		UserBankCard bank = userBankCardService.findUserBankCardByUserId(Long.parseLong(userid));
		if (bank!=null) {
			return true;
		}
		return false;
	}

	@Override
	public Result saveAndModifyBankInfo(String userId, UserBankCardModel bankModel) {
		if ((userId==null|| "".equals(userId))|| (!BeanUtils.checkFieldValueNull(bankModel))){
			
			return ResultUtils.returnError("信息不完整,请求失败");
		}
		//Customer findOne = customerService.findOne(Long.parseLong(userId));
		/*if (!BeanUtils.checkFieldValueNull(findOne)) {
			return ResultUtils.returnError("非法的用户信息,无法绑定身份信息");
			
		}*/
		UserBankCard bankCard;
		 bankCard = userBankCardService.findUserBankCardByUserId(Long.parseLong(userId));
		if (bankCard==null) {
			 bankCard = new UserBankCard();
		}
		bankCard.setBankName(bankModel.getBankName());
		bankCard.setCardNum(bankModel.getCardNum());
		bankCard.setUserId(Long.parseLong(userId));
		bankCard.setCreatedTime(new Date());
		bankCard.setTelPhone(bankModel.getTelPhone());
		bankCard.setOwnName(bankModel.getCardNum());
		//findOne.setBankStatus(1);//状态修改为已绑定银行卡
		try {
			userBankCardService.saveAndModify(bankCard);
			logger.info("银行卡认证信息保存成功! 用户:"+userId);
			//customerService.saveAndModify(findOne);
			logger.info("银行卡认证信息状态修改成功! 用户:"+userId);
		} catch (Exception e) {
			logger.error("银行卡认证信息出错! 用户:"+userId);
			e.printStackTrace();
		}
		
		return ResultUtils.returnSuccess("SUCCESS");
	}


	public Result cashGZYL(TakeOut takeOut, Map<String, Object> requestMap){
		
		
		rpcCashGZYLService.cashGZYLService(takeOut, requestMap);
		return null;
	}

//	@Override
//	public Result saveIdentityInfo(String userid, UserIdentityNumberModel IdentityModel) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
}

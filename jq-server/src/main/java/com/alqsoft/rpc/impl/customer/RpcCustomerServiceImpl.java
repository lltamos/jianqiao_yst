package com.alqsoft.rpc.impl.customer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alqframework.pay.weixin.util.MD5Util;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.alqsoft.dao.balance.BalanceDao;
import com.alqsoft.dao.customer.CustomerDao;
import com.alqsoft.entity.Balance;
import com.alqsoft.entity.Customer;
import com.alqsoft.rpc.customer.RpcCustomerService;
import com.alqsoft.utils.CheckElement;
import com.alqsoft.utils.CommUtils;
import com.alqsoft.utils.MobileCheck;
@Service
@Transactional
public class RpcCustomerServiceImpl implements RpcCustomerService{

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private MobileCheck mobileCheck;
	@Autowired
	private BalanceDao balanceDao;
	
	@Override
	@Transactional(readOnly=false)
	public Result userRegister(Customer customer,String checkmsg) {
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("注册信息不能为空!");
		}
		String phone = customer.getPhone();
		String password = customer.getPassword();
		if(CommUtils.isNull(phone)){
			return ResultUtils.returnError("手机号不能为空!");
		}
		if(CommUtils.isNull(password)){
			return ResultUtils.returnError("密码不能为空!");
		}
		if(CommUtils.isNull(checkmsg)){
			return ResultUtils.returnError("短信验证码不能为空!");
		}
		
		//"^1[34578]\\d{9}$"; //^[0-9]*$
		String regExp ="^1[34578]\\d{9}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(phone);
		if(m.find() == false){
			return ResultUtils.returnError("请输入正确的手机号!");
		}
		Result checkResult = mobileCheck.checkMsg(phone, checkmsg, "JQ2017031602");
		if(checkResult.getCode() != 1){
			return ResultUtils.returnError(checkResult.getMsg());
		}
		Customer dbcustomer = customerDao.getCustomerByPhone(phone);
		if(!CommUtils.isNull(dbcustomer)){
			return ResultUtils.returnError("该账号已存在!");
		}
		customer.setPassword(MD5Util.MD5Encode(password, "utf-8"));
		customer.setType(0);
		customer.setDeleted(0);
		customer.setIsRecommender(0L);
		customer.setIsMerchant(0L);
		Customer save = customerDao.save(customer);
		if(null != save){
			/**
			 * 此处是在用户注册成功的时候生成其对应的三张Balance表
			 */
			Balance balance = new Balance();
			balance.setCustomerId(save.getId());
			balance.setType(1);
			balance.setBalanceRemain(0L);
			balance.setLastUpdateTime(new Date());
			balance.setFee(0L);
			balance.setDepositFee(0L);
			balance.setHaveWithdrawalFee(0L);
			balanceDao.save(balance);
			Balance balance1 = new Balance();
			balance1.setCustomerId(save.getId());
			balance1.setType(2);
			balance1.setBalanceRemain(0L);
			balance1.setLastUpdateTime(new Date());
			balance1.setFee(0L);
			balance1.setDepositFee(0L);
			balance1.setHaveWithdrawalFee(0L);
			balanceDao.save(balance1);
			Balance balance2 = new Balance();
			balance2.setCustomerId(save.getId());
			balance2.setType(3);
			balance2.setBalanceRemain(0L);
			balance2.setLastUpdateTime(new Date());
			balance2.setFee(0L);
			balance2.setDepositFee(0L);
			balance2.setHaveWithdrawalFee(0L);
			balanceDao.save(balance2);
			
		}
		return ResultUtils.returnSuccess("注册成功!");
	}

	@Override
	public Result forgetpassword(Customer customer, String checkmsg) {
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("密码不能为空!");
		}
		String phone = customer.getPhone();
		String password = customer.getPassword();
		String md5Encode = MD5Util.MD5Encode(password, "utf-8");
		Customer customerByPhone = customerDao.getCustomerByPhone(phone);
		if(CommUtils.isNull(customerByPhone)){
			return ResultUtils.returnError("账号不存在!");
		}
		Result checkResult = mobileCheck.checkMsg(phone, checkmsg, "JQ2017031603");
		if(checkResult.getCode() != 1){
			return ResultUtils.returnError(checkResult.getMsg());
		}
		if(customerByPhone.getPassword().equals(md5Encode)){
			return ResultUtils.returnError("新密码不能与原密码一致!");
		}
		customerByPhone.setPassword(md5Encode);
		customerDao.save(customerByPhone);
		return ResultUtils.returnSuccess("新密码修改成功!");
	}
}

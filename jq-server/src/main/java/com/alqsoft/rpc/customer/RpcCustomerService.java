package com.alqsoft.rpc.customer;

import org.alqframework.result.Result;

import com.alqsoft.entity.Customer;



public interface RpcCustomerService {

	/**
	 * 用户注册
	 * @param customer
	 * @return
	 */
	public Result userRegister(Customer customer,String checkmsg);
	
	/**
	 * 忘记密码
	 * @param customer
	 * @param checkmsg
	 * @return
	 */
	public Result forgetpassword(Customer customer, String checkmsg);
}

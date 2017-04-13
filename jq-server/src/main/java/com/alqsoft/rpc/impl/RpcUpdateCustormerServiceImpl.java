package com.alqsoft.rpc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.entity.Customer;
import com.alqsoft.rpc.RpcUpdateCustormerService;
import com.alqsoft.service.customer.CustomerService;

/**
 * 
 * @Description: 用户操作
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月16日 下午2:25:58 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */

@Service
public class RpcUpdateCustormerServiceImpl implements RpcUpdateCustormerService {

	@Autowired
	private CustomerService customerService;

	@Override
	public int updatePasswd(Long id, String passwd) {

		int updateCustomerPasswd = customerService.updateCustomerPasswd(id, passwd);

		return updateCustomerPasswd;
	}

	@Override
	public int updatePhone(Long id, String phone) {
		
		int updateCustomerPasswd = customerService.updateCustomerPhone(id, phone);
		
		return updateCustomerPasswd;
	}

	@Override
	public Customer updateCustorm(Customer customer) {
		Customer cu = customerService.saveAndModify(customer);
		return cu;
	}

	@Override
	public Customer updateLoginTime(Customer customer) {
		// TODO Auto-generated method stub
		return customerService.saveAndModify(customer);
	}
}
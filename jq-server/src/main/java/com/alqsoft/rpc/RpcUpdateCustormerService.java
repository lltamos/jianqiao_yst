package com.alqsoft.rpc;

import com.alqsoft.entity.Customer;

/**
 * 
 * @Description: 用户操作
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月16日 下午2:25:58
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface RpcUpdateCustormerService {
	
	/**
	 * 
	 * @param id
	 * @param passwd
	 * @return
	 */
	public int updatePasswd(Long id,String passwd);
	
	
	//修改手机号
	
	public int updatePhone(Long id,String phone);
	
	public Customer updateCustorm(Customer customer);
	Customer updateLoginTime(Customer customer);
}
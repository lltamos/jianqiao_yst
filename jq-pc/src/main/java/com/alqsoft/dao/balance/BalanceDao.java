package com.alqsoft.dao.balance;

import java.util.Map;

/**
 * 
 * @Description: 账户额度信息
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 下午2:46:01
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface BalanceDao {
	
	//通过用户id查询余额
	public Map<String,Object> getBalanceByCId(Integer cid);
}

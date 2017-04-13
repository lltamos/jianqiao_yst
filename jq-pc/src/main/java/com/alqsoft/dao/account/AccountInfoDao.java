package com.alqsoft.dao.account;


import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

/**
 * 
 * @Description: 用户账户信息dao
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月4日 下午3:41:45
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@MyBatisRepository
public interface AccountInfoDao {
	
	/**
	 * 查询余额
	 * @param userId 查询账户的id
	 * @return
	 */
	Map<String,String> findAccountBalanceByUser(Integer userId);
	
}

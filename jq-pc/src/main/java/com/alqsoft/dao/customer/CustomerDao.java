package com.alqsoft.dao.customer;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.Customer;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 下午3:21:59
 * 
 */
@MyBatisRepository
public interface CustomerDao {

	Customer getCustomerById(Long id);

	/**
	 * 根据手机号获取用户信息
	 * @param phone
	 * @return
	 */
	Customer getCustomerByPhone(String phone);

}

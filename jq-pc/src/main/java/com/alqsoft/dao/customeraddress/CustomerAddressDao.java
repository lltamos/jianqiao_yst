package com.alqsoft.dao.customeraddress;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.CustomerAddress;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午5:26:22
 * 
 */
@MyBatisRepository
public interface CustomerAddressDao {

	public CustomerAddress findByColumnValue(Long aid);

}

package com.alqsoft.dao.dicsrtvicetype;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.DicServiceType;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年4月5日 上午9:28:49
 * 
 */
@MyBatisRepository
public interface DicServiceTypeDao {

	DicServiceType getServiceTypeInfo(Long serviceTypeId);

}

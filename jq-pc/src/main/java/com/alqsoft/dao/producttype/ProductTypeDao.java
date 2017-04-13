package com.alqsoft.dao.producttype;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.ProductType;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 下午2:01:53
 * 
 */
@MyBatisRepository
public interface ProductTypeDao {

	public ProductType findProductTypeInfo(long producttypeid);

}

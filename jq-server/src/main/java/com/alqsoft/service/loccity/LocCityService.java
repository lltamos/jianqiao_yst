package com.alqsoft.service.loccity;

import java.util.List;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.LocCity;

/**
 * 市级业务
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午8:36:42
 * 
 */
public interface LocCityService extends BaseService<LocCity> {
	/**
	 * 查询省份下市级
	 * @param provId
	 * @return
	 */
	List<LocCity> getCityByProvId(Integer provId);
}

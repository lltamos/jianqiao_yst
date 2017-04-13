package com.yst.web.service.city;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.yst.web.entity.city.City;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年5月5日 上午9:50:32
 * 
 */
public interface CityService extends BaseService<City>{

	/**
	 * 查询省
	 * @return
	 */
	public Result getProv();
	
	/**
	 * 查询某省下的全部市
	 * @param pid
	 * @return
	 */
	public Result getCityByProvId(Integer provId);
	
	/**
	 * 判断当前省份与城市是否匹配
	 * @param provId
	 * @param id
	 * @return
	 */
	public Result findCityWhetherOrNotProv(Integer provId,Integer id);
}

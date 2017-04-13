package com.alqsoft.service.locprov;

import java.util.List;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.LocProv;

/**
 * 省份业务
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午8:36:42
 * 
 */
public interface LocProvService extends BaseService<LocProv> {
	/**
	 * 查询所有省份
	 * @return
	 */
	List<LocProv> findAll();
}

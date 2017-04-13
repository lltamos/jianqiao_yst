package com.alqsoft.dao.loccity;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.LocCity;

/**
 * 市级dao
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午8:23:19
 * 
 */
public interface LocCityDao extends BaseDao<LocCity> {
	@Query("FROM LocCity o WHERE o.provId=:provId")
	public List<LocCity> getCityByProvId(@Param("provId")Integer provId); 
}

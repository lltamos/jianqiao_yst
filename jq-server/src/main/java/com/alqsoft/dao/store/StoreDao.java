package com.alqsoft.dao.store;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.Store;

/**
 * 分院dao
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午8:23:19
 * 
 */
public interface StoreDao extends BaseDao<Store> {
	
	@Query(value="select * from store s where  s.merchant_id=:merchant_id and s.name=:name",nativeQuery=true)
	public Store storeCheck(@Param("merchant_id") Integer merchantId,@Param("name") String name);
}

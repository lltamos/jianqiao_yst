package com.alqsoft.dao.merchant;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.Merchant;

/**
 * 总院dao
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午8:23:19
 * 
 */
public interface MerchantDao extends BaseDao<Merchant> {
	@Query("FROM Merchant o WHERE o.deleted='0'")
	public List<Merchant> findAllMerchant(); 
	
	@Query("from Merchant o WHERE o.customerId=:customerId")
	public Merchant findMerchantByCustomer(@Param("customerId")Long customerId);

	@Query("from Merchant where id=:id")
	public Merchant getMerchentById(@Param("id")Long id);
}

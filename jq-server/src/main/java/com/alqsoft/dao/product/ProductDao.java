package com.alqsoft.dao.product;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.Product;

/**
 * 服务包dao
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午8:23:19
 * 
 */
public interface ProductDao extends BaseDao<Product> {

	
	@Query("from Product where id=:id")
	Product getProductById(@Param("id")Long id);
}

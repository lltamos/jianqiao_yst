package com.yst.web.dao;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yst.web.model.Product;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年6月25日 上午11:05:37
 * 
 */
public interface ProductDaos extends BaseDao<Product>{

	@Query("from Product as d where product_type_id=:product_type_id")
	List<Product> getProductByProductTypeId(@Param("product_type_id")Integer product_type_id);

}

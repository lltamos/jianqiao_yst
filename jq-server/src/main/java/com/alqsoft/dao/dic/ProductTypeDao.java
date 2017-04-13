package com.alqsoft.dao.dic;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.ProductType;


public interface ProductTypeDao extends BaseDao<ProductType>{

	@Query("from ProductType where id=:id")
	ProductType getProductTypeById(@Param("id")Long id);

}

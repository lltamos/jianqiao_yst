package com.alqsoft.dao.productimage;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.productimage.ProductImage;


public interface ProductImageDao extends BaseDao<ProductImage>{
	@Query("FROM ProductImage o WHERE o.productId=:productId")
	List<ProductImage> getImageByProductId(@Param("productId")Long productId);
	@Modifying
	@Query(value="delete from product_image WHERE product_id=:productId",nativeQuery=true)
	void deleteByProductId(@Param("productId")Long productId);
}

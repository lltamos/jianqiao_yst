package com.alqsoft.service.productimage;

import java.util.List;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.productimage.ProductImage;

public interface ProductImageService extends BaseService<ProductImage>{
	List<ProductImage> getImageByProductId(Long productId);
}

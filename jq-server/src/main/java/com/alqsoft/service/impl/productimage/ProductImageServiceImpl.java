package com.alqsoft.service.impl.productimage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.productimage.ProductImageDao;
import com.alqsoft.entity.productimage.ProductImage;
import com.alqsoft.service.productimage.ProductImageService;


@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	private ProductImageDao productImageDao;
	
	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductImage get(Long arg0) {
		// TODO Auto-generated method stub
		return productImageDao.findOne(arg0);
	}

	@Override
	public ProductImage saveAndModify(ProductImage arg0) {
		// TODO Auto-generated method stub
		return productImageDao.save(arg0);
	}

	@Override
	public List<ProductImage> getImageByProductId(Long productId) {
		return this.productImageDao.getImageByProductId(productId);
	}

}

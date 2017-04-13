package com.alqsoft.service.impl.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.product.ProductDao;
import com.alqsoft.entity.Product;
import com.alqsoft.service.product.ProductService;

@Service
@Transactional(readOnly=true)
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Map<String, Object>> findServiceByCategory(String departmentName) {
		List<Map<String, Object>> category = productDao.findServiceByCategory(departmentName);
		return category;
	}
	
	

	@Override
	public List<Map<String, Object>> getProductIndx(Integer rows) {
		List<Map<String, Object>> productIndex = productDao.getProductIndex(rows);
		return productIndex;
	}

	@Override
	public List<Map<String, Object>> getProductByPage(Map<String, Object> params) {
		List<Map<String, Object>> productByPage = productDao.getProductByPage(params);
		return productByPage;
	}



	@Override
	public List<Map<String, Object>> serviceByType(Map<String, Object> params) {
		List<Map<String, Object>> serviceByType = productDao.serviceByType(params);
		return serviceByType;
	}



	@Override
	public Integer getProductTotal() {
		return productDao.getProductTotal();
	}



	@Override
	public Integer getProductTotalByServiceName(String serviceName) {
		// TODO Auto-generated method stub
		return productDao.getProductTotalByServiceName(serviceName);
	}



	@Override
	public String getProductNameById(Long id) {
		// TODO Auto-generated method stub
		return productDao.getProductNameById(id);
	}



	@Override
	public Product getProductById(Long productId) {
		Product productById = productDao.getProductById(productId);
		return productById;
	}



	@Override
	public String getProductImageById(Long productId) {
		return productDao.getProductImageById(productId);
	}



	

	
	
	
}

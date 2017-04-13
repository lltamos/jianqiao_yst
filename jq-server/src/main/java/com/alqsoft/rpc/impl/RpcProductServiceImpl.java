package com.alqsoft.rpc.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.product.ProductDao;
import com.alqsoft.entity.Product;
import com.alqsoft.rpc.RpcProductService;
@Service
@Transactional
public class RpcProductServiceImpl implements RpcProductService {
	
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		Product product = productDao.getProductById(id);
		
		return product;
	}

}

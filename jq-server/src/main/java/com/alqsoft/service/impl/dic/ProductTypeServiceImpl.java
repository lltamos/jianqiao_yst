package com.alqsoft.service.impl.dic;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.dic.ProductTypeDao;
import com.alqsoft.entity.ProductType;
import com.alqsoft.service.dic.ProductTypeService;
import com.google.common.collect.Lists;
@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService{

	@Autowired
	private ProductTypeDao productTypeDao;
	@Override
	public boolean delete(Long arg0) {
		productTypeDao.delete(arg0);
		return true;
	}

	@Override
	public ProductType get(Long arg0) {
		return productTypeDao.findOne(arg0);
	}

	@Override
	public ProductType saveAndModify(ProductType arg0) {
		return productTypeDao.save(arg0);
	}

	@Override
	public List<ProductType> findAll() {
		Iterable<ProductType> iterable = productTypeDao.findAll(new Sort(Direction.DESC, new String[] { "createdTime" }));
		List<ProductType> dicOfficelist = Lists.newArrayList(iterable.iterator());
		return dicOfficelist;
	}
	
	

}

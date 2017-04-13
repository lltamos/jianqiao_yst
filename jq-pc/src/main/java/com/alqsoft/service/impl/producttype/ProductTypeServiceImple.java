package com.alqsoft.service.impl.producttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.producttype.ProductTypeDao;
import com.alqsoft.entity.ProductType;
import com.alqsoft.service.producttype.ProductTypeService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 下午2:09:34
 * 
 */
@Service
@Transactional
public class ProductTypeServiceImple implements ProductTypeService {

	@Autowired
	private ProductTypeDao productTypeDao;
	
	@Override
	public ProductType findProductTypeInfo(Long producttypeid) {
		return productTypeDao.findProductTypeInfo(producttypeid);
	}

}

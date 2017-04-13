package com.yst.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.yst.web.dao.ProductDao;
import com.yst.web.model.Product;
import com.yst.web.utils.BaseDaoImpl;

@Repository("productDao")
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {

}

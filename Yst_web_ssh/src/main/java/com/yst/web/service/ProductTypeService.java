package com.yst.web.service;

import java.util.List;

import org.alqframework.result.Result;

import com.yst.web.model.AppResult;
import com.yst.web.model.ProductType;

public interface ProductTypeService {
	public List<ProductType> selectAll();
	public List<ProductType> queryList();
	public ProductType findProductTypeInfo(Integer product_type_id);
	public AppResult updateProductType(ProductType productType);
	public void deleteProductType(Integer product_type_id);
	public AppResult addProductType(ProductType productType);
	
	public AppResult getProductTypeList();
	public Result findProductTypeAll(Integer page,Integer rows);
	
}

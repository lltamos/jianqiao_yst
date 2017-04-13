package com.yst.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yst.web.model.AppResult;
import com.yst.web.model.Product;

public interface ProductService {
	public Product findById(int id);
	public List<Product> selectAll();
	public void add(Product product);
	public void deleteById(int id);
	public void update(Product product);
	public AppResult reg(Product product);
	public AppResult getProductList(Product product);
	public AppResult updateGetInfo(Product product);
	public AppResult deleteProduct(Product product);
	public AppResult getAllList(Product product);
	public List<Product> selectAllByPage();
	public AppResult updateInfo(Product product);
	public AppResult uploadFile(Product product);
	public void testSearch(Product product);
	public void initIndex();
	public AppResult getInfo(Product product);
	public AppResult selectProductList(Product product, HttpServletRequest request);
	
	public List<Product> findByOtherColumn(String column, Object value);
}

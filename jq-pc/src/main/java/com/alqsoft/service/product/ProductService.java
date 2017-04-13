package com.alqsoft.service.product;

import java.util.List;
import java.util.Map;

import com.alqsoft.entity.Product;

public interface ProductService {
	/**
	 * 疑难杂症的导航
	 */
	public  List<Map<String, Object>> findServiceByCategory(String departmentName);
	
	/**
	 * 首页显示疑难杂症
	 */
	public  List<Map<String, Object>> getProductIndx(Integer rows);
	
	/**
	 * 分页显示疑难杂症
	 */
	public  List<Map<String, Object>> getProductByPage(Map<String, Object> params);
	
	/**
	 * 根据导航类型查询服务
	 */
	public List<Map<String, Object>> serviceByType(Map<String, Object> params);
	
	/**
	 * 查询服务包总数
	 */
	public Integer getProductTotal();
	
	/**
	 * 根据服务包类别的到服务包总数
	 */
	public Integer getProductTotalByServiceName(String serviceName);
	
	/**
	 * 根据服务包id查询类型id
	 * @param id
	 * @return
	 */
	public String getProductNameById(Long id);

	public Product getProductById(Long productId);

	public String getProductImageById(Long productId);

	
}

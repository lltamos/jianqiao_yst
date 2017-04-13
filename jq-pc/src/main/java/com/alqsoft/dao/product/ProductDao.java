package com.alqsoft.dao.product;
/**
 * 
 * @Description: ProductDao
 * @author shenguang
 * @e-mail shenguang044539@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月13日 下午1:47:30
 * Copyright  2013 北京易商通公司 All rights reserved.
 *
 */

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.Product;
@MyBatisRepository
public interface ProductDao{
	/**
	 * 疑难杂症的导航
	 */
	public List<Map<String, Object>> findServiceByCategory(String departmentName);

	/**
	 * 首页显示疑难杂症
	 */
	public List<Map<String, Object>> getProductIndex(Integer rows);
	
	/**
	 * 分页显示疑难杂症
	 */
	public List<Map<String, Object>> getProductByPage(Map<String, Object> map);
	
	/**
	 * 根据导航类型查询服务
	 */
	public List<Map<String, Object>> serviceByType(Map<String, Object> params);
	
	/**
	 * 获得说有服务包的总数
	 */
	public Integer getProductTotal();
	
	/**
	 * 根据类别获得该类别的说有服务包的总数
	 */
	public Integer getProductTotalByServiceName(String serviceName);

	/**
	 * 查询服务包
	 * @param productId
	 * @return
	 */
	public Product getProductById(Long id);
	
	/**
	 * 根据服务包id获取服务类型
	 * @param id
	 * @return
	 */
	public String getProductNameById(Long id);
	
	/**
	 * 查询id
	 * @param productId
	 * @return
	 */
	public String getProductImageById(Long id);

}
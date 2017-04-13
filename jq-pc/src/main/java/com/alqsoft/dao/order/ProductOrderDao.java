package com.alqsoft.dao.order;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.ProductOrder;

/**
 * 
 * @Description: TODO
 * @author 服务包订单
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月4日 下午3:50:02 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@MyBatisRepository
public interface ProductOrderDao {

	/** 查询所有订单（分页） */
	public List<Map<String, Object>> findProductOrderListByCid(Map<String, Object> map);

	// 根据id查询订单
	public Map<String, Object> getProductOrderById(Long orderId);

	/** 查询订单 根据订单状态，status=0 代表待支付，1代表支付完成（分页） */
	public List<Map<String, Object>> findProductOrderByStatusAndCId(Map<String, Object> map);

	// 查询总数
	public int getCountFormOrder(Long cid);
	
	//返回某种状态订单总数
	public int getCountFormOrderByStatus(Map<String, Object> map);
	
	/**
	 * 
	* @Title: findProductOrderListByDoctorId  
	* @Description: 根据医生id查询服务包订单
	* @author   腾卉 
	* @return List<Map<String,Object>>    返回类型
	 */
	public List<Map<String, Object>> findProductOrderListByDoctorId(Map<String, Object> map);
	/**
	 * 
	* @Title: getCountFormOrder  
	* @Description: 根据医生id查询服务包订单总数
	* @author   腾卉 
	* @return int    返回类型
	 */
	public int getCountOrderByDoctorId(Long doctor_id);

	public List<ProductOrder> getProductorderByCid(Map<String, Object> map);

	public int getProductorderByCidCount(long cid);

	public ProductOrder getProductOrderByProductOrderId(Long product);

	public int getProductIdById(Long oid);

	public ProductOrder getProductOrderByOid(Long productOrderId);
	//根据订单号查找订单
	public ProductOrder getProductOrder(Map<String, Object> map);
}

package com.alqsoft.service.order;

import java.util.List;
import java.util.Map;

import com.alqsoft.entity.ProductOrder;

/**
 * 
 * @Description: TODO
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月12日 下午3:30:07 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
public interface ProductOrderService {

	public Map<String, Object> getProductOrderDateils(Long orderId);

	/**
	 * 
	 * @param page
	 * @param rows
	 * @param status 0 未支付，1，已支付
	 * @param cid
	 * @return
	 */
	public List<Map<String, Object>> findProductOrderByStatusAndCId(Integer page, Integer rows,
			Integer status, Long cid);

	public List<Map<String, Object>> getProductOrderAllList(Integer page, Integer rows, Long cid);

	// 返回总数
	public int getCountFormOrder(Long cid);

	// 返回某种状态订单总数
	public int getCountFormOrderByStatus(Long cid,Integer status);
	
	/**
	 * 
	* @Title: findProductOrderListByDoctorId  
	* @Description: 根据医生id查询服务包订单
	* @author   腾卉 
	* @return List<Map<String,Object>>    返回类型
	 */
	public List<Map<String, Object>> findProductOrderListByDoctorId(Long doctor_id,Integer page, Integer rows);
	/**
	 * 
	* @Title: getCountFormOrder  
	* @Description: 根据医生id查询服务包订单总数
	* @author   腾卉 
	* @return int    返回类型
	 */
	public int getCountOrderByDoctorId(Long doctor_id);

	public int getProductIdById(Long oid);

	public ProductOrder getProductOrderByOid(Long productOrderId);
	
}

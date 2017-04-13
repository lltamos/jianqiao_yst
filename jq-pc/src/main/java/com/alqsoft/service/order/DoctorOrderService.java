package com.alqsoft.service.order;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: TODO
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月12日 下午3:30:07
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface DoctorOrderService {
	
	public Map<String, Object> getDoctorOrderDateils(Long orderId);
	
	
	/**
	 * 
	 * @param page
	 * @param rows
	 * @param status 0 未支付，1，已支付
	 * @param cid
	 * @return
	 */
	public List<Map<String, Object>> findDoctorOrderByStatusAndCId(Integer page, Integer rows,
			Integer status, Long cid);

	
	
	//返回总数
	public int getCountFormOrder(Long cid);
	
	
	// 返回某种状态订单总数
	public int getCountFormOrderByStatus(Long cid,Integer status);
	
	public List<Map<String, Object>> getDoctorOrderAllList(Integer page, Integer rows,
			Long cid);
	
	
	/**
	 * 
	* @Title: findDoctorOrderListByDoctorId  
	* @Description: 根据医生id查询订单记录
	* @author   腾卉 
	* @return List<Map<String,Object>>    返回类型
	 */
	public List<Map<String, Object>> getDoctorOrderListByDoctorId(Long doctor_id,Integer page, Integer rows);
	
	/**
	 * 
	* @Title: getOrderCountByDoctorId  
	* @Description: 根据医生id查询订单数量
	* @author   腾卉 
	* @return Integer    返回类型
	 */
	public int getOrderCountByDoctorId(Long doctor_id);
}

package com.alqsoft.dao.order;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.DoctorServiceOrder;

/**
 * 
 * @Description: TODO
 * @author 医生订单
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月4日 下午3:50:02 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@MyBatisRepository
public interface DoctorOrderDao {

	/**查询所有订单（分页） */
	public List<Map<String, Object>> findDoctorOrderListByCid(Map<String, Object> map);
	
	

	/** 查询订单 根据订单状态，status=0 代表待支付，1代表支付完成（分页） */
	public List<Map<String, Object>> findDoctorOrderByStatusAndCId(Map<String, Object> map);
	
	

	//根据id查询订单
	public Map<String,Object> getDoctorOrderById(Long orderId);
	
	// 查询总数
	public int getCountFormOrder(Long cid);
	
	//返回某种状态订单总数
	public int getCountFormOrderByStatus(Map<String, Object> map);
	
	/**
	 * 
	* @Title: findDoctorOrderListByDoctorId  
	* @Description: 根据医生id查询订单
	* @author   腾卉 
	* @return List<Map<String,Object>>    返回类型
	 */
	public List<Map<String, Object>> getDoctorOrderListByDoctorId(Map<String, Object> map);
	
	/**
	 * 
	* @Title: getOrderCountByDoctorId  
	* @Description: 查询订单数量
	* @author   腾卉 
	* @return Integer    返回类型
	 */
	public int getOrderCountByDoctorId(Long doctor_id);
	
	//根据医生订单号获取订单
	public DoctorServiceOrder getDoctorOrder(Map<String, Object> map);
	
}

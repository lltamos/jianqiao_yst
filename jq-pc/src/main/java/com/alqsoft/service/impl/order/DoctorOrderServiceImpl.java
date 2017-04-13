package com.alqsoft.service.impl.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.order.DoctorOrderDao;
import com.alqsoft.service.order.DoctorOrderService;

/**
 * 
 * @Description: TODO
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月13日 下午2:11:18 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Service
public class DoctorOrderServiceImpl implements DoctorOrderService {

	@Autowired
	private DoctorOrderDao doctorOrderDao;

	// 根据用户查询订单
		@Override
		public List<Map<String, Object>> getDoctorOrderAllList(Integer page, Integer rows, Long cid) {

			Map<String, Object> map = new HashMap<String, Object>();
			
			int index=(page-1)*rows;
			map.put("startIndex", index);
			map.put("endIndex", rows);
			map.put("id", cid);
			List<Map<String, Object>> list = doctorOrderDao.findDoctorOrderListByCid(map);

			if (list != null) {

				for (Map<String, Object> m : list) {
					
					m.put("order_genre", "doctororder");
					
				}

			}

			return list;
		}
		
	@Override
	public Map<String, Object> getDoctorOrderDateils(Long orderId) {
		Map<String, Object> map = doctorOrderDao.getDoctorOrderById(orderId);
		return map;
	}

	// 根据用户和状态查询订单
	@Override
	public List<Map<String, Object>> findDoctorOrderByStatusAndCId(Integer page, Integer rows,
			Integer status, Long cid) {

		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("startIndex", index);
		map.put("endIndex", rows);
		map.put("status", status);
		map.put("id", cid);
		List<Map<String, Object>> list = doctorOrderDao.findDoctorOrderByStatusAndCId(map);
		return list;

	}


	@Override
	public int getCountFormOrder(Long cid) {
				
		return doctorOrderDao.getCountFormOrder(cid);
	}

	@Override
	public int getCountFormOrderByStatus(Long cid,Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", cid);
		map.put("status", status);
		
		int countFormOrderByStatus = doctorOrderDao.getCountFormOrderByStatus(map);
		
		return countFormOrderByStatus;
	}

	/**
	 * 腾卉
	 */
	@Override
	public List<Map<String, Object>> getDoctorOrderListByDoctorId(Long doctor_id,Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("doctor_id", doctor_id);
		map.put("startIndex", index);
		map.put("endIndex", rows);
		return doctorOrderDao.getDoctorOrderListByDoctorId(map);
	}

	/**
	 * 腾卉
	 */
	@Override
	public int getOrderCountByDoctorId(Long doctor_id) {
		return doctorOrderDao.getOrderCountByDoctorId(doctor_id);
	}

}

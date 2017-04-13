package com.alqsoft.service.impl.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.order.ProductOrderDao;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.service.order.ProductOrderService;

/**
 * 
 * @Description: TODO
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月12日 下午3:48:26 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

	@Autowired
	private ProductOrderDao productOrderDao;

	@Override
	public Map<String, Object> getProductOrderDateils(Long orderId) {
		Map<String, Object> map = productOrderDao.getProductOrderById(orderId);
		return map;
	}

	@Override
	public List<Map<String, Object>> findProductOrderByStatusAndCId(Integer page, Integer rows,
			Integer status, Long cid) {

		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("startIndex", index);
	
		map.put("endIndex", rows);
		map.put("status", status);
		map.put("id", cid);
		List<Map<String, Object>> list = productOrderDao.findProductOrderByStatusAndCId(map);

		if (list != null) {

			for (Map<String, Object> m : list) {

				m.put("order_genre", "productorder");

			}

		}

		return list;

	}

	@Override
	public List<Map<String, Object>> getProductOrderAllList(Integer page, Integer rows, Long cid) {

		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("startIndex", index);
		
		map.put("endIndex", rows);
		map.put("id", cid);
		List<Map<String, Object>> list = productOrderDao.findProductOrderListByCid(map);
		return list;
	}

	@Override
	public int getCountFormOrder(Long cid) {
		
		return productOrderDao.getCountFormOrder(cid);
	}

	@Override
	public int getCountFormOrderByStatus(Long cid, Integer status) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id", cid);
		map.put("status", status);

		int countFormOrderByStatus = productOrderDao.getCountFormOrderByStatus(map);

		return countFormOrderByStatus;
	
	}

	/**
	 * 腾卉
	 */
	@Override
	public List<Map<String, Object>> findProductOrderListByDoctorId(Long doctor_id,Integer page, Integer rows ) {
		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("doctor_id", doctor_id);
		map.put("startIndex", index);
		map.put("endIndex", rows);
		List<Map<String, Object>> list = productOrderDao.findProductOrderListByDoctorId(map);
		return list;
	}

	/**
	 * 腾卉
	 */
	@Override
	public int getCountOrderByDoctorId(Long doctor_id) {
		return productOrderDao.getCountOrderByDoctorId(doctor_id);
	}

	@Override
	public int getProductIdById(Long oid) {
		return productOrderDao.getProductIdById(oid);
	}

	@Override
	public ProductOrder getProductOrderByOid(Long productOrderId) {
		return productOrderDao.getProductOrderByOid(productOrderId);
	}

	

}

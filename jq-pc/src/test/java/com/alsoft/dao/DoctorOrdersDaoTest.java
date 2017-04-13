package com.alsoft.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.alqsoft.dao.order.DoctorOrderDao;

/**
 * 
 * @Description: TODO
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月9日 下午6:25:43 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */

public class DoctorOrdersDaoTest {

	private SqlSession session;

	@Before
	public void before() throws IOException {

		String resource = "mybatis/mybatis.xml";
		InputStream reader = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
		session = ssf.openSession();

	}


	//分页查看医生订单测试
	@Test
	public void findPageOrder() {
		DoctorOrderDao orderDao = session.getMapper(DoctorOrderDao.class);
		
		Map<String,Object> map=new  HashMap();
		map.put("startIndex", 0);
		map.put("endIndex", 5);
		map.put("id", 1);
		List<Map<String, Object>> findPageOrders = orderDao.findDoctorOrderListByCid(map);
		System.out.println(findPageOrders);
		System.out.println(findPageOrders.size());

	}
	
	//待支付订单测试 ，已支付订单测试
	@Test
	public void findToBePayOrder() {
		DoctorOrderDao orderDao = session.getMapper(DoctorOrderDao.class);
		
		Map<String,Object> map=new  HashMap<String,Object>();
		map.put("startIndex", 0);
		map.put("endIndex", 5);
		map.put("id", 1);
		map.put("status", 1);
		List<Map<String, Object>> findPageOrders = orderDao.findDoctorOrderByStatusAndCId(map);
		System.out.println(findPageOrders);
		System.out.println(findPageOrders.size());

	}

}

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

import com.alqsoft.dao.order.ProductOrderDao;

/**
 * 
 * @Description: TODO
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 下午1:39:23
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public class ProductOrdersDaoTest {
	private SqlSession session;

	@Before
	public void before() throws IOException {

		String resource = "mybatis/mybatis.xml";
		InputStream reader = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
		session = ssf.openSession();

	}
	
	

	
	//分页查看全部服务包订单
	@Test
	public void findFulfillOdersPageTest(){
		ProductOrderDao orderDao = session.getMapper(ProductOrderDao.class);
		
			Map<String, Object>  map=new HashMap<String,Object>();
			map.put("startIndex", 0);
			map.put("endIndex", 5);
			map.put("id", 1);
			map.put("status", 0);
			
			List<Map<String,Object>> orders = orderDao.findProductOrderByStatusAndCId(map);			
			System.out.println(orders.size());
			System.out.println(orders);
			
		
	}

}

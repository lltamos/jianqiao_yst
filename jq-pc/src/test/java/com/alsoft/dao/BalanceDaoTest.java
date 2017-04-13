package com.alsoft.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.alqsoft.dao.balance.BalanceDao;

/**
 * 
 * @Description: TODO
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 下午2:45:21
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public class BalanceDaoTest {
	
	private SqlSession session;

	@Before
	public void before() throws IOException {

		String resource = "mybatis/mybatis.xml";
		InputStream reader = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
		session = ssf.openSession();

	}
	
	//查询用户余额测试
	@Test
	public void getBalanceByCidTest(){
		BalanceDao balanceDao = session.getMapper(BalanceDao.class);
		Map<String, Object> map = balanceDao.getBalanceByCId(1);
		System.out.println(map.get("balance_remain"));
		
	}

}

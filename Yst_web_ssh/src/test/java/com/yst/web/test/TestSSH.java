package com.yst.web.test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yst.web.dao.UserDao;
import com.yst.web.model.User;
import com.yst.web.service.UserService;

public class TestSSH {
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		UserService us = (UserService)ac.getBean("userService");
		UserDao ud = (UserDao)ac.getBean("userDao");
		User user = new User();
		user.setLogin_name("admin");
		user.setPassword("12345");
//		us.reg(user);

	}
}

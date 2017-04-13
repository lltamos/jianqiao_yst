/**
 * Project Name:Yst_web_ssh
 * File Name:TestHiber.java
 * Package Name:com.yst.web.test
 * Date:2016年1月5日上午10:08:25
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.yst.web.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yst.web.model.Merchant;
import com.yst.web.service.MerchantService;

/**
 * ClassName:TestHiber <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年1月5日 上午10:08:25 <br/>
 * @author   张灿
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:/applicationContext.xml")
public class TestHiber {
	
	@Resource(name="merchantService")
	private MerchantService merchantService;
	
	@Test
	public void testMerchantService(){
		Merchant m = merchantService.findById(3);
		System.out.println(m.getDes());
	}
}


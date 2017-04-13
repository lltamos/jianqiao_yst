/**
 * Project Name:Yst_web_ssh
 * File Name:TestController.java
 * Package Name:com.yst.web.controller.test
 * Date:2016年1月5日上午11:08:42
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.yst.web.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:TestController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年1月5日 上午11:08:42 <br/>
 * @author   张灿
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping("ydmvc/test")
public class TestController {
	
	
	@RequestMapping("test.do")
	@ResponseBody
	public String test(){
		return "test";
	}
}


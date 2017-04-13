/**
 * Project Name:ydg
 * File Name:index.java
 * Package Name:com.ydg.web.action
 * Date:2015年11月18日下午8:35:38
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.yst.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.utils.BaseAction;

/**
 * ClassName:index <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年11月18日 下午8:35:38 <br/>
 * @author   lenovo
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller("indexAction")
@Scope("prototype")
public class indexAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7594058191468295509L;

	/**
	 * 跳转到登陆页
	 * @return
	 */
	public String index(){
		return "index";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}	


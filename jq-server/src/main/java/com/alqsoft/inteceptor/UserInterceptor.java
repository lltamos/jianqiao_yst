package com.alqsoft.inteceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alqsoft.entity.UserTable;



/**
 * 
 * @Title: UserInterceptor.java
 * @Description: 用户登录拦截器 
 * @author 陈振兵
 * @e-mail chenzhenbing@139.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年1月10日 下午3:41:23
 * Copyright © 2013 厦门卓讯信息技术有限公司 All rights reserved.
 *
 */
public class UserInterceptor implements HandlerInterceptor {
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception err)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		//System.out.println("========进入登录拦截器了========");
		String requestUri = request.getRequestURI().toString();
		// 获取session值---到时 开发的时候启用
		UserTable user = (UserTable) request.getSession().getAttribute("dbUser");
			if(user==null){
				if (request.getHeader("x-requested-with") != null&& request
		                 .getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
					response.setCharacterEncoding("UTF-8");  
					response.setContentType("application/json; charset=utf-8");  
					PrintWriter out = response.getWriter();
					out.write("{\"code\":\"3\",\"msg\":\"未登录或登录过期\"}");
					out.close();
				}else{
					response.sendRedirect(request.getContextPath().toString() + "/user/user-login");// 跳转到登录页面
				}
				return false;
		    }else{
		    	Map<String, Object> params = new HashMap<String, Object>();
				String roleId = user.getAuthority();
		    	if(requestUri.contains("/admin/after")){//后台-管理员权限
		    		if(roleId.equals("1")){
		    			return true;
		    		}
		    	}
		    	if(requestUri.contains("/hospital/after/")){//后台-总院权限
		    		if(roleId.equals("2")){
		    			return true;
		    		}
		    	}
		    	if(requestUri.contains("/doctor/after/")){//后台-医生权限
		    		if(roleId.equals("3")){
		    			return true;
		    		}
		    	}
		    	if(requestUri.contains("/referrer/after/")){//后台推荐人-权限
		    		if(roleId.equals("4")){
		    			return true;
		    		}
		    	}
		    	/*if(requestUri.contains("/user/user-index/")){//后台推荐人-权限
		    		if(!roleId.equals("") && roleId != null){
		    			return true;
		    		}
		    	}*/
		    	response.sendRedirect(request.getContextPath().toString() + "/user/user-login");// 跳转到登录页面
		    	return false;
		    }
	}

}

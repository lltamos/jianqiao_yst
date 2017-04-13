package com.yst.web.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
/**
 * 拦截spring mvc的请求 传递给 spring mvc servlet
 * @author zc
 *
 */
public class StrutsAndSpringMvcFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		String uri = request.getRequestURI();
		if(StringUtils.contains(uri, "ydmvc")){
			System.out.println("进入springmc控制层");
			System.out.println(StringUtils.replace(uri, request.getContextPath(), ""));
			request.getRequestDispatcher(StringUtils.replace(uri, request.getContextPath(), "")).forward(arg0, arg1);
			return;
		}else{
			System.out.println(uri);
			if(StringUtils.equals(uri, "/")||StringUtils.equals(uri, "")){
				response.sendRedirect(uri+"index!index");
				return;
			}
			System.out.println("进入struts控制层");
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}

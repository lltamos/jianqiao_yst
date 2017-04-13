package com.yst.web.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yst.web.model.Customer;
import com.yst.web.model.User;
import com.yst.web.utils.ServerParam;

/**
 * 系统登陆拦截器
 */
public class LoginInterceptor extends AbstractInterceptor {
	private static Log logger = LogFactory.getLog(LoginInterceptor.class);

	/**
	 * 获取公共访问页面的地址
	 */
	private final List<String> getPublicUrls() {
		List<String> publicUrls = new ArrayList<String>();
		publicUrls.add("/user!getVerifyCode.action");
		publicUrls.add("/user!login");
		publicUrls.add("/user!getSession");
		publicUrls.add("/customer!webLogin");
		publicUrls.add("/product!productListPage");
		publicUrls.add("/product!productPage");
		publicUrls.add("/product!indexPage");
		publicUrls.add("/product!wapPage");
		publicUrls.add("/product!infoPage");
		publicUrls.add("/alipay!alipayNotify");
		publicUrls.add("/index!index");
		//publicUrls.add("/customerIllnessRecord!addIllnessRecord");
		return publicUrls;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		String url = request.getRequestURI();
		List<String> publicUrls = getPublicUrls();
		for (String publicUrl : publicUrls) {
			if (url.contains(publicUrl)) {
				return invocation.invoke();
			}
		}
		Map session = ctx.getSession();
		String client_type = request.getParameter("client_type");
		if (client_type == null || client_type.equals("")) {
			User user = (User) session.get(ServerParam.USER_SESSION);
			if (user != null) {
				return invocation.invoke();
			}
			logger.debug("client_type:" + client_type +",未登录或client_type为空");
			return "login_page";
		} else if (client_type.equals("I") || client_type.equals("A")) {
			logger.debug("client_type:" + client_type);
			return invocation.invoke();
		} else {
			logger.debug("client_type:" + client_type);
			return "login_page";
		}
	}

}

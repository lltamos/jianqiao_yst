package com.yst.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yst.web.model.PageModel;
import com.yst.web.utils.PageModelContext;

public class SpringPaginateInterceptor extends HandlerInterceptorAdapter {
	private static Log logger = LogFactory
			.getLog(SpringPaginateInterceptor.class);

	private int pageSize = 5;

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		PageModelContext.removePageModel();
		PageModel pm = new PageModel();
		pm.setStartIndex(this.getStartIndex(request));
		pm.setPageSize(this.getPageSize(request));
		pm.setPageNo(this.getPageNo(request));
		pm.setSearch(this.getSearch(request));
		PageModelContext.setPageModel(pm);
		return true;
	}

	private int getPageNo(HttpServletRequest request) {
		String PageNoStr = request.getParameter("page_no") == null ? null
				: request.getParameter("page_no");
		if (PageNoStr != null && !PageNoStr.equals("")) {
			return Integer.parseInt(PageNoStr);
		}
		return 0;
	}

	private int getStartIndex(HttpServletRequest request) {
		String startIndexStr = request.getParameter("start") == null ? null
				: request.getParameter("start");
		if (startIndexStr != null && !startIndexStr.equals("")) {
			return Integer.parseInt(startIndexStr);
		}
		return 0;
	}

	private int getPageSize(HttpServletRequest request) {
		if (request.getParameter("length") != null
				|| request.getParameter("page_size") != null) {
			String name = request.getParameter("length") != null ? "length"
					: "page_size";
			String pageSizeStr = request.getParameter(name);
			if (pageSizeStr != null && !pageSizeStr.equals("")) {
				return Integer.parseInt(pageSizeStr);
			}
		}
		return pageSize;
	}

	private String getSearch(HttpServletRequest request) {
		String searchStr = request.getParameter("search") == null ? null
				: request.getParameter("search");
		if (searchStr != null && !searchStr.equals("")) {
			return searchStr;
		}
		return null;
	}
}

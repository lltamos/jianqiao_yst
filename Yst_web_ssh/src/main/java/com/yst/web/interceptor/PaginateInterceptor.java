package com.yst.web.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yst.web.model.PageModel;
import com.yst.web.utils.PageModelContext;

public class PaginateInterceptor extends AbstractInterceptor {
	private static Log logger = LogFactory.getLog(PaginateInterceptor.class);
	
	private int pageSize=5;
	

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		PageModel pm = new PageModel();
		pm.setStartIndex(this.getStartIndex(invocation));
		pm.setPageSize(this.getPageSize(invocation));
		pm.setPageNo(this.getPageNo(invocation));
		pm.setSearch(this.getSearch(invocation));
		PageModelContext.setPageModel(pm);
		String result = invocation.invoke();
		PageModelContext.removePageModel();
		return result;
	}
	private int getPageNo(ActionInvocation invocation) {
		if (invocation.getInvocationContext().getParameters().get("page_no") != null) {
			String PageNoStr = ((String[])invocation.getInvocationContext().getParameters().get("page_no"))[0];
			if(PageNoStr !=null && !PageNoStr.equals("")){
				return Integer.parseInt(PageNoStr);
			}
		}
		return 0;
	}
	private int getStartIndex(ActionInvocation invocation) {
		if (invocation.getInvocationContext().getParameters().get("start") != null) {
			String startIndexStr = ((String[])invocation.getInvocationContext().getParameters().get("start"))[0];
			if(startIndexStr !=null && !startIndexStr.equals("")){
				return Integer.parseInt(startIndexStr);
			}
		}
		return 0;
	}

	private int getPageSize(ActionInvocation invocation) {
		if (invocation.getInvocationContext().getParameters().get("length") != null || invocation.getInvocationContext().getParameters().get("page_size") != null) {
			String name = (invocation.getInvocationContext().getParameters().get("length") != null)?"length":"page_size";
			String pageSizeStr = ((String[])invocation.getInvocationContext().getParameters().get(name))[0];
			if(pageSizeStr !=null && !pageSizeStr.equals("")){
				return Integer.parseInt(pageSizeStr);
			}
		}
		return pageSize;
	}
	private String getSearch(ActionInvocation invocation) {
		if (invocation.getInvocationContext().getParameters().get("search") != null) {
			String startIndexStr = ((String[])invocation.getInvocationContext().getParameters().get("search"))[0];
			if(startIndexStr !=null && !startIndexStr.equals("")){
				return startIndexStr;
			}
		}
		return null;
	}
}

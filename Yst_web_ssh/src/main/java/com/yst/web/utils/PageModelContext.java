package com.yst.web.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yst.web.model.PageModel;

public class PageModelContext {
	private static Log logger = LogFactory.getLog(PageModelContext.class);
	private static ThreadLocal pageModelThread = new ThreadLocal();

	public static PageModel getPageModel() {
		return (PageModel) pageModelThread.get();
	}

	public static void setPageModel(PageModel pm) {
		pageModelThread.set(pm);
	}
	
	public static void removePageModel(){
		pageModelThread.remove();
	}
	
}

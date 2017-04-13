package com.yst.web.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;

import com.yst.web.action.DoctorServiceOrderAction;
import com.yst.web.action.ProductAction;
import com.yst.web.thread.CheckServiceTimeThread;

public class ProjectInit implements ServletContextListener {
	static final Log logger = LogFactory.getLog(ProjectInit.class);

	// 系统初始化执行方法
	public void contextDestroyed(ServletContextEvent e) {
		logger.debug("系统停止...");
	}

	public void contextInitialized(ServletContextEvent e) {
		logger.debug("系统初始化开始...");
		// 获取项目根目录
		String root_path = e.getServletContext().getRealPath("/");
		WebApplicationContext root = (WebApplicationContext) e
				.getServletContext()
				.getAttribute(
						"org.springframework.web.context.WebApplicationContext.ROOT");
//		long start = System.currentTimeMillis();
//		ProductAction productAction = root.getBean(ProductAction.class);
//		productAction.initIndex();
//		long end = System.currentTimeMillis();
//		logger.debug("创建索引用时：" + (end - start));
		logger.debug("启动检查线程...");
		DoctorServiceOrderAction dsoa = root
				.getBean(DoctorServiceOrderAction.class);
		CheckServiceTimeThread cstt = new CheckServiceTimeThread(dsoa);
		Thread t = new Thread(cstt);
		t.start();
		logger.debug("成功启动检查线程...");
		logger.debug("application path : {" + root_path + "}");
		logger.debug("系统初始化结束...");
	}
}

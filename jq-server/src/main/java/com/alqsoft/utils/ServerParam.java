package com.alqsoft.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public interface ServerParam {
	//虚拟路径
	//public HttpServletRequest request = ServletActionContext.getRequest();
//	public String path = request.getContextPath();
//	public String basePath = request.getScheme() + "://" + request.getServerName()
//			+ ":" + request.getServerPort() + path + "/";// 项目url
	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
	public static String BASE_PATH =request.getRealPath("/"); 
	//imageUploadPath 图片上传路径
	public static String IMAGE_UPLOAD_PATH =request.getServletContext().getRealPath("")+"/upload/";
	//项目绝对路径
	public static String ROOT_PATH=request.getServletContext().getRealPath("")+"\\";
	//html模板路径
	public static String HTML_FTL_PATH="ftl/";
	//XML保存路径
	public static final String XML_FILE = "C://TelCode.xml";
	//域名
//	public static String DOMAIN = "http://192.168.1.73:8080";
	public static String DOMAIN = "http://182.254.137.185";
	//项目名
	public static String PROJECT_NAME = "/yst_web/";
	//是否禁用常量
	public static final Integer DELETE_YES = 1;
	public static final Integer DELETE_NO = 0;
	//是否下架常量
	public static final Integer OFF = 1;
	public static final Integer ON =0;
	
	//session名称
	public static final String BACK_URL="back_url";
	public static final String USER_SESSION = "dbUser";
	public static final String CUSTOMER_SESSION="dbCustomer";
	public static final String DOCTOR_SESSION="dbDoctor";
	//alipay
	// 余额充值 = 0,
	// 服务包付款 = 1,
	// 医生服务付款 = 2,
	// 健康中心付款 = 3,
	public static final String[] ALIPAY_ACT ={"余额充值","服务包付款","医生服务付款","健康中心付款"};
}

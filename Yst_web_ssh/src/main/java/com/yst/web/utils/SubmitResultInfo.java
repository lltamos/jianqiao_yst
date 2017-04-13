package com.yst.web.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 系统提交结果
 */
public class SubmitResultInfo {
	private static Log logger = LogFactory.getLog(SubmitResultInfo.class);
	
	/**
	 * 重定向地址
	 */
	public String url;
	
	public SubmitResultInfo(ResultInfo resultInfo){
		this.resultInfo = resultInfo;
	}
	
	//操作结果信息
	private ResultInfo resultInfo;
	
	public ResultInfo getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

		
}

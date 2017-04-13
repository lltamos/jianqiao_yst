package com.yst.web.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 系统抛出异常结果类
 */
public class ExceptionResultInfo extends Exception {
	private static Log logger = LogFactory.getLog(ExceptionResultInfo.class);

	private static final long serialVersionUID = 696796481651610659L;

	/**
	 * 系统提示信息
	 */
	private ResultInfo resultInfo;
	
	private String exceptionInfo;
	
	public String getExceptionInfo() {
		return exceptionInfo;
	}


	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}


	public ExceptionResultInfo(ResultInfo resultInfo) {
		super(resultInfo.toString());
		this.resultInfo = resultInfo;
	}


	public ResultInfo getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}

	public ExceptionResultInfo() {
		super();
	}

	public ExceptionResultInfo(String info) {
		super(info);
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
		resultInfo.setMessage(info);
		this.resultInfo = resultInfo;
	}

	public ExceptionResultInfo(String info, Integer errorCode) {
		super(info);
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
		resultInfo.setMessage(info);
		resultInfo.setType(errorCode);
		this.resultInfo = resultInfo;
	}

	public ExceptionResultInfo(Throwable cause) {
		super(cause);
	}
}

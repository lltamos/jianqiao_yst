package com.alqsoft.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.unionpay.acp.sdk.SDKConfig;

/**
 * @ClassName: InitParam
 * @Description: 初始化配置文件参数工具类
 * @author 张灿
 * @e-mail zhangzhaocan@yeah.net
 * @version v1.0
 * @copyright 2014-2016
 * @date 2014-5-4 下午5:34:38
 * 
 */
@Component
@Lazy(false)
public class InitParamPc {
	static final Log logger = LogFactory.getLog(InitParam.class);
	private Properties properties = new Properties();
	/** 操作对象. */
	private static InitParamPc initParam;

	@PostConstruct
	public void init() throws IOException {
		logger.info("系统初始化加载全局参数...");
		SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件
		logger.info(SDKConfig.getConfig().getBackRequestUrl() + "================================");
		InputStream inputStream = this.getClass().getResourceAsStream("/init.properties");
		properties.load(inputStream);
		IOUtils.closeQuietly(inputStream);
		logger.info("系统初始化加载结束...");
	}

	/**
	 * 获取initParam对象.
	 * 
	 * @return
	 */
	public static InitParamPc getInitParam() {
		if (null == initParam) {
			initParam = new InitParamPc();
			try {
				initParam.init();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return initParam;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}

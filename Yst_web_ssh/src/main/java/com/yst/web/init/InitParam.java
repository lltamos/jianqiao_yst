package com.yst.web.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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
public class InitParam {

	private Properties properties = new Properties();

	@PostConstruct
	public void init() throws IOException {
		InputStream inputStream = this.getClass().getResourceAsStream("/init.properties");
		properties.load(inputStream);
		IOUtils.closeQuietly(inputStream);
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}

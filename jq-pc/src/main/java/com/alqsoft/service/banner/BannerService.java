package com.alqsoft.service.banner;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月29日 下午4:57:51
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface BannerService {
	
	List<Map<String,Object>>fetchBanner(Integer len);
}

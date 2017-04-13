package com.alqsoft.service.imlog;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年4月5日 下午7:55:35
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface ImlogCusService {
	
	public List<Map<String, Object>> findAllByCustomerId(Long cid,int page,int row); 
	
	public int getCountByCustomerId(Long cid); 

}

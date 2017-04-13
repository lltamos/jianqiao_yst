/**
 * Project Name:xyt_ydg
 * File Name:BootStrapUtils.java
 * Package Name:com.ydg.web.utils
 * Date:2015年12月18日下午2:58:19
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.yst.web.utils;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * ClassName:BootStrapUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年12月18日 下午2:58:19 <br/>
 * @author   张灿
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class BootStrapResultUtils {
	/**
	 * 正常返回bootstrap数据
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public static <T> BootStrapResult<List<T>> returnPage(Class<T> clazz, Page<T> page) {
		BootStrapResult<List<T>> bootStrapResult = new BootStrapResult<List<T>>();
		bootStrapResult.setRecordsFiltered(Integer.valueOf((int) page.getTotalElements()));
		bootStrapResult.setRecordsTotal(Integer.valueOf((int) page.getTotalElements()));
		bootStrapResult.setData(page.getContent());
		return bootStrapResult;
	}
}


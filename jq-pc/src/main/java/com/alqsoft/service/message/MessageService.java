package com.alqsoft.service.message;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: MessageService 
* @Description: 健桥资讯service
* @author 腾卉 
* @date 2017年3月27日 下午4:13:10 
*
 */
public interface MessageService {

	/**
	 * 
	* @Title: getAll 
	* @Description: 查询全部资讯
	* @return List<Message>    返回类型 
	* @author 腾卉 
	* @throws
	 */
	public List<Map<String, Object>> getAll();
}

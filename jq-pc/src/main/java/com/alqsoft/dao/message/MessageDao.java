package com.alqsoft.dao.message;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

/**
 * 
* @ClassName: MessageDao 
* @Description: 健桥咨询dao
* @author 腾卉 
* @date 2017年3月27日 下午4:04:55 
*
 */
@MyBatisRepository
public interface MessageDao {

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

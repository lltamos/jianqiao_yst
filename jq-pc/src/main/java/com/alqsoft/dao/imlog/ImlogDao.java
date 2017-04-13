package com.alqsoft.dao.imlog;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

/**
 * 
* @ClassName: ImlogDao 
* @Description: 聊天记录dao
* @author 腾卉 
* @date 2017年4月5日 下午4:06:25 
*
 */
@MyBatisRepository
public interface ImlogDao {

	/**
	 * 
	* @Title: findAll 
	* @Description: 医生个人中心-查看聊天记录列表
	* @return List<Map<String,Object>>    返回类型 
	* @author 腾卉 
	* @throws
	 */
	public List<Map<String, Object>> findAllByDoctorId(Map<String, Object> map); 
	
	/**
	 * 
	* @Title: getCountByDoctorId 
	* @Description: 查询当前医生对应聊天记录总数
	* @return int    返回类型 
	* @author 腾卉 
	* @throws
	 */
	public int getCountByDoctorId(Long doctorId);
	
	
	
	public int getCountByCustomerId(Long cid);
	
	public List<Map<String, Object>> findAllByCustomerId(Map<String, Object> map); 
}

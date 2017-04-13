package com.alqsoft.service.imlog;

import java.util.List;
import java.util.Map;

public interface ImlogService {

	/**
	 * 
	* @Title: findAll 
	* @Description: 医生个人中心-查看聊天记录列表
	* @return List<Map<String,Object>>    返回类型 
	* @author 腾卉 
	* @throws
	 */
	public List<Map<String, Object>> findAllByDoctorId(Long doctorId,Integer page, Integer rows);
	/**
	 * 
	* @Title: getCountByDoctorId 
	* @Description: 查询当前医生对应聊天记录总数
	* @return int    返回类型 
	* @author 腾卉 
	* @throws
	 */
	public int getCountByDoctorId(Long doctorId); 
}

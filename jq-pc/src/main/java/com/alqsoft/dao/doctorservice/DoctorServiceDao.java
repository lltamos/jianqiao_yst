package com.alqsoft.dao.doctorservice;

import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

@MyBatisRepository
public interface DoctorServiceDao {
	
	/**
	 * 根据当前医生id查询当前咨询的价格
	 * @return
	 */
	public Map<String,Object> getDoctorServicePrice(Long id);
	
	
	
}

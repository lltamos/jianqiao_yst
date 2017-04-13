package com.alqsoft.service.doctorserver;

import java.util.Map;

public interface DoctorServerService {
	
	/**
	 * 根据当前医生id查询当前咨询的价格
	 * @return
	 */
	public Map<String,Object> getDoctorServicePrice(Long id);
}

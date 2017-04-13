package com.alqsoft.service.productdetails;

import java.util.List;
import java.util.Map;

public interface ProductDetailsService {
	/**
	 * 根据服务包ID获取服务包详情
	 * @return
	 */
	public Map<String,Object> getProductDetailsById(Long id);
	/**
	 * 获取服务包类型
	 * @return
	 */
	public Map<String,Object>  getServiceNameByServiceTypeId(Map<String, Object> map);
	/**
	 * 根据服务包服务类型ID获取对应的医生
	 * @return
	 */
	public List<Map<String,Object>> getDoctorByProductTypeId(Long id);
	
	/**
	 * 根据服务包服务类型ID获取对应的患者日记
	 *  @return
	 */
	public List<Map<String,Object>> getDiaryByProductTypeId(Map<String,Object> param);
	
	/**
	 * 获取最新的日记id
	 * @return
	 */
	public Map<String,Object> getNewDiary();
	
	/**
	 * 获取服务包定金
	 * @return
	 */
	public Map<String,Object> getDepositePrice(Long id);
}

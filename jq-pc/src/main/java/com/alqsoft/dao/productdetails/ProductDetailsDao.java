package com.alqsoft.dao.productdetails;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;


@MyBatisRepository
public interface ProductDetailsDao {
	
	/**
	 * 根据服务包ID获取服务包详情
	 * @return
	 */
	public Map<String,Object> getProductDetailsById(Long id);
	/**
	 * 根据服务包类型ID获取服务包类型名称
	 * @return
	 */
	public List<Map<String,Object>> getServiceNameByServiceTypeId(Map<String, Object> map);
	/**
	 * 根据服务包服务类型ID获取对应的医生 :根据职称等级排序,取4条
	 * @return
	 */
	public List<Map<String,Object>> getDoctorByProductTypeId(Long productTypeId);
	
	/**
	 * 根据服务包服务类型ID获取对应的患者日记:取最新的一条日记
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
	
	
	/**
	 * 根据日记id获取日记的点赞数量
	 * @return
	 */
	public Long getLikeNum(Long diaryId);
	
	/**
	 * 根据日记id获取该日记的图片
	 * @return
	 */
	public List<String> getImageByDiaryId(Long diaryId);
}

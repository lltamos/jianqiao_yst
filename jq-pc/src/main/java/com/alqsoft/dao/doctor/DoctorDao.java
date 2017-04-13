package com.alqsoft.dao.doctor;
/**
 * 
 * @Description: DoctorDao
 * @author shenguang
 * @e-mail shenguang044539@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月13日 下午1:47:30
 * Copyright  2013 北京易商通公司 All rights reserved.
 *
 */

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.PatientHistory;
import com.alqsoft.entity.doctor.DoctorInfo;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.patient.PatientDisease;
@MyBatisRepository
public interface DoctorDao{


	
	/**
	 * 首页：显示不同科室：暂时没有用到
	 */
	public List<Map<String, Object>> getServiceNameByOrder();
	
	/**
	 * 首页：根据科室名称查询出三个医生：暂时没有用
	 */
	public List<DoctorInfo> getDoctorByProducTypeId(Long producTypeId);
	
	/**
	 * 显示名医高手一级页页面
	 */
	public List<DoctorInfo> doctorPage(Map<String, Object> params);
	/**
	 * 根据医生id查询他的在线咨询数量
	 * @param doctorId
	 * @return
	 */
	public Integer countOnlineAsk(Long doctorId);
	/**
	 * 根据医生id查询他的在线预约数量
	 * @param doctorId
	 * @return
	 */
	public Integer countOnlineDate(Long doctorId);
	/**
	 * 获取，根据医生id的获取日记总数
	 */
	public Integer findDiaryByDoctorIdTotal(Long id);
	/**
	 *根据id获取医生个人信息的展示
	 */
	public DoctorInfo getDoctorInfoById(Long id);
	/**
	 *根据id获取医生的doctor_service_order价格
	 */
	public Integer getDoctorOrderPriceById(Long doctorId);
	/**
	 *根据id获取医生的在线预约价格
	 */
	public Map<String, Object> getProductOrderPriceById(Long doctorId);
	/**
	 *根据城市名称，获取该城市的医生 ：暂时没有用到
	 */
	public List<DoctorInfo> findDoctorByCityName(Map<String, Object> map);
	
	/**
	 * 分页显示医生的图片
	 */
	public List<Map<String, Object>> doctorImage(Map<String, Object> params);
	/**
	 * 显示医生的医生总条数
	 */
	public Integer doctorImageCount(Long doctorId);
	/**
	 * 显示医生的日记评价
	 */
	public List<Map<String, Object>> doctorDiary(Map<String, Object> params);
	
	/**
	 *患者求医页面，展示求以内容
	 */
	public List<PatientHistory> findDoctor(Map<String, Object> map);
	/**
	 *根据用户id获取用户图像
	 * @param custmorId
	 * @return
	 */
	public String findHeaderImage(Long custmorId);
	/**
	 *根据用户id获取，该用户的患者求医的图片
	 * @param custmorId
	 * @return
	 */
	public List<String> findDiseaseImage(Long id);
	
	/**
	 * 
	* @Title: findByCid  
	* @Description: 根据customerid查医生id
	* @author   腾卉 
	* @return Doctors    返回类型
	 */
	public Doctors findByCid(Long customer_id);
	/**
	 * 获取所有医生总数
	 * @param id
	 * @return
	 */
	public Integer getDoctorTotal();
	
	/**
	 * 获取所有的患者求医记录
	 * @param id
	 * @return
	 */
	public Integer getPatientTotal();
	
	/**
	 * 获取，根据城市和科室的记录总数
	 */
	public Integer getDoctorByCitNameTotal();
	
	/**
	 * 根据患者求医ID查找cutomerId
	 */
	public PatientDisease findCustomerIdByPatientDisease(Long id);

	public int verificationDoctor(Long cid);
	
	/**
	 *根据用户电话获取医生个人信息(前提是他是医生)
	 */
	public Doctors getDoctorByCustomerId(Long customerId);
	/**
	 *根据用户电话获取医生个人信息(前提是他是医生)
	 */
	public Map<String, Object> getDoctorServiceTimeByDoctorId(Long doctorId);
	
	/**
	 *获取所有城市
	 */
	public List<Map<String, Object>> findAllPro();
	/**
	 *获取服务包类型
	 */
	public List<Map<String, Object>> findProductType();
	
	/**
	 *根据医生id获取医生服务是否开启
	 */
	public Integer getDoctorServiceStats(Integer doctorId);

	public Doctors getDoctorById(Long doctorId);
	/**
	 *根据城市id获取城市名称
	 */
	public String getPatientProvById(Long id);
}

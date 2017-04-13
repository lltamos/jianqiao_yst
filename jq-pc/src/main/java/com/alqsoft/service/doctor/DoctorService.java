package com.alqsoft.service.doctor;

import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.PatientHistory;
import com.alqsoft.entity.doctor.DoctorInfo;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.patient.PatientDisease;

public interface DoctorService {
	public  List<Map<String, Object>> getDoctorIndx();
	/**
	 * 名医高手页面
	 * @param params
	 * @return
	 */
	public List<DoctorInfo> doctorPage(Map<String, Object> params);
	
	/**
	 *根据id获取医生个人信息的展示
	 */
	public DoctorInfo getDoctorInfoById(Long id);
	
	/**
	 * 医生详情中的相册
	 */
	public List<Map<String, Object>> doctorImages(Map<String, Object> params);
	
	/**
	 * 医生评价日记
	 */
	public List<Map<String, Object>> doctorDiary(Map<String, Object> params);
	/**
	 * 根据城市和科室获取城市列表
	 */
	public List<DoctorInfo> findDoctorByCityName(Map<String, Object> params);
	/**
	 * 获取患者求医列表
	 * @param map
	 * @return
	 */
	public  List<PatientHistory> findDoctor(Map<String, Object> map);
	
	
	/**
	 * 医生认证
	 * @param doctor
	 * @param customer 
	 * @return
	 */
	public Result doctorCertification(String aids,Doctors doctor, Customer customer);
	
	/**
	 * 获取医生总数
	 * @return
	 */
	public  Integer getDoctorTotal();
	/**
	 * 获取所有患者求医记录总数
	 * @return
	 */
	public Integer getPatientTotal();
	/**
	 * 获取，根据城市和科室的记录总数
	 */
	public Integer getDoctorByCitNameTotal();
	/**
	 * 获取，根据医生id的获取日记总数
	 */
	public Integer findDiaryByDoctorIdTotal(Long id);
	
	/**
	 * 根据患者求医ID查找cutomerId
	 */
	public PatientDisease findCustomerIdByPatientDisease(Long id);
	
	public Result verificationDoctor(Long cid);
	
	/**
	 *根据用户id获取医生个人信息(前提是他是医生)
	 */
	public Doctors getDoctorByCustomerId(Long customerId);
	/**
	 *根据用户id获取医生个人信息(前提是他是医生)
	 */
	public Map<String, Object> getDoctorServiceTimeByDoctorId(Long doctorId);
	
	/**
	 *根据id获取医生的doctor_service_order价格
	 */
	public Integer getDoctorOrderPriceById(Long doctorId);
	
	/**
	 *获取所有城市
	 */
	public List<Map<String, Object>> findAllPro();
	/**
	 *获取服务包类型
	 */
	public List<Map<String, Object>> findProductType();
	/**
	 * 
	* @Title: findByCid 
	* @Description: 根据customerid 查询医生
	* @return Doctors    返回类型 
	* @author 腾卉 
	* @throws
	 */
	public Doctors findByCid(Long customer_id);
	
	public Doctors getDoctorById(Long doctorId); 
	/**
	 *根据医生id获取医生服务是否开启
	 */
	public Integer getDoctorServiceStats(Integer doctorId);
	
}

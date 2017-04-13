package com.yst.web.service;

import java.util.List;

import org.alqframework.result.Result;

import com.yst.web.model.AppResult;
import com.yst.web.model.Doctor;

public interface DoctorService {
	public Doctor findById(int id);
	public AppResult add(Doctor doctor);
	public AppResult updateApprovalDoctor(Doctor doctor);
	public AppResult updateDoctorInfo(Doctor doctor);
	public AppResult addOnlineTimeInfo(Doctor doctor);
	public List<Doctor> selectDoctorListByParame();
	public List<Doctor> queryDoctorByParame(Doctor doctor);
	public AppResult addDoctorApply(Doctor doctor);
	public AppResult queryDoctorAppealStatusByColumn(Doctor doctor);
	public AppResult searchDic(String dics);//数据字典
	public AppResult getDoctorList(String latitude2,String longitude2,Integer type);
	public AppResult getDoctorDetails(Integer doctor_id);
	public AppResult findDoctorOrderBySpecList(Doctor doctor,String order_parame, String latit, String longit);
	public AppResult getDoctorByParam(Doctor doctor);
	public AppResult getMerchantDoctorList(Integer merchant_id);
	public AppResult getDoctorIdByHuanXinId(String haunxin_id);
	
	public Result findDoctorAll();
	public Result getDoctorListAll(Integer page, Integer rows);
	public Result getOneDoctorDetails(Integer doctor_id);
	public Result getDoctorComment(Integer doctor_id, Integer page, Integer rows);
	public Result saveDoctorComment(Integer doctor_id, Integer customer_id, Integer service_star, String customer_comment);
	//public Result getDoctorListByProductTypeId(Integer productTypeId, Integer page, Integer rows);
	public Result getDic(String dics);
	/**
	 * 获取医生分页列表
	 * @return
	 */
	public AppResult findDoctorListPage();
}
 
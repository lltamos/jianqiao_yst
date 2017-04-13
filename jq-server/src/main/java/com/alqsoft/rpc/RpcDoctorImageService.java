package com.alqsoft.rpc;

import java.util.List;

import org.alqframework.result.Result;
import com.alqsoft.entity.DoctorImage;
import com.alqsoft.entity.doctor.Doctors;

public interface RpcDoctorImageService {

	
	/**
	 * 保存图文详情
	 * @param doctorImage
	 * @return
	 */
	public Result saveDoctorImage(DoctorImage doctorImage);
	
	/**
	 * 
	* @Title: findById  
	* @Description: 根据医生id查看图文介绍
	* @author   腾卉  
	* @return DoctorImage    返回类型
	 */
	public List<DoctorImage> findById(Long doctorId);

	/**
	 * 
	* @Title: updateDoctorImage  
	* @Description: 修改图文介绍
	* @author   腾卉 
	* @return String    返回类型
	 */
	public Result updateDoctorImage(DoctorImage doctorImage);
	
	/**
	 * 
	* @Title: findByDoctorId  
	* @Description: 根据医生id查询医生信息
	* @author   腾卉 
	* @return Doctors    返回类型
	 */
	public Doctors findByDoctorId(Long doctorid);
	
}

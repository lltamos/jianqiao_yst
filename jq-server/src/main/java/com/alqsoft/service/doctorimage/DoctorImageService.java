package com.alqsoft.service.doctorimage;

import java.util.List;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.DoctorImage;
import com.alqsoft.utils.BootStrapResult;

public interface DoctorImageService extends BaseService<DoctorImage>{
	
	/**
	 * 
	* @Title: findById  
	* @Description: 根据医生id查找图文介绍
	* @author   腾卉  
	* @return DoctorImage    返回类型
	 */
	public List<DoctorImage> findById(Long doctorId);
	
	/**
	 * 医生图文详情列表
	 * @param doctorid
	 * @param page
	 * @param length
	 * @return
	 */
	public BootStrapResult<List<DoctorImage>> findDoctorImageByPage(String doctorid, Integer page, Integer length);

	/**
	 * 保存图文详情
	 * @param doctorImage
	 * @return
	 */
	public Result saveDoctorImage(DoctorImage doctorImage);

	/**
	 * 删除图文详情
	 * @param doctorimageid
	 * @return
	 */
	public Result deleteDoctorImage(Integer doctorimageid);
	
	
}

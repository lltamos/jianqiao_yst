package com.alqsoft.service.doctor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.utils.BootStrapResult;

public interface DoctorService extends BaseService<Doctors>{

	/**
	 * 医生列表分页
	 * @param doctorname
	 * @return
	 */
	public BootStrapResult<List<Doctors>> findDoctorByPage(String doctorname,Long status,Integer start,Integer length);

	/**
	 * 医生申请
	 * @param doctor
	 * @return
	 */
	public Result doctorApply(Doctors doctor,MultipartFile[] imageDoctor);

	/**
	 * 按照id获取医生信息
	 * @param doctorid
	 * @return
	 */
	public Doctors findByDoctorId(Long doctorid);

	/**
	 * 修改医生
	 * @param doctor
	 * @return
	 */
	public Result doctorUpdate(Doctors doctor);

	/**
	 * 医生审核
	 * @param doctorid
	 * @param verify
	 * @return
	 */
	public Result doctorApproval(Long doctorid, Integer verify);
	/**
	 * 查询医生列表
	 * @param request
	 * @return
	 */
	public List<Doctors> findSelect(HttpServletRequest request);
	
	public Doctors getDoctorByCustomerID(Long id);

	public Result saveApplyDoctor(Doctors doctor);

	public BootStrapResult<List<Doctors>> findApplyDoctorByPage(String doctorphone,Integer applyStatus, Integer page, Integer length);

	public Result examineDoctor(Integer apply, Long id, String reason);

	public Result doctorDisable(Long doctorid);


}

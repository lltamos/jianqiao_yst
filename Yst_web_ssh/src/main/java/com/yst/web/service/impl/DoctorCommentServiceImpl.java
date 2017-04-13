package com.yst.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.doctor.DoctorCommentDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.Doctor;
import com.yst.web.model.DoctorComment;
import com.yst.web.model.PageModel;
import com.yst.web.service.DoctorCommentService;
import com.yst.web.utils.PageModelContext;
@Service("doctorCommentService")
@Transactional
public class DoctorCommentServiceImpl implements DoctorCommentService{
	private static Log logger = LogFactory.getLog(DoctorCommentServiceImpl.class);
	@Resource(name = "doctorCommentDao")
	private DoctorCommentDao doctorCommentDao;
	@Override
	public AppResult getDoctorCommentList(DoctorComment doctorComment) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer doctor_id = doctorComment.getDoctorId();
		if(doctor_id!=null || !"".equals(doctor_id)){
		String hql = "from DoctorComment as o where doctor.doctor_id=?";
		PageModel pm =PageModelContext.getPageModel();
		List<DoctorComment> list = this.doctorCommentDao.query(hql, pm, doctor_id);
		int count = this.doctorCommentDao.count(DoctorComment.class);
		int length = list.size();
		if(length>0){
			/*for (int i = 0; i < length; i++) {
				DoctorComment dc = list.get(i);
				dc.setCustomer_phone(dc.getCustomer().getPhone());
				dc.setCustomer_age(dc.getCustomer().getAge());
				dc.setCustomer_sex(dc.getCustomer().getSex());
			}*/
			appResult.setDoctor_id(doctor_id);
			appResult.setData(list);
			appResult.setPage_model(pm);
			appResult.setError_info("获取列表成功");
			appResult.setResult(AppResult.SUCCESS);
			
		}else{
			appResult.setError_info("所属医生无评价信息");
		}
		}else{
			appResult.setError_info("医生id不能为空");
		}
		return appResult;
	}
	
	@Override
	public AppResult addDoctorComment(DoctorComment doctorComment) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer doctor_id = doctorComment.getDoctorId();
		Integer customer_id = doctorComment.getCustomer_id();
		if(doctor_id!=null&&customer_id!=null){
			Doctor doctor = this.doctorCommentDao.findByColumnValue(Doctor.class, "doctor_id", doctor_id);
			Customer customer = this.doctorCommentDao.findByColumnValue(Customer.class, "customer_id", customer_id);
			if(doctor!=null&&customer!=null){
				doctorComment.setComment_date(new Date(System.currentTimeMillis()));
				doctorComment.setDoctorId(doctor_id);
				doctorComment.setCustomer_id(customer_id);
				this.doctorCommentDao.save(doctorComment);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("医生或用户不存在");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("医生id或用户id不能为空");
			appResult.setData("");
		}
		return appResult;
	}

}

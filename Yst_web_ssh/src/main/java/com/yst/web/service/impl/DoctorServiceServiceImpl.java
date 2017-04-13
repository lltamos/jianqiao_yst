package com.yst.web.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.yst.web.dao.CustomerDao;
import com.yst.web.dao.doctor.DoctorDao;
import com.yst.web.dao.doctor.DoctorServiceDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.Doctor;
import com.yst.web.model.DoctorService;
import com.yst.web.service.DoctorServiceServcie;
import com.yst.web.utils.BeanUtils;
@Service("doctorServiceService")
@Transactional
public class DoctorServiceServiceImpl implements DoctorServiceServcie{
	private static Log logger = LogFactory.getLog(DoctorServiceServiceImpl.class);
	@Resource(name = "doctorServiceDao")
	DoctorServiceDao doctorServiceDao;
	@Resource(name = "customerDao")
	private CustomerDao customerDao;
	@Resource(name= "doctorDao")
	private DoctorDao doctorDao;
	
	@Override
	public AppResult addServiceInfo(DoctorService doctorService) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);
		appResult.setResult(AppResult.FAILED);
		if(StringUtils.isEmptyOrWhitespaceOnly(doctorService.getPhone())){
			appResult.setError_info("手机号为空");
		}else{
			Customer customer = this.customerDao.findByColumnValue(Customer.class, "phone", doctorService.getPhone());
			if(customer==null){
				appResult.setError_info("所属手机号的用户不存在");
			}else{
				Doctor doctor = this.doctorDao.findByColumnValue(Doctor.class, "customer.customer_id",customer.getCustomer_id());
				if(customer!=null){
					if(doctor==null){
						appResult.setError_info("该手机用户不是医生");
					}else{
						Integer doctor_service_id = doctorService.getDoctor_service_id();
						if(doctor_service_id!=null){
							DoctorService ds = this.doctorServiceDao.findByColumnValue(DoctorService.class, "doctor_service_id", doctor_service_id);
							if(ds!=null){
								doctorService.setDoctor(doctor);
								BeanUtils.copy(doctorService, ds);
								this.doctorServiceDao.saveOrUpdate(ds);
							}
						}else{
							doctorService.setDoctor(doctor);
							this.doctorServiceDao.saveOrUpdate(doctorService);
						}
						appResult.setResult(AppResult.SUCCESS);
					}
				}else{
					appResult.setError_info("手机号错误或没有该手机号");
				}
			}
			
		}
		return appResult;
	}

	@Override
	public AppResult getDoctorService(Integer doctor_id) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);
		appResult.setResult(AppResult.FAILED);
		if(doctor_id!=null&&!"".equals(doctor_id)){
			DoctorService doctorService = this.doctorServiceDao.findByColumnValue(DoctorService.class, "doctor.doctor_id", doctor_id);
			if(doctorService!=null){
				appResult.setData(doctorService);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("所属医生无服务设置信息");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("医生id不能为空");
			appResult.setData("");
		}
		return appResult;
	}


}

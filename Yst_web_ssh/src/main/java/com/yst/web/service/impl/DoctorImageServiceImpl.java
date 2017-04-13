package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.doctor.DoctorImageDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Doctor;
import com.yst.web.model.DoctorImage;
import com.yst.web.model.PageModel;
import com.yst.web.service.DoctorImageService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.PageModelContext;
@Service("doctorImageService")
@Transactional
public class DoctorImageServiceImpl implements DoctorImageService{
	private static Log logger = LogFactory.getLog(DoctorImageServiceImpl.class);
	@Resource(name ="doctorImageDao")
	private DoctorImageDao doctorImageDao;
	
	@Override
	public AppResult findDoctorImageList(Integer doctor_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(doctor_id!=null){
			Doctor doctor = this.doctorImageDao.findByColumnValue(Doctor.class, "doctor_id", doctor_id);
			if(doctor!=null){
				//List<DoctorImage> list = this.doctorImageDao.selectByColumnValue(DoctorImage.class, "doctor.doctor_id", doctor_id);
				String hql = "from DoctorImage as di where doctor.doctor_id=?";
				List<DoctorImage> list = this.doctorImageDao.query(hql, null, doctor_id);
				int length = list.size();
				if(length>0){
					for (int i = 0; i < length; i++) {
						DoctorImage doctorImage = list.get(i);
						doctorImage.setStr_doctor(doctorImage.getDoctor().getName());
					}
					appResult.setData(list);
					appResult.setResult(AppResult.SUCCESS);
				}else{
					appResult.setError_info("所属医生没有医生图片信息");
					appResult.setData("");
				}
			}else{
				appResult.setError_info("医生不存在");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("医生id不能为空");
			appResult.setData("");
		}
		return appResult;
	}

	@Override
	public AppResult addDoctorImage(DoctorImage doctorImage) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer doctor_id = doctorImage.getDoctor_id();
		if(doctor_id!=null){
			Doctor doctor = this.doctorImageDao.findByColumnValue(Doctor.class, "doctor_id", doctor_id);
			if(doctor!=null){
				Doctor d = new Doctor();
				d.setDoctor_id(doctorImage.getDoctor_id());
				doctorImage.setDoctor(d);
				appResult = BeanUtils.uploadImage(doctorImage, "doctorImage");
				if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
					if (appResult.getResult().equals(AppResult.FAILED)) {
						return appResult;
					}
				}
				this.doctorImageDao.save(doctorImage);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("所属医生不存在");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("医生id不能为空");
			appResult.setData("");
		}
		return appResult;
	}

	@Override
	public AppResult deleteDoctorImage(Integer image_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(image_id!=null){
			DoctorImage doctorImage = this.doctorImageDao.findByColumnValue(DoctorImage.class, "doctor_image_id", image_id);
			if(doctorImage!=null){
				this.doctorImageDao.delete(DoctorImage.class, image_id);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("医生图片不存在");
				appResult.setData("");
			}
			
		}else{
			appResult.setError_info("医生图片id不能为空");
			appResult.setData("");
		}
		return appResult;
	}

	@Override
	public DoctorImage getDoctorImage(Integer image_id) {
		DoctorImage doctorImage = this.doctorImageDao.findByColumnValue(DoctorImage.class, "doctor_image_id", image_id);
		return doctorImage;
	}

	@Override
	public AppResult updateDoctorImage(DoctorImage doctorImage) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(doctorImage!=null){
			DoctorImage di  = this.doctorImageDao.findByColumnValue(DoctorImage.class, "doctor_image_id", doctorImage.getDoctor_image_id());
			if(di!=null){
				Integer doctor_id = doctorImage.getDoctor_id();
				if(doctor_id!=null){
					Doctor doctor =	this.doctorImageDao.findByColumnValue(Doctor.class, "doctor_id", doctor_id);
					if(doctor!=null){
						appResult = BeanUtils.uploadImage(doctorImage, "doctorImage");
						if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
							if (appResult.getResult().equals(AppResult.FAILED)) {
								return appResult;
							}
						}
						di.setDoctor(doctor);
						BeanUtils.copy(doctorImage, di);
						this.doctorImageDao.update(di);
						appResult.setResult(AppResult.SUCCESS);
					}else{
						appResult.setError_info("所属医生不存在");
						appResult.setData("");
					}
				}else{
					appResult.setError_info("医生id不能为空");
					appResult.setData("");
				}
			}else{
				appResult.setError_info("图片id不能为空");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("医生图片信息不能为空");
			appResult.setData("");
		}
		return appResult;
	}

}

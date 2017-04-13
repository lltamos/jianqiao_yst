package com.alqsoft.rpc.impl;


import java.util.List;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.doctorimage.DoctorImageDao;
import com.alqsoft.entity.DoctorImage;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.rpc.RpcDoctorImageService;
import com.alqsoft.service.doctor.DoctorService;

@Service
@Transactional
public class RpcDoctorImageServiceImpl implements RpcDoctorImageService {


	@Autowired
	private DoctorImageDao doctorImageDao;
	
	@Autowired
	private DoctorService doctorService;
	
	
	@Override
	public Result saveDoctorImage(DoctorImage doctorImage) {
		Result result = new Result();
		Doctors doctors = doctorImageDao.getDoctorImageByDoctorId(new Long(doctorImage.getDoctorId()));
		String des = doctorImage.getDes();
		String image = doctorImage.getImage();
		if( doctors == null ){
			result.setCode(4);
			result.setMsg("所属医生不存在!");
			return result;
		}
		if ( StringUtils.isBlank(des) ) {
			result.setCode(3);
			result.setMsg("描述不能为空!");
			return result;
		}
		if ( StringUtils.isBlank(image) ) {
			result.setCode(2);
			result.setMsg("图片不能为空!");
			return result;
		}
		doctorImage.setDoctorName(doctors.getName());
		doctorImageDao.save(doctorImage);
		result.setCode(1);
		result.setMsg("保存成功！");
		return result;
	}
	


	@Override
	public Result updateDoctorImage(DoctorImage doctorImage) {
		Long doctorId2 = doctorImage.getDoctorId();
		String doctorName = doctorImage.getDoctorName();
		Long id = doctorImage.getId();
		String des = doctorImage.getDes();
		String image = doctorImage.getImage();
		if ( StringUtils.isBlank(des) ) {
			return ResultUtils.returnError("描述不能为空!");
		}
		if ( StringUtils.isBlank(image) ) {
			return ResultUtils.returnError("图片不能为空!");
		}
		DoctorImage findOne = doctorImageDao.findOne(id);
		findOne.setDoctorId(doctorId2);
		findOne.setDoctorName(doctorName);
		findOne.setDes(des);
		findOne.setImage(image);
		doctorImageDao.save(findOne);
		return ResultUtils.returnError("保存成功!");
	}



	@Override
	public List<DoctorImage> findById(Long doctorId) {
		List<DoctorImage> doctorImageList = doctorImageDao.findById(doctorId);
		return doctorImageList;
	}



	@Override
	public Doctors findByDoctorId(Long doctorid) {
		Doctors doctors = doctorService.findByDoctorId(doctorid);
		return doctors;
	}

	
}

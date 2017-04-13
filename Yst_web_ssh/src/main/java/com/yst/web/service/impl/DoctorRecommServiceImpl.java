package com.yst.web.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.doctor.DoctorRecommDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Doctor;
import com.yst.web.model.DoctorRecomm;
import com.yst.web.service.DoctorRecommService;
@Service("doctorRecommService")
@Transactional
public class DoctorRecommServiceImpl implements DoctorRecommService{
	private static Log logger = LogFactory.getLog(DoctorRecommServiceImpl.class);
	@Resource(name = "doctorRecommDao")
	private DoctorRecommDao doctorRecommDao;
	
	@Override
	public AppResult getHomeDoctorList() {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		//DoctorRecomm d =
		DoctorRecomm doctorRecomm = this.doctorRecommDao.findDoctorRecomm();
		
		Integer hd1 = doctorRecomm.getHome_doctor_1();
		Integer hd2 = doctorRecomm.getHome_doctor_2();
		Integer hd3 = doctorRecomm.getHome_doctor_3();
		Integer hd4 = doctorRecomm.getHome_doctor_4();
		Integer hd5 = doctorRecomm.getHome_doctor_5();
		if(hd1!=null&&hd2!=null&&hd3!=null&&hd4!=null&&hd5!=null){
			//获取家庭医生
			Doctor homeDoctor1 =  this.doctorRecommDao.findByColumnValue(Doctor.class, "doctor_id",hd1 );
			homeDoctor1.setOffice_id(homeDoctor1.getDicOffice().getOffice_id());
			homeDoctor1.setTitle_id(homeDoctor1.getDicTitle().getTitle_id());
			homeDoctor1.setSpec_id(homeDoctor1.getDicSpec().getSpec_id());
			homeDoctor1.setHospital_type_id(homeDoctor1.getDicHospitalType().getHospital_type_id());
			homeDoctor1.setStr_office(homeDoctor1.getDicOffice().getName());
			homeDoctor1.setStr_title(homeDoctor1.getDicTitle().getName());
			homeDoctor1.setStr_spec(homeDoctor1.getDicSpec().getName());
			homeDoctor1.setStr_dicHospitalType(homeDoctor1.getDicHospitalType().getName());
			
			Doctor homeDoctor2 =  this.doctorRecommDao.findByColumnValue(Doctor.class, "doctor_id",hd2 );
			homeDoctor2.setOffice_id(homeDoctor2.getDicOffice().getOffice_id());
			homeDoctor2.setTitle_id(homeDoctor2.getDicTitle().getTitle_id());
			homeDoctor2.setSpec_id(homeDoctor2.getDicSpec().getSpec_id());
			homeDoctor2.setHospital_type_id(homeDoctor2.getDicHospitalType().getHospital_type_id());
			homeDoctor2.setStr_office(homeDoctor2.getDicOffice().getName());
			homeDoctor2.setStr_title(homeDoctor2.getDicTitle().getName());
			homeDoctor2.setStr_spec(homeDoctor2.getDicSpec().getName());
			homeDoctor2.setStr_dicHospitalType(homeDoctor2.getDicHospitalType().getName());
			
			Doctor homeDoctor3 =  this.doctorRecommDao.findByColumnValue(Doctor.class, "doctor_id",hd3 );
			homeDoctor3.setOffice_id(homeDoctor3.getDicOffice().getOffice_id());
			homeDoctor3.setTitle_id(homeDoctor3.getDicTitle().getTitle_id());
			homeDoctor3.setSpec_id(homeDoctor3.getDicSpec().getSpec_id());
			homeDoctor3.setHospital_type_id(homeDoctor3.getDicHospitalType().getHospital_type_id());
			homeDoctor3.setStr_office(homeDoctor3.getDicOffice().getName());
			homeDoctor3.setStr_title(homeDoctor3.getDicTitle().getName());
			homeDoctor3.setStr_spec(homeDoctor3.getDicSpec().getName());
			homeDoctor3.setStr_dicHospitalType(homeDoctor3.getDicHospitalType().getName());
			
			Doctor homeDoctor4 =  this.doctorRecommDao.findByColumnValue(Doctor.class, "doctor_id",hd4 );
			homeDoctor4.setOffice_id(homeDoctor4.getDicOffice().getOffice_id());
			homeDoctor4.setTitle_id(homeDoctor4.getDicTitle().getTitle_id());
			homeDoctor4.setSpec_id(homeDoctor4.getDicSpec().getSpec_id());
			homeDoctor4.setHospital_type_id(homeDoctor4.getDicHospitalType().getHospital_type_id());
			homeDoctor4.setStr_office(homeDoctor4.getDicOffice().getName());
			homeDoctor4.setStr_title(homeDoctor4.getDicTitle().getName());
			homeDoctor4.setStr_spec(homeDoctor4.getDicSpec().getName());
			homeDoctor4.setStr_dicHospitalType(homeDoctor4.getDicHospitalType().getName());
			
			Doctor homeDoctor5 =  this.doctorRecommDao.findByColumnValue(Doctor.class, "doctor_id",hd5 );
			homeDoctor5.setOffice_id(homeDoctor5.getDicOffice().getOffice_id());
			homeDoctor5.setTitle_id(homeDoctor5.getDicTitle().getTitle_id());
			homeDoctor5.setSpec_id(homeDoctor5.getDicSpec().getSpec_id());
			homeDoctor5.setHospital_type_id(homeDoctor5.getDicHospitalType().getHospital_type_id());
			homeDoctor5.setStr_office(homeDoctor5.getDicOffice().getName());
			homeDoctor5.setStr_title(homeDoctor5.getDicTitle().getName());
			homeDoctor5.setStr_spec(homeDoctor5.getDicSpec().getName());
			homeDoctor5.setStr_dicHospitalType(homeDoctor5.getDicHospitalType().getName());
			Object[] obj = new Object[]{homeDoctor1,homeDoctor2,homeDoctor3,homeDoctor4,homeDoctor5};
			appResult.setData(obj);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setError_info("家庭医生id不能为空");
			appResult.setData("");
		}
		return appResult;
	}

	
	@Override
	public AppResult getSpecDoctorList() {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		DoctorRecomm doctorRecomm = this.doctorRecommDao.findDoctorRecomm();
		Integer sd1 = doctorRecomm.getSpec_doctor_1();
		Integer sd2 = doctorRecomm.getSpec_doctor_2();
		Integer sd3 = doctorRecomm.getSpec_doctor_3();
		Integer sd4 = doctorRecomm.getSpec_doctor_4();
		Integer sd5 = doctorRecomm.getSpec_doctor_5();
		if(sd1!=null&&sd2!=null&&sd3!=null&&sd4!=null&&sd5!=null){
			//获取疑难杂症医生  
			Doctor specDoctor1 =  this.doctorRecommDao.findByColumnValue(Doctor.class, "doctor_id",sd1 );
			specDoctor1.setOffice_id(specDoctor1.getDicOffice().getOffice_id());
			specDoctor1.setTitle_id(specDoctor1.getDicTitle().getTitle_id());
			specDoctor1.setSpec_id(specDoctor1.getDicSpec().getSpec_id());
			specDoctor1.setHospital_type_id(specDoctor1.getDicHospitalType().getHospital_type_id());
			specDoctor1.setStr_office(specDoctor1.getDicOffice().getName());
			specDoctor1.setStr_title(specDoctor1.getDicTitle().getName());
			specDoctor1.setStr_spec(specDoctor1.getDicSpec().getName());
			specDoctor1.setStr_dicHospitalType(specDoctor1.getDicHospitalType().getName());
			
			Doctor specDoctor2 =  this.doctorRecommDao.findByColumnValue(Doctor.class, "doctor_id",sd2 );
			specDoctor2.setOffice_id(specDoctor2.getDicOffice().getOffice_id());
			specDoctor2.setTitle_id(specDoctor2.getDicTitle().getTitle_id());
			specDoctor2.setSpec_id(specDoctor2.getDicSpec().getSpec_id());
			specDoctor2.setHospital_type_id(specDoctor2.getDicHospitalType().getHospital_type_id());
			specDoctor2.setStr_office(specDoctor2.getDicOffice().getName());
			specDoctor2.setStr_title(specDoctor2.getDicTitle().getName());
			specDoctor2.setStr_spec(specDoctor2.getDicSpec().getName());
			specDoctor2.setStr_dicHospitalType(specDoctor2.getDicHospitalType().getName());
			
			Doctor specDoctor3 =  this.doctorRecommDao.findByColumnValue(Doctor.class, "doctor_id",sd3 );
			specDoctor3.setOffice_id(specDoctor3.getDicOffice().getOffice_id());
			specDoctor3.setTitle_id(specDoctor3.getDicTitle().getTitle_id());
			specDoctor3.setSpec_id(specDoctor3.getDicSpec().getSpec_id());
			specDoctor3.setHospital_type_id(specDoctor3.getDicHospitalType().getHospital_type_id());
			specDoctor3.setStr_office(specDoctor3.getDicOffice().getName());
			specDoctor3.setStr_title(specDoctor3.getDicTitle().getName());
			specDoctor3.setStr_spec(specDoctor3.getDicSpec().getName());
			specDoctor3.setStr_dicHospitalType(specDoctor3.getDicHospitalType().getName());
			
			Doctor specDoctor4 =  this.doctorRecommDao.findByColumnValue(Doctor.class, "doctor_id",sd4 );
			specDoctor4.setOffice_id(specDoctor4.getDicOffice().getOffice_id());
			specDoctor4.setTitle_id(specDoctor4.getDicTitle().getTitle_id());
			specDoctor4.setSpec_id(specDoctor4.getDicSpec().getSpec_id());
			specDoctor4.setHospital_type_id(specDoctor4.getDicHospitalType().getHospital_type_id());
			specDoctor4.setStr_office(specDoctor4.getDicOffice().getName());
			specDoctor4.setStr_title(specDoctor4.getDicTitle().getName());
			specDoctor4.setStr_spec(specDoctor4.getDicSpec().getName());
			specDoctor4.setStr_dicHospitalType(specDoctor4.getDicHospitalType().getName());
			
			Doctor specDoctor5 =  this.doctorRecommDao.findByColumnValue(Doctor.class, "doctor_id",sd5 );
			specDoctor5.setOffice_id(specDoctor5.getDicOffice().getOffice_id());
			specDoctor5.setTitle_id(specDoctor5.getDicTitle().getTitle_id());
			specDoctor5.setSpec_id(specDoctor5.getDicSpec().getSpec_id());
			specDoctor5.setHospital_type_id(specDoctor5.getDicHospitalType().getHospital_type_id());
			specDoctor5.setStr_office(specDoctor5.getDicOffice().getName());
			specDoctor5.setStr_title(specDoctor5.getDicTitle().getName());
			specDoctor5.setStr_spec(specDoctor5.getDicSpec().getName());
			specDoctor5.setStr_dicHospitalType(specDoctor5.getDicHospitalType().getName());
			Object[] obj = new Object[]{specDoctor1,specDoctor2,specDoctor3,specDoctor4,specDoctor5};
			appResult.setData(obj);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setError_info("疑难杂症id不能为空");
			appResult.setData("");
		}
		return appResult;
	}
	
}

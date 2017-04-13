package com.alqsoft.dao.consultation;

import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.DoctorService;
import com.alqsoft.entity.DoctorServiceTime;

@MyBatisRepository
public interface ConsultationDao {

	Map<String, Object> findDoctor(Map<String, Object> param);
	
	Map<String, Object> findCustomer(Map<String, Object> param);

	Map<String, Object> findPhone(Map<String, Object> param);

	Integer getDoctorServiceTime(String doctorId);

	DoctorServiceTime getDoctorServiceDayTime(String doctorId);

}

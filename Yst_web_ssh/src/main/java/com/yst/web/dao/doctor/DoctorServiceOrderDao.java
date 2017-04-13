package com.yst.web.dao.doctor;

import java.util.Map;

import com.yst.web.model.DoctorService;
import com.yst.web.model.DoctorServiceOrder;
import com.yst.web.utils.BaseDao;

public interface DoctorServiceOrderDao extends BaseDao{
	public DoctorServiceOrder findDoctorServiceOrderByParam(int customer_id, int doctor_id);
	public void updateDoctorServiceOrder(Integer doctor_id,DoctorService doctorService);
	public Map<String, Object> getDoctorConsultTime(Integer customer_id, Integer doctor_id, Integer service_type_id);
}

package com.alqsoft.rpc;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.DoctorService;
import com.alqsoft.entity.DoctorServiceTime;
import com.alqsoft.entity.doctor.Doctors;

public interface RpcDoctorServicesService extends BaseService<DoctorServiceTime>{
	
	public DoctorServiceTime upadteDoctorservice(DoctorServiceTime doctorServiceTime);
	
	public Doctors getDoctor(Long id); 
	public DoctorServiceTime getDoctorServiceTimeByCustomerId(Long id);
	public Boolean upadteDoctorServicePrice(Doctors doctors,Integer price1);
	public Result closeDoctorServicePrice(Long id);
	public DoctorService getPriceByDoctorId(Integer id);
}

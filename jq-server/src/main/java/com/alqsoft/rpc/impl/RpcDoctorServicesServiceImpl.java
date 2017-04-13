package com.alqsoft.rpc.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.doctor.DoctorDao;
import com.alqsoft.dao.doctorservice.DoctorServiceDao;
import com.alqsoft.dao.doctorservicetime.DoctorServiceTimeDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.DoctorServiceTime;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.rpc.RpcDoctorServicesService;
import com.alqsoft.service.doctor.DoctorService;

@Service
@Transactional
public class RpcDoctorServicesServiceImpl implements RpcDoctorServicesService {

	@Autowired
	private DoctorServiceTimeDao doctorServiceTimeDao;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private DoctorServiceDao doctorServiceDao;
	@Autowired
	private DoctorDao doctorDao;

	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		doctorServiceTimeDao.delete(arg0);
		return true;
	}

	@Override
	public DoctorServiceTime get(Long arg0) {
		// TODO Auto-generated method stub
		DoctorServiceTime doctorServiceTime = doctorServiceTimeDao.findOne(arg0);
		return doctorServiceTime;
	}

	@Override
	public DoctorServiceTime saveAndModify(DoctorServiceTime arg0) {
		// TODO Auto-generated method stub
		DoctorServiceTime doctorServiceTime = doctorServiceTimeDao.save(arg0);
		return doctorServiceTime;
	}

	@Override
	public DoctorServiceTime upadteDoctorservice(DoctorServiceTime doctorServiceTime) {
		// TODO Auto-generated method stub
		DoctorServiceTime doctorService = doctorServiceTimeDao.save(doctorServiceTime);
		return doctorService;
	}

	@Override
	public Doctors getDoctor(Long id) {
		// TODO Auto-generated method stub
		Doctors doctors = doctorService.getDoctorByCustomerID(id);
		return doctors;
	}

	@Override
	public DoctorServiceTime getDoctorServiceTimeByCustomerId(Long id) {
		DoctorServiceTime doctorServiceTimeByCustomer = doctorServiceTimeDao.getDoctorServiceTimeByCustomer(id);

		return doctorServiceTimeByCustomer;
	}

	@Override
	public Boolean upadteDoctorServicePrice(Doctors doctors, Integer price1) {
		com.alqsoft.entity.DoctorService doctorService1 = doctorServiceDao
				.getDoctorServiceByDoctorId(Integer.parseInt(doctors.getId().toString()));
		Integer doctorId = Integer.parseInt(doctors.getId().toString());
		int res = doctorServiceDao.closeDoctorService(doctorId, 0);
		if (doctorService1 != null) {
			doctorService1.setService_online_time_price(price1);
			com.alqsoft.entity.DoctorService save = doctorServiceDao.save(doctorService1);
			if (save != null && res > 0) {
				return true;
			}
			return false;
		} else {
			com.alqsoft.entity.DoctorService doctorService2 = new com.alqsoft.entity.DoctorService();
			doctorService2.setCreatedTime(new Date());
			doctorService2.setDoctor_id(Integer.parseInt(doctors.getId().toString()));
			doctorService2.setService_online_time_price(price1);
			com.alqsoft.entity.DoctorService save2 = doctorServiceDao.save(doctorService2);
			if (save2 != null && res > 0) {
				return true;
			}
			return false;
		}
	}

	@Override
	public com.alqsoft.entity.DoctorService getPriceByDoctorId(Integer id) {
		com.alqsoft.entity.DoctorService doctorServiceByDoctorId = doctorServiceDao.getDoctorServiceByDoctorId(id);
		return (com.alqsoft.entity.DoctorService) doctorServiceByDoctorId;
	}

	@Override
	public Result closeDoctorServicePrice(Long id) {
		com.alqsoft.entity.DoctorService doctorService1 = new com.alqsoft.entity.DoctorService();
		Result result = new Result();
		String doctorId = id.toString();
		com.alqsoft.entity.DoctorService doctorService = doctorServiceDao
				.getDoctorServiceByDoctorId(Integer.parseInt(doctorId));
		
		if(doctorService ==null){
			com.alqsoft.entity.DoctorService save = doctorServiceDao.save(doctorService1);
			/* int closeDoctorService = doctorServiceDao.closeDoctorService(Integer.parseInt(doctorId), 1);*/
			save.setDoctor_id(Integer.parseInt(doctorId));
			save.setService_online_time_onoff(1);
			com.alqsoft.entity.DoctorService doctorService2 = doctorServiceDao.save(save);
			 if(doctorService2 !=null){
				result.setCode(1);
				result.setContent("关闭服务成功!");
				return result;
			 }
		}
		
		if (null == doctorService.getService_online_time_onoff() || doctorService.getService_online_time_onoff() == 1) {
			int res = doctorServiceDao.closeDoctorService(Integer.parseInt(doctorId), 0);
			if (res > 0) {
				result.setCode(1);
				result.setContent("开启服务成功!");
			} else {
				result.setCode(0);
				result.setContent("开启服务失败!");
			}
		}
		if (doctorService.getService_online_time_onoff() == 0) {
			int res = doctorServiceDao.closeDoctorService(Integer.parseInt(doctorId), 1);
			if (res > 0) {
				result.setCode(1);
				result.setContent("关闭服务成功!");
			} else {
				result.setCode(0);
				result.setContent("关闭服务失败!");
			}
		}
		return result;
	}

}

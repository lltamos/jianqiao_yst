package com.alqsoft.service.impl.doctorserver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.doctorservice.DoctorServiceDao;
import com.alqsoft.service.doctorserver.DoctorServerService;

@Service
@Transactional
public class DoctorServerServiceImpl implements DoctorServerService{
	
	@Autowired
	private DoctorServiceDao doctorServiceDao;

	@Override
	public Map<String, Object> getDoctorServicePrice(Long id) {
		// TODO Auto-generated method stub
		Map<String, Object> doctorServicePrice = doctorServiceDao.getDoctorServicePrice(id);
		return doctorServicePrice;
	}
	
	
}

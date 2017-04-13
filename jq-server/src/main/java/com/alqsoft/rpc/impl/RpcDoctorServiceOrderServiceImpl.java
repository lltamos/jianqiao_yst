package com.alqsoft.rpc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.doctorserviceorder.DoctorServiceOrderDao;
import com.alqsoft.entity.DoctorServiceOrder;
import com.alqsoft.rpc.RpcDoctorServiceOrderService;

@Service
@Transactional
public class RpcDoctorServiceOrderServiceImpl implements RpcDoctorServiceOrderService{
	
	@Autowired
	private DoctorServiceOrderDao doctorServiceOrderDao;

	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		doctorServiceOrderDao.delete(arg0);
		return true;
	}

	@Override
	public DoctorServiceOrder get(Long arg0) {
		// TODO Auto-generated method stub
		DoctorServiceOrder findOne = doctorServiceOrderDao.findOne(arg0);
		return findOne;
	}

	@Override
	public DoctorServiceOrder saveAndModify(DoctorServiceOrder arg0) {
		// TODO Auto-generated method stub
		return doctorServiceOrderDao.save(arg0);
	}

}

package com.alqsoft.rpc.impl;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.order.DoctorOrderDao;
import com.alqsoft.entity.DoctorServiceOrder;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.rpc.RpcDoctorOrderService;

@Service
public class RpcDoctorOrderServiceImpl implements RpcDoctorOrderService {

	@Autowired
	private DoctorOrderDao doctor;

	@Override
	public boolean delete(Long arg0) {
		try {
			doctor.delete(arg0);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public DoctorServiceOrder get(Long arg0) {

		return doctor.findOne(arg0);
	}

	@Override
	public DoctorServiceOrder saveAndModify(DoctorServiceOrder arg0) {	
		return doctor.save(arg0);
	}
	
	@Override
	public boolean lazyCensorOrder(Long id) {


		Timer t = new Timer();

		t.schedule(new TimerTask() {

			@Override
			public void run() {
				DoctorServiceOrder p = doctor.findOne(id);
				

				if (p!=null&&p.getPayStatus() == 0) {					
					doctor.delete(id);					
				}

			}
		}, 900000);

		return true;
	}

}

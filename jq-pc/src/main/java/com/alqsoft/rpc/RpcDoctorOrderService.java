package com.alqsoft.rpc;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.DoctorServiceOrder;

public interface RpcDoctorOrderService extends BaseService<DoctorServiceOrder> {
	
	public boolean lazyCensorOrder(Long id);
}

package com.alqsoft.rpc;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.DoctorServiceOrder;
import com.alqsoft.entity.ProductOrder;

public interface RpcDoctorOrderService extends BaseService<DoctorServiceOrder> {
	
	public boolean lazyCensorOrder(Long id);
}

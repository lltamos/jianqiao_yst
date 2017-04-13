package com.alqsoft.rpc;

import org.alqframework.orm.BaseService;
import org.alqframework.webmvc.springmvc.Result;

import com.alqsoft.entity.ProductOrder;

public interface RpcProductOrderService extends BaseService<ProductOrder>{
	
	public ProductOrder addProductOrder(ProductOrder p); 
	
	
	public boolean lazyCensorOrder(Long id);
}

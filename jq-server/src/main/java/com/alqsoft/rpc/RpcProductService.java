package com.alqsoft.rpc;

import com.alqsoft.entity.Product;

public interface RpcProductService {
	
	Product getProductById(Long id);
}

package com.yst.web.service;

import com.yst.web.model.AppResult;
import com.yst.web.model.CustomerAddress;

public interface CustomerAddressService {
	
	public AppResult findCustomerAddressList(Integer customer_id);
	public AppResult addCustomerAddress(CustomerAddress customerAddress);
	public AppResult updateCustomerAddress(CustomerAddress customerAddress);
	public AppResult deleteCustomerAddress(CustomerAddress customerAddress);
	public AppResult saveCustomerAddress(CustomerAddress customerAddress);
	public AppResult getAddressInfo(CustomerAddress customerAddress);
}

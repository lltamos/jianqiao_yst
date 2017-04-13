package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.CustomerAddressDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.CustomerAddress;
import com.yst.web.service.CustomerAddressService;
import com.yst.web.utils.BeanUtils;
@Service("customerAddressService")
@Transactional
public class CustomerAddressServiceImpl implements CustomerAddressService{

	@Resource(name="customerAddressDao")
	private CustomerAddressDao customerAddressDao;
	
	@Override
	public AppResult findCustomerAddressList(Integer customer_id) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);
		appResult.setResult(AppResult.FAILED);
		if(customer_id!=null){
			List<CustomerAddress> customerAddressList = this.customerAddressDao.selectByColumnValue(CustomerAddress.class, "customer_id", customer_id);
			if(customerAddressList.size()>0){
				appResult.setData(customerAddressList);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setData("");
				appResult.setError_info("所属用户没有地址信息");
			}
		}else{
			appResult.setData("");
			appResult.setError_info("用户id不能为空");
		}
		
		return appResult;
	}

	@Override
	public AppResult addCustomerAddress(CustomerAddress customerAddress) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);
		appResult.setResult(AppResult.FAILED);
		if(customerAddress.getCustomer_id()!=null){
			this.customerAddressDao.save(customerAddress);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setData("");
			appResult.setError_info("用户id不能为空");
		}
		return appResult;
	}

	@Override
	public AppResult updateCustomerAddress(CustomerAddress customerAddress) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);
		appResult.setResult(AppResult.FAILED);
		if(customerAddress.getId()!=null){
			CustomerAddress ca = this.customerAddressDao.findByColumnValue(CustomerAddress.class, "id", customerAddress.getId());
			if(ca!=null){
				if(customerAddress.getCustomer_id()!=null){
					Integer customer_id =ca.getCustomer_id();
					Customer dbCustomer =this.customerAddressDao.get(Customer.class, customer_id);
					if(dbCustomer==null){
						customerAddress.setCustomer_id(customer_id);
						BeanUtils.copy(customerAddress, ca);
						this.customerAddressDao.update(ca);
						appResult.setResult(AppResult.SUCCESS);
					}else{
						appResult.setData("");
						appResult.setError_info("所属用户不存在");
					}
				}else{
					appResult.setData("");
					appResult.setError_info("用户id不能为空");
				}
			}else{
				appResult.setData("");
				appResult.setError_info("所属地址不存在");
			}
		}else{
			appResult.setData("");
			appResult.setError_info("地址id不能为空");
		}
		return appResult;
	}

	@Override
	public AppResult deleteCustomerAddress(CustomerAddress customerAddress) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);
		appResult.setResult(AppResult.FAILED);
		Integer id = customerAddress.getId();
		if(id==null || id.equals("")){
			appResult.setError_info("地址id不能为空");
			return appResult;
		}
		Integer is_default =customerAddress.getIs_default();
		if(is_default==null || is_default.equals("")){
			appResult.setError_info("是否为默认不能为空");
			return appResult;
		}
		CustomerAddress dbCustomerAddress =this.customerAddressDao.get(CustomerAddress.class, id);
		if(dbCustomerAddress==null){
			appResult.setError_info("地址id不存在或错误");
			return appResult;
		}
		if(is_default==1){
			Integer customer_id =dbCustomerAddress.getCustomer_id();
			Customer dbCustomer =this.customerAddressDao.get(Customer.class, customer_id);
			if(dbCustomer==null){
				appResult.setError_info("这个地址不是默认地址");
				return appResult;
			}
			dbCustomer.setAddress_id(null);
			this.customerAddressDao.saveOrUpdate(dbCustomer);
		}
		this.customerAddressDao.delete(dbCustomerAddress);
		appResult.setResult(AppResult.SUCCESS);
		appResult.setError_info("删除成功");
		return appResult;
	}

	@Override
	public AppResult saveCustomerAddress(CustomerAddress customerAddress) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);
		appResult.setResult(AppResult.FAILED);
		Integer id = customerAddress.getId();
		Integer customer_id = customerAddress.getCustomer_id();
		if(id==null || id.equals("")){
			appResult.setError_info("地址id不能为空");
			return appResult;
		}
		if(customer_id==null || customer_id.equals("")){
			appResult.setError_info("用户id不能为空");
			return appResult;
		}
		CustomerAddress dbCustomerAddress =this.customerAddressDao.get(CustomerAddress.class, id);
		if(dbCustomerAddress==null){
			appResult.setError_info("地址id不存在错误");
			return appResult;
		}
		Customer dbCustomer =this.customerAddressDao.get(Customer.class, customer_id);
		if(dbCustomer==null){
			appResult.setError_info("用户id不存在或错误");
			return appResult;
		}
		dbCustomer.setAddress_id(id);
		this.customerAddressDao.saveOrUpdate(dbCustomer);
		appResult.setResult(AppResult.SUCCESS);
		appResult.setError_info("保存成功");
		return appResult;
	}

	@Override
	public AppResult getAddressInfo(CustomerAddress customerAddress) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);
		appResult.setResult(AppResult.FAILED);
		Integer id = customerAddress.getId();
		if(id==null || id.equals("")){
			appResult.setError_info("地址id不能为空");
			return appResult;
		}
		CustomerAddress dbCustomerAddress =this.customerAddressDao.get(CustomerAddress.class, id);
		if(dbCustomerAddress==null){
			appResult.setError_info("地址id不存在错误");
			return appResult;
		}
		appResult.setData(dbCustomerAddress);
		appResult.setResult(AppResult.SUCCESS);
		appResult.setError_info("获取成功");
		return appResult;
	}
}

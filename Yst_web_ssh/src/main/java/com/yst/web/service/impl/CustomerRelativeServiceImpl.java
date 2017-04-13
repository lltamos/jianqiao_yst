package com.yst.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.CustomerDao;
import com.yst.web.dao.CustomerRelativeDao;
import com.yst.web.entity.patient.Patient;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.CustomerIllnessRecord;
import com.yst.web.model.CustomerRelative;
import com.yst.web.model.RelativeMedicineRecord;
import com.yst.web.service.CustomerRelativeService;
@Service("customerRelativeService")
@Transactional
public class CustomerRelativeServiceImpl implements CustomerRelativeService{
	private static Log logger = LogFactory.getLog(CustomerRelativeServiceImpl.class);
	@Resource(name = "customerRelativeDao")
	private CustomerRelativeDao customerRelativeDao;
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public AppResult selectByParame(CustomerRelative customerRelative) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer customer_id = customerRelative.getCustomer_id();
		if(customer_id!=null || "".equals(customer_id)){
			List<CustomerRelative> list = this.customerRelativeDao.selectByColumnValue(CustomerRelative.class, "customer.customer_id", customerRelative.getCustomer_id());
			if(list.size()>0){
				Integer count = this.customerRelativeDao.count(CustomerRelative.class);
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("size", count);
				map.put("relatives", list);
				appResult.setData(map);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("所属用户无相关人员信息");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("客户id为空");
		}
		return appResult;
	}

	
	@Override
	public AppResult addCustomerRelative(CustomerRelative custoerRelative) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer customer_id = custoerRelative.getCustomer_id();
		if(customer_id!=null || !"".equals(customer_id)){
			Customer customer = this.customerRelativeDao.findByColumnValue(Customer.class, "customer_id", customer_id);
			if(customer==null){
				appResult.setError_info("所属客户不存在");
			}else{
				custoerRelative.setCustomer(customer);
				this.customerRelativeDao.save(custoerRelative);
				int relative_id = custoerRelative.getRelative_id();
				appResult.setData(relative_id);
				appResult.setResult(AppResult.SUCCESS);
			}
		}else{
			appResult.setError_info("客户ID不能为空");
		}
		return appResult;
	}


	@Override
	public AppResult deleteRelative(int customer_id, int relative_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(customer_id>0 || relative_id >0){
			String hqll = "from CustomerRelative as c where patient_id=?";
			CustomerRelative cr = (CustomerRelative) this.customerRelativeDao.queryForObject(hqll, relative_id);
			if(cr != null){
				relative_id=cr.getRelative_id();
				CustomerRelative customerRelative = this.customerRelativeDao.getCustomerRelative(customer_id, relative_id);
				if(customerRelative!=null){
					customerRelative.setCustomer_id(customer_id);
					customerRelative.setRelative_id(relative_id);
					Set<CustomerIllnessRecord> cr_set = customerRelative.getCustomerIllnessRecord();
					customerRelative.setCustomerIllnessRecord(cr_set);
					Set<RelativeMedicineRecord> cir_set = customerRelative.getRelativeMedicineRecord();
					customerRelative.setRelativeMedicineRecord(cir_set);
					this.customerRelativeDao.delete(customerRelative);
					appResult.setResult(AppResult.SUCCESS);
				}else{
					appResult.setError_info("没有相关人员信息");
					appResult.setData("");
				}
			}else{
				appResult.setError_info("没有相关人员信息");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("customer_id或者relative_id不能为空");
			appResult.setData("");
		}
		return appResult;
	}


	@Override
	public List<CustomerRelative> selectAll() {
		return this.customerRelativeDao.query(CustomerRelative.class);
	}


	@SuppressWarnings("null")
	@Override
	public Result savePatientRelative(Patient patient2, Integer customerId) {
		Customer customer = customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		CustomerRelative customerRelative = new CustomerRelative();
		String phone = patient2.getPatientPhone();
		Date time = patient2.getCreatedTime();
		Integer patient_id = Integer.valueOf(patient2.getId().toString());
		Integer sex = patient2.getSex();
		String name = patient2.getPatientName();
		customerRelative.setPatient_id(patient_id);
		customerRelative.setBirth_date(time);
		customerRelative.setCustomer(customer);
		customerRelative.setSex(sex);
		customerRelative.setPhone(phone);
		customerRelative.setName(name);
		customerRelativeDao.save(customerRelative);
		return ResultUtils.returnSuccess("保存成功");
	}

}

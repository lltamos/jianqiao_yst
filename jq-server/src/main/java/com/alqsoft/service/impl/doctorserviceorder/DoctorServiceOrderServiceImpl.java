package com.alqsoft.service.impl.doctorserviceorder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.doctorserviceorder.DoctorServiceOrderDao;
import com.alqsoft.entity.DoctorServiceOrder;
import com.alqsoft.service.doctorserviceorder.DoctorServiceOrderService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;


@Service
@Transactional
public class DoctorServiceOrderServiceImpl implements DoctorServiceOrderService{
	
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
		return doctorServiceOrderDao.findOne(arg0);
	}

	@Override
	public DoctorServiceOrder saveAndModify(DoctorServiceOrder arg0) {
		// TODO Auto-generated method stub
		return doctorServiceOrderDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<DoctorServiceOrder>> findDoctorByPage(String orderId,String customerPhone,
			String doctorPhone,Integer payStatus,
			Integer page, Integer length) { 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_orderId", orderId);
		params.put("EQ_customerPhone", customerPhone);
		params.put("EQ_doctorPhone", doctorPhone);
		params.put("EQ_payStatus", payStatus);
		params.put("EQ_deleted", 0);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<DoctorServiceOrder> specification = DynamicSpecifications.bySearchFilter(filter.values(),DoctorServiceOrder.class);
 		Page<DoctorServiceOrder> doctorServiceOrder = doctorServiceOrderDao.findAll(specification, new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
 		return BootStrapResultUtils.returnPage(DoctorServiceOrder.class, doctorServiceOrder);
	}

	@Override
	public DoctorServiceOrder getDoctorServiceOrder(Long id) {
		// TODO Auto-generated method stub
		return doctorServiceOrderDao.findOne(id);
	}

	@Override
	public Result deleted(DoctorServiceOrder doctorServiceOrder) {
		// TODO Auto-generated method stub
		Long id = doctorServiceOrder.getId();
		if(id == null){
			return SpringMVCUtils.returnError("订单ID不能为空!");
		}
		DoctorServiceOrder findOne = doctorServiceOrderDao.findOne(id);
		findOne.setDeleted(1);
		doctorServiceOrderDao.save(findOne);
		return SpringMVCUtils.returnSuccess("操作成功！");
	}

	@Override
	public Result updateDoctorServiceOrder(DoctorServiceOrder doctorServiceOrder) {
		Long id = doctorServiceOrder.getId();
		Integer serviceType = doctorServiceOrder.getServiceType();
		Integer price = doctorServiceOrder.getPrice();
		Integer executed = doctorServiceOrder.getExecuted();
		Integer pay_status = doctorServiceOrder.getPayStatus();//
		Integer pay_type = doctorServiceOrder.getPay_type();
		Date pay_time = doctorServiceOrder.getPay_time();
		String pay_relative_id = doctorServiceOrder.getPay_relative_id();
		Integer approved = doctorServiceOrder.getApproved();
		DoctorServiceOrder findOne = doctorServiceOrderDao.findOne(id);
		if(findOne ==null){
			return SpringMVCUtils.returnSuccess("当前订单不存在！");
		}
		findOne.setServiceType(serviceType);
		findOne.setPrice(price);
		findOne.setExecuted(executed);
		findOne.setPay_type(pay_type);
		findOne.setPayStatus(pay_status);
		findOne.setPay_time(pay_time);
		findOne.setPay_relative_id(pay_relative_id);
		findOne.setApproved(approved);
		System.out.println("before");
		DoctorServiceOrder save = doctorServiceOrderDao.save(findOne);
		System.out.println(save.toString());
		System.out.println("after");
		return SpringMVCUtils.returnSuccess("修改成功！");
	}

	@Override
	public BootStrapResult<List<DoctorServiceOrder>> findDoctor(String orderId, String customerPhone,Integer doctorId,
			String serviceType, Integer payStatus, Integer page, Integer length) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_orderId", orderId);
		params.put("EQ_customerPhone", customerPhone);
		params.put("EQ_serviceType", serviceType);
		params.put("EQ_payStatus", payStatus);
		params.put("EQ_deleted", 0);
		params.put("EQ_doctorId", doctorId);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<DoctorServiceOrder> specification = DynamicSpecifications.bySearchFilter(filter.values(),DoctorServiceOrder.class);
 		Page<DoctorServiceOrder> doctorServiceOrder = doctorServiceOrderDao.findAll(specification, new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
 		return BootStrapResultUtils.returnPage(DoctorServiceOrder.class, doctorServiceOrder);
	}

}

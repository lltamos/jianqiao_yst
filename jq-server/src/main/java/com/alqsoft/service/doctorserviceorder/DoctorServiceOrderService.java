package com.alqsoft.service.doctorserviceorder;

import java.util.List;

import org.alqframework.orm.BaseService;
import org.alqframework.webmvc.springmvc.Result;

import com.alqsoft.entity.DoctorServiceOrder;
import com.alqsoft.utils.BootStrapResult;

public interface DoctorServiceOrderService extends BaseService<DoctorServiceOrder>{
	
	/**
	 * 医生订单--分页列表
	 * @param doctorname
	 * @param start
	 * @param length
	 * @return
	 */
	public BootStrapResult<List<DoctorServiceOrder>> findDoctorByPage(String orderId,String customerPhone,
			String doctorPhone,Integer payStatus,
			Integer page, Integer length);
	
	/**
	 * 医生后台--医生查看自己的在线咨询订单列表
	 * @param orderId
	 * @param customerPhone
	 * @param doctorPhone
	 * @param serviceType
	 * @param payStatus
	 * @param page
	 * @param length
	 * @return
	 */
	public BootStrapResult<List<DoctorServiceOrder>> findDoctor(String orderId,String customerPhone,Integer doctorId,
			String serviceType,Integer payStatus,
			Integer page, Integer length);
	/**
	 * 查看医生订单详情
	 * @param id
	 * @return
	 */
	public DoctorServiceOrder getDoctorServiceOrder(Long id);
	
	/**
	 * 删除医生订单
	 * @param doctorServiceOrder
	 * @return
	 */
	Result deleted(DoctorServiceOrder doctorServiceOrder);
	
	
	public Result updateDoctorServiceOrder(DoctorServiceOrder doctorServiceOrder);

}

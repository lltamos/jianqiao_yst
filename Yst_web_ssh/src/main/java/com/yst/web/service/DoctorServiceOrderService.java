package com.yst.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorServiceOrder;
import com.yst.web.utils.BootStrapResult;

public interface DoctorServiceOrderService {
	public DoctorServiceOrder findDoctorServiceOrderInfo(Integer order_id);
	public AppResult updateDoctorServiceOrder(DoctorServiceOrder doctorServiceOrder);
	public AppResult queryList(Integer doctor_id);
	//public AppResult queryListCondition(String);
	public AppResult queryDoctorHomeApprovalList();
	public AppResult deleteDoctorServiceOrder(Integer order_id);
	public AppResult getDoctorServiceOrder(Integer doctor_id);
	public AppResult addDoctorServiceOrder(DoctorServiceOrder doctorServiceOrder,HttpServletRequest request);
	public void alipayNotify(String out_trade_no,Date gmt_payment,Integer pay_type);
	public AppResult findCustomerServiceOrderInfo(Integer doctor_id,Integer customer_id);
	public AppResult getMedicalOrders(Integer customer_id);
	public AppResult updateCheckServiceTime();
	/**
	 * 咨询订单分润查询
	 * @param integer 
	 * @return
	 */
	public AppResult findConsultOrderPage(Integer doctor_id);
	
	public BootStrapResult<List<DoctorServiceOrder>> findDoctorServiceOrderByPage(Map<String, Object> searchParams,
			Integer start, Integer pageSize, HttpServletRequest request);
	/**
	 * 验证并获取医生咨询时间
	 * @param doctorServiceOrder
	 * @return
	 */
	public AppResult getDoctorConsultTime(DoctorServiceOrder doctorServiceOrder);
}

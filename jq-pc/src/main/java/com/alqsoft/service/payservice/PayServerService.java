package com.alqsoft.service.payservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.alqframework.result.Result;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.DoctorServiceOrder;
import com.alqsoft.entity.ProductOrder;

public interface PayServerService {
	
	public Result payServer(Long doctorId,Integer type,Customer customer,HttpServletRequest request,String tjr) throws Exception;
	public Result getErWeiMa(Long doctorId,Integer type,Customer customer,HttpServletRequest request,String tjr) throws Exception;
	public Result getDoctorService(Long doctorId);
}

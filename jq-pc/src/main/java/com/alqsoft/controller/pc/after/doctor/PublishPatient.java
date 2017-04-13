package com.alqsoft.controller.pc.after.doctor;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.patient.PatientDisease;
import com.alqsoft.rpc.RpcPublishDisease;

@Controller
@RequestMapping("/pc/view/doctor")
public class PublishPatient {

	@Autowired
	private RpcPublishDisease rpcPublichDisease;

	@RequestMapping("/publishtofinddoctor")
	@ResponseBody
	public Result publishtofinddoctor(PatientDisease  disease,HttpServletRequest request){
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		
		String parameter = request.getParameter("images");
		if(parameter!=null){
			String[] split = parameter.split(",");
			disease.setImgAddress(split);
		}
		if(null != customer){
			disease.setCustomerId(customer.getId());
			disease.setCustomerImage(customer.getImage());
			Result result = rpcPublichDisease.savePatientDiease(disease);
			return result;
		}
		Result result = rpcPublichDisease.savePatientDiease(disease);
		return result;
	}
}

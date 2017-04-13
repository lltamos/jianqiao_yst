package com.alqsoft.controller.pc.after.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Customer;
import com.alqsoft.rpc.RpcDoctorServicesService;
import com.alqsoft.service.payservice.PayServerService;

@RequestMapping("erweima")
@Controller
public class ErweimaContorller {
	@Autowired
	private PayServerService parServerService;
	@Autowired
	private RpcDoctorServicesService rpcDoctorServicesService;
	@RequestMapping("/goto-pay")
	public String toPayService(Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam Long doctorid,
			@RequestParam Integer type,
			@RequestParam String tjr
			) throws Exception{
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(customer == null){
			return "view/customer/login";
		}
		Result result = parServerService.getErWeiMa(doctorid, type, customer,request,tjr);
		model.addAttribute("result",result);
		
		return "after/servicepackage/erweima";
	}
	@RequestMapping("topay")
	@ResponseBody
	public Result toPay(Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam Long doctorid,
			@RequestParam Integer type,
			@RequestParam String tjr
			) throws Exception{
		Result result = new Result();

		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(customer == null){
			result.setCode(2);//code=2表示还没有登陆，跳转到登陆页面
			result.setContent("您还没有登陆，请登录！");
			return result;
		}
		
		Result result2 = parServerService.getDoctorService(doctorid);
		return result2;
	}
	
}

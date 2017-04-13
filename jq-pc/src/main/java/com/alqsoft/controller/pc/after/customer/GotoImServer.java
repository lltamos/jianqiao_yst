package com.alqsoft.controller.pc.after.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Customer;
import com.alqsoft.service.consultation.ConsultationService;

@Controller
@RequestMapping("goIm")
public class GotoImServer {
		
	@Autowired
	private ConsultationService consultationService;
	
	/**
	 * 在线聊天功能
	 * @param request
	 * @param model
	 * @return
	 * by:zmy
	 * @throws IOException 
	 */
	@RequestMapping("sayadmin")
	public String gotoSayadmin(HttpServletRequest request,Model model,HttpServletResponse response) throws IOException{
		Customer custmoer = getSession(request);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(new Date());
		String doctorName = request.getParameter("doctorName")+"";
		if(custmoer==null){
			/*Random r =new Random(10);
			int nextInt = r.nextInt(5);
			int index = nextInt%5;*/
			//model.addAttribute("toUser","kefu"+index);
			Result result = consultationService.findAdmin("admin","游客"+str,model);
			return "after/im/imindex";
		}else{
			Result result = consultationService.findAdmin("admin",custmoer.getPhone(),model);
		}
		
	/*	Result result = consultationService.findDoctor(custmoer.getId(),doctorName,custmoer.getPhone(),model);
		if(!result.getCode().equals(1)){
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert("+result.getMsg()+")</script>");
			writer.flush();
			writer.println("<script>location.href='/index.jsp'</script>");
			writer.flush();
			writer.close();
return null;
		}*/
		return "after/im/imindex";
	}
	
	
	@RequestMapping("verifyCustomerPay")
	@ResponseBody
	public Result verifyPay(HttpServletRequest request,Model model,HttpServletResponse response){
		Result result = new Result();
		Customer custmoer = getSession(request);
		String doctorName = request.getParameter("doctorId")+"";
		if(custmoer ==null){
			result.setCode(3);
			result.setMsg("请先登录");
			return result;
		}
		//result = consultationService.findDoctor(custmoer.getId(),doctorName,custmoer.getPhone(),model);
		result = consultationService.verifyOrder(custmoer.getId(), doctorName, custmoer.getPhone(), model);
		
		return result;
	}
	
	@RequestMapping("verifyCustomer")
	@ResponseBody
	public Result verifyCustomer(HttpServletRequest request,Model model,HttpServletResponse response,@RequestParam Long customerId) throws Exception{
		Result result = new Result();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(customer ==null){
			result.setCode(3);
			result.setMsg("请先登录");
			return result;
		}
		Long doctorCustomerId = customer.getId();
		//result = consultationService.findDoctor(custmoer.getId(),doctorName,custmoer.getPhone(),model);
		result = consultationService.verifyCustomer(doctorCustomerId, customerId, model);
		
		return result;
	}
	
	@RequestMapping("sayDoctor")
	public String gotoSayDoctor(HttpServletRequest request,Model model,HttpServletResponse response) throws IOException{
		Customer custmoer = getSession(request);
		String doctorName = request.getParameter("doctorId")+"";
		if(custmoer==null){
			PrintWriter writer = response.getWriter();
			writer.print("<script>alert('请先登录 ')</script>");
			writer.flush();
			return "";
		}
		
		Result result = consultationService.findDoctor(custmoer.getId(),doctorName,custmoer.getPhone(),model);
		if(!result.getCode().equals(1)){
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			writer.println("<script>alert('"+result.getMsg()+"')</script>");
			writer.flush();
			return "";
		}
		return "after/im/imindex";
	}
	
	@RequestMapping("sayCustomer")
	public String gotosayCustomer(HttpServletRequest request,Model model,HttpServletResponse response) throws Exception{
		Customer custmoer = getSession(request);
		String customerId = request.getParameter("customerId")+"";
		if(custmoer==null){
			PrintWriter writer = response.getWriter();
			writer.print("<script>alert('请先登录 ')</script>");
			writer.flush();
			return "";
		}
		
		Result result = consultationService.findCustomer(custmoer.getId(),customerId,custmoer.getPhone(),model);
		//if(result.getCode().equals(1)){
			PrintWriter writer = response.getWriter();
			writer.print("<script>alert("+result.getMsg()+")</script>");
			writer.flush();
			//writer.println("<script>location.href='/index.jsp'</script>");
			//return "";
		//}
		return "after/im/imindex";
	}
	
	
	@RequestMapping("logAdmin")
	public String logAdmin(HttpServletRequest request,Model model,HttpServletResponse response) throws IOException{
		Customer custmoer = getSession(request);
		Result result = consultationService.findAdmin("admin","admin",model);
		return "after/im/imindex";
	}
	
	@RequestMapping("logdDoctor")
	public String logindDoctor(HttpServletRequest request,Model model,HttpServletResponse response) throws IOException{
		Customer custmoer = getSession(request);
		if(custmoer==null){
			PrintWriter writer = response.getWriter();
			writer.print("<script>alert('请先登录 ')</script>");
			writer.print("<script>window.location.href=history.go(-1)</script>");
			writer.flush();
			return  "";
		}
		
		Result result = consultationService.findAdmin(custmoer.getPhone(),custmoer.getPhone(),model);
		return "after/im/imindex";
	}
	
	
	private Customer getSession(HttpServletRequest request) {
		return (Customer) request.getSession().getAttribute("customer");
	
	}
}

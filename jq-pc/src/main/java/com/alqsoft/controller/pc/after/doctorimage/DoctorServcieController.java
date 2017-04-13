package com.alqsoft.controller.pc.after.doctorimage;

import java.util.Date;
import java.util.Map;

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
import com.alqsoft.entity.DoctorServiceTime;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.rpc.RpcDoctorServicesService;
import com.alqsoft.service.doctor.DoctorService;

/**
 *医生服务设置
 * @author 
 *
 */
@Controller
@RequestMapping("pc/after/doctorservice")
public class DoctorServcieController {
		
	@Autowired
	private RpcDoctorServicesService rpcDoctorServicesService;
	@Autowired
	private DoctorService doctorService;
	@RequestMapping("gosettings")
	public String goSettings(Model model,HttpServletRequest request, HttpServletResponse response){
//		model.addAttribute("style","doctorservice");
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		//根据当前用户的id，获取他的医生信息==》得到doctorId；
		if(customer.getType()==1){
			Long customerId = customer.getId();
			Doctors doctor = doctorService.getDoctorByCustomerId(customerId);
//			2根据doctorid获取到doctorService中的价格
			Integer price1 = doctorService.getDoctorOrderPriceById(doctor.getId());
			if(null == price1){
				price1=0;
			}
//			3根据doctorid获取医生服务的时间长的
			Map<String, Object> doctorServiceTime = doctorService.getDoctorServiceTimeByDoctorId(doctor.getId());
//			4根据doctorid判断服务是否开启或关闭
			Integer onOff = doctorService.getDoctorServiceStats(Integer.parseInt(doctor.getId().toString()));
			model.addAttribute("doctor",doctor);
			model.addAttribute("price1",price1);
			model.addAttribute("doctorServiceTime",doctorServiceTime);
			model.addAttribute("onOff",onOff);
			model.addAttribute("style","doctorservice");
			return "after/servicepackage/doctorservice";
		}
		return "/pc/view/doctor/to-certification";
	}
	
	
	@RequestMapping("tosettings")
	@ResponseBody
	public Result updateDoctorService(Model model,HttpServletRequest request,
			HttpServletResponse response,@RequestParam Integer price1,
			@RequestParam String mon,@RequestParam String tue,@RequestParam String wed,@RequestParam String thu,
			@RequestParam String fri,@RequestParam String sat,@RequestParam String sun,@RequestParam String date01,
			@RequestParam String date02
			){
		DoctorServiceTime doctorServiceTime = new DoctorServiceTime();
		Result result = new Result();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		/*	if(customer ==null){
			return ResultUtils.returnError("请登录后再进行操作!");
		}
		Long id = customer.getId();
		Doctors doctor = rpcDoctorServicesService.getDoctor(id);
		Long id2 = doctor.getId();
		if(id2 ==null){
			return ResultUtils.returnError("请先成为医生用户,在进行操作!");
		}*/
		Long customerId = customer.getId();
		Doctors doctor = doctorService.getDoctorByCustomerId(customerId);
		Long id =doctor.getId();
		
		DoctorServiceTime doctorServiceTimeByCustomerId = rpcDoctorServicesService.getDoctorServiceTimeByCustomerId(id);
		
		if(doctorServiceTimeByCustomerId ==null && null != id){
			doctorServiceTime.setMonday(mon);
			doctorServiceTime.setTuesday(tue);
			doctorServiceTime.setWednesday(wed);
			doctorServiceTime.setThursday(thu);
			doctorServiceTime.setFriday(fri);
			doctorServiceTime.setSaturday(sat);
			doctorServiceTime.setSunday(sun);
			doctorServiceTime.setCan_consult_starttime(date01);
			doctorServiceTime.setCan_consult_endtime(date02);
			doctorServiceTime.setUpdateTime(new Date());
			doctorServiceTime.setDoctor_id(id);
			DoctorServiceTime upadteDoctorservice = rpcDoctorServicesService.upadteDoctorservice(doctorServiceTime);
			Boolean isPrice = rpcDoctorServicesService.upadteDoctorServicePrice(doctor, price1);
			if(upadteDoctorservice !=null && isPrice){
				result.setCode(1);
				result.setMsg("保存成功");
			}else{
				result.setCode(0);
				result.setMsg("保存失败");
			}
			
		}else{
			doctorServiceTimeByCustomerId.setMonday(mon);
			doctorServiceTimeByCustomerId.setTuesday(tue);
			doctorServiceTimeByCustomerId.setWednesday(wed);
			doctorServiceTimeByCustomerId.setThursday(thu);
			doctorServiceTimeByCustomerId.setFriday(fri);
			doctorServiceTimeByCustomerId.setSaturday(sat);
			doctorServiceTimeByCustomerId.setCan_consult_starttime(date01);
			doctorServiceTimeByCustomerId.setCan_consult_endtime(date02);
			doctorServiceTimeByCustomerId.setUpdateTime(new Date());
			doctorServiceTimeByCustomerId.setDoctor_id(id);
			DoctorServiceTime upadteDoctorservice = rpcDoctorServicesService.upadteDoctorservice(doctorServiceTimeByCustomerId);
			Boolean isPrice2 = rpcDoctorServicesService.upadteDoctorServicePrice(doctor, price1);
			if(upadteDoctorservice !=null && isPrice2){
				result.setCode(1);
				result.setMsg("保存成功");
			}else{
				result.setCode(0);
				result.setMsg("保存失败");
			}
			
		}
		
		return result;
	}
	@RequestMapping("/close")
	@ResponseBody
	public Result closeDoctorService(HttpServletRequest request,
			HttpServletResponse response
			){
		Result res = new Result();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		Long customerId = customer.getId();
		Doctors doctor = doctorService.getDoctorByCustomerId(customerId);
		Long id =doctor.getId();
		if(null!=id){
			Result result = rpcDoctorServicesService.closeDoctorServicePrice(id);
			return result;
		}
		res.setCode(0);
		res.setContent("服务操作失败!");
		return  res;
	}
}

package com.alqsoft.controller.pc.after.doctor;

import java.util.HashMap;
import java.util.List;
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
import com.alqsoft.entity.PatientHistory;
import com.alqsoft.entity.doctor.DoctorInfo;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.service.dicspec.DictionaryService;
import com.alqsoft.service.doctor.DoctorService;
import com.alqsoft.service.merchant.MerchantService;



/**
 * 
 * @Description: 
 * @author shenguang
 * @e-mail shenguang044539@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 上午11:04:01
 * Copyright  2013 北京易商通公司 All rights reserved.
 *
 */
@Controller
@RequestMapping("/pc/view/doctor")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private DictionaryService dicSpecService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private CustomerService customerService;
	
	
	/**
	 * 跳转名医高手
	 * @return
	 */
	@RequestMapping("/todoctor")
	public String toDoctorPage(Model model,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="5")Integer rows){
		Map<String, Object> params = new HashMap<>();
		params.put("page", (page-1)*rows);
		params.put("rows",rows);
		List<DoctorInfo> doctorList = doctorService.doctorPage(params);
		Integer total = doctorService.getDoctorTotal();
		double totalPage2 = Math.ceil(total / rows.doubleValue());// 向上取整
		Integer totalPage = (new Double(totalPage2)).intValue();
		model.addAttribute("doctorList", doctorList);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("check", "doctor");
		return "/view/doctor/doctortwopage";
	}
	/**
	 * 显示医生的个人详情页
	 * @param model
	 * @param id
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/showdoctorinfo")
	public String showDoctorInfo(Model model,
			@RequestParam(value="id",defaultValue="1")Long id,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="2")Integer rows){
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("page", 0);
		params.put("rows",4);
		List<Map<String, Object>> doctorImages = doctorService.doctorImages(params);
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("page", page);
		map.put("rows",rows);
		List<Map<String, Object>> doctorDiarys = doctorService.doctorDiary(map);
		Integer total = doctorService.findDiaryByDoctorIdTotal(id); 
		double totalPage2 = Math.ceil(total / rows.doubleValue());// 向上取整
		Integer totalPage = (new Double(totalPage2)).intValue();
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("page", page);
		pageMap.put("totalPage", totalPage);
		DoctorInfo doctorInfo = doctorService.getDoctorInfoById(id);
		model.addAttribute("doctorImages", doctorImages);
		model.addAttribute("doctorDiarys", doctorDiarys);
		model.addAttribute("doctorInfo",doctorInfo);
		model.addAttribute("check", "doctor");
		model.addAttribute("pageMap", pageMap);
		return "/view/doctor/doctorinfopage";
	}
 
	/**
	 * 跳转发布求医页面
	 * @return
	 */
	@RequestMapping("/publishdisease")
	public String publishDisease(Model model,
			HttpServletRequest request,
			HttpServletResponse response){
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(null != customer){
			model.addAttribute(customer);
		}
		List<Map<String, Object>> findAllPro = doctorService.findAllPro();
		List<Map<String, Object>> findProductType = doctorService.findProductType();
		model.addAttribute("findAllPro", findAllPro);
		model.addAttribute("findProductType", findProductType);
		model.addAttribute("check", "paitent");
		return "/view/doctor/publishdisease";
	}
	/**
	 * 患者求医一级页面
	 * @param model
	 * @param page
	 * @param rows
	 * @return
	 */
	
	@RequestMapping("/finddoctor")
	public String finddoctor(Model model,@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="2")Integer rows){
		Map<String, Object> map = new HashMap<>();
		map.put("page", (page-1)*rows);
		map.put("rows",rows);
		List<PatientHistory> diseaseList = doctorService.findDoctor(map);
		Integer total = doctorService.getPatientTotal();
		double totalPage2 = Math.ceil(total / rows.doubleValue());// 向上取整
		Integer totalPage = (new Double(totalPage2)).intValue();
		model.addAttribute("diseaseList", diseaseList);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("check", "paitent");
		return "/view/doctor/finddoctorpage";
	}
	
	/**
	 * 首页显示医生:暂时没有用到,首页的医生是固定数据
	 * @return
	 */
	@RequestMapping("/doctorindex")
	@ResponseBody
	public List<Map<String, Object>> getDoctorIndex(){
		List<Map<String, Object>> doctorIndx = doctorService.getDoctorIndx();
		return doctorIndx;
	}

	/**
	 * 跳转医生认证页面
	 * @return
	 */
	@RequestMapping("to-certification")
	public String toCertification(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response
			){
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(customer == null){
			return "redirect:/pc/view/customer/to-login";
		}

		//专长
		List<Map<String, Object>> speclist = dicSpecService.findDicSpecAll();
		List<Map<String, Object>> officelist = dicSpecService.finddicOfficeAll();
		List<Map<String, Object>> titallist = dicSpecService.findDicTitleAll();
		List<Map<String, Object>> merchantlist = merchantService.findMerchantAll();
		model.addAttribute("speclist",speclist);
		model.addAttribute("officelist",officelist);
		model.addAttribute("titallist",titallist);
		model.addAttribute("merchantlist",merchantlist);
		return "view/doctor/doctorcertification";
	}
	/**
	 * 医生认证
	 * @param doctor
	 * @return
	 */
	@RequestMapping("doctor-certification")
	@ResponseBody
	public Result doctorCertification(
			Model model,
			Doctors doctor,
			HttpServletRequest request,
			HttpServletResponse response
			){
		Result result = new Result();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(customer == null){
			result.setCode(3);
			result.setMsg("未登录，请先登录");
			return result;
		}
		String aids = request.getParameter("aids");
		result = doctorService.doctorCertification(aids,doctor, customer);
		return result;
	}
	
	/**
	 * 根据城市查询该城市的医生
	 * @return
	 */
	@RequestMapping("/finddoctorbycity")
	public String findDoctorByCity(
			Model model,
			@RequestParam(value="officeName",defaultValue="内科")String officeName,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="5")Integer rows){
		Map<String, Object> map = new HashMap<>();
		map.put("officeName", officeName);
		map.put("page", (page-1)*rows);
		map.put("rows",rows);
		List<DoctorInfo> doctorList = doctorService.findDoctorByCityName(map);
		Integer total = doctorService.getDoctorByCitNameTotal();
		double totalPage2 = Math.ceil(total / rows.doubleValue());// 向上取整
		Integer totalPage = (new Double(totalPage2)).intValue();
		model.addAttribute("doctorList", doctorList);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("officeName", officeName);
		model.addAttribute("check", "doctor");
		return "/view/doctor/doctortsreach";
	}
	
	/**
	 * 验证医生
	 */
	@RequestMapping("verification-doctor")
	@ResponseBody
	public Result verificationDoctor(
			HttpServletRequest request,
			HttpServletResponse response
			){
		Result result = new Result();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(customer == null){
			result.setCode(2);
			result.setMsg("未登录，请先登录");
			return result;
		}
		Long cid = customer.getId();
		Customer customer2 = customerService.getCustomerById(cid);
		Integer type = customer2.getType();
		if(type == null){
			type = 0;
		}
		if(type == 1){
			result.setCode(1);
			result.setMsg("已是医生，请勿重复申请");
			return result;
		}
		result = doctorService.verificationDoctor(cid);
		return result;
	}
	
}

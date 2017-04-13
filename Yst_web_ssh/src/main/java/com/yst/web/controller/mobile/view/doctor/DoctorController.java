package com.yst.web.controller.mobile.view.doctor;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.service.DoctorService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年6月21日 上午10:35:05
 * 
 */
@RequestMapping("/ydmvc/mobile/view/doctor")
@Controller
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	/**
	 * 医生列表
	 * @param page_no
	 * @param page_size
	 * @return
	 */
	@RequestMapping("getDoctorListAll.do")
	@ResponseBody
	public Result getDoctorListAll(
			@RequestParam(value="page_no",defaultValue="0")Integer page_no,
			@RequestParam(value="page_size",defaultValue="5")Integer page_size){
		return this.doctorService.getDoctorListAll(page_no-1,page_size);
	}
	
	/**
	 * 医生详情页
	 * @param doctor_id
	 * @return
	 */
	@RequestMapping("getDoctorDetails.do")
	@ResponseBody
	public Result getDoctorDetails(
			@RequestParam(value="doctor_id",required=false)Integer doctor_id){
		return this.doctorService.getOneDoctorDetails(doctor_id);
	}
	
	/**
	 * 医生详情页评价
	 * @param doctor_id
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("getDoctorComment.do")
	@ResponseBody
	public Result getDoctorComment(
			@RequestParam(value="doctor_id",required=false)Integer doctor_id,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="5")Integer rows){
		return this.doctorService.getDoctorComment(doctor_id,page-1,rows);
	}
	
	/**
	 * 添加医生评论
	 * @param doctor_id
	 * @param customer_id
	 * @param service_star
	 * @param customer_comment
	 * @return
	 */
	@RequestMapping("addDoctorComment.do")
	@ResponseBody
	public Result addDoctorComment(
			@RequestParam(value="doctor_id",required=false)Integer doctor_id,
			@RequestParam(value="customer_id",required=false)Integer customer_id,
			@RequestParam(value="service_star",required=false)Integer service_star,
			@RequestParam(value="customer_comment",required=false)String customer_comment){
		return this.doctorService.saveDoctorComment(doctor_id,customer_id,service_star,customer_comment);
	}
	
	/**
	 * 获取字典数据接口(该接口可以获取任意字典的所有字段)
	 * @param dics
	 * @return
	 */
	@RequestMapping("getDic.do")
	@ResponseBody
	public Result getDic(
			@RequestParam(value="dics",required=false)String dics){
		return this.doctorService.getDic(dics);
	}
}

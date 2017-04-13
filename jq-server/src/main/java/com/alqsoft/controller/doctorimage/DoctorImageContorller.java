package com.alqsoft.controller.doctorimage;

import java.util.List;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.DoctorImage;
import com.alqsoft.service.doctorimage.DoctorImageService;
import com.alqsoft.utils.BootStrapResult;

/**
 * 
    * @ClassName: DoctorImageContorller  
    * @Description:   医生图文介绍-控制层
    * @author 腾卉  
    * @date 2017年3月12日  
    *
 */

@RequestMapping("doctorimage")
@Controller
public class DoctorImageContorller {

	@Autowired
	private DoctorImageService doctorImageService;
	
	/**
	 * 到医生图文详情列表
	 * @return
	 */
	@RequestMapping("to-doctorimage-list")
	public String toDoctorImagePage(@RequestParam(value="doctorid")String doctorid,Model model){
		model.addAttribute("doctorid",doctorid);
		return "doctor/doctorimage/doctorimage-list";
		
	}
	
	/**
	 * 医生图文详情列表
	 * @param doctorname
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping("doctorImage-list")
	@ResponseBody
	public BootStrapResult<List<DoctorImage>> findDoctorImageByPage(@RequestParam(value="doctorid", required=false)String doctorid,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length){
		Integer page=start/length;
		BootStrapResult<List<DoctorImage>> result = doctorImageService.findDoctorImageByPage(doctorid,page,length);
		return result;
	}
	
	/**
	 * 到添加医生图文详情页面
	 * @param doctorid
	 * @param model
	 * @return
	 */
	@RequestMapping("to-doctorimage-add")
	public String toDoctorImageAdd(@RequestParam(value="doctorid")String doctorid,Model model){
		model.addAttribute("doctorid",doctorid);
		return "doctor/doctorimage/doctorimage-add";
		
	}
	
	/**
	 * 添加医生图文详情
	 * @param doctorid
	 * @return
	 */
	@RequestMapping("doctorimage-add")
	@ResponseBody
	public Result doctorImageAdd(DoctorImage doctorImage){
		Result result = doctorImageService.saveDoctorImage(doctorImage);
		return result;
	}
	
	/**
	 * 到修改图文详情页面
	 * @param doctorid
	 * @param model
	 * @return
	 */
	@RequestMapping("to-doctorimage-modify")
	public String toDoctorImageUpdate(@RequestParam(value="diid")Integer diid,@RequestParam(value="doctorid")Integer doctorid,Model model){
		DoctorImage doctorimage = doctorImageService.get(new Long(diid));
		model.addAttribute("doctorimage",doctorimage);
		model.addAttribute("doctorid",doctorid);
		return "doctor/doctorimage/doctorimage-update";
		
	}
	
	/**
	 * 编辑图文详情
	 * @param doctorImage
	 * @return
	 */
	@RequestMapping("doctorimage-modfiy")
	@ResponseBody
	public Result doctorImageModfiy(DoctorImage doctorImage){
		Result result = doctorImageService.saveDoctorImage(doctorImage);
		return result;
	}
	
	/**
	 * 删除图文详情
	 * @param doctorImage
	 * @return
	 */
	@RequestMapping("doctorimage-remove")
	@ResponseBody
	public Result removeDoctorImage(@RequestParam(value="doctorimageid")Integer doctorimageid){
		Result result = doctorImageService.deleteDoctorImage(doctorimageid);
		return result;
	} 
	
}

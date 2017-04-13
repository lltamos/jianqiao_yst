package com.alqsoft.controller.pc.after.doctorimage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.DoctorImage;
import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.rpc.RpcDoctorImageService;
import com.alqsoft.service.attachment.AttachmentService;
import com.alqsoft.service.doctor.DoctorService;

/**
 * 
 * @ClassName: DoctorImageController
 * @Description: 医生图文介绍-控制层
 * @author 腾卉
 * @date 2017年3月13日
 *
 */
@Controller
@RequestMapping("/pc/after/doctorimage")
public class DoctorImageController {

	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private RpcDoctorImageService rpcDoctorImageService;
	@Autowired
	private DoctorService doctorService;
	
	
	/**
	 * 
	* @Title: decide 
	* @Description: 跳转我是医生之前验证医生状态是否可用
	* @return Result    返回类型 
	* @author 腾卉 
	* @throws
	 */
	@RequestMapping("decide")
	@ResponseBody
	public Result decide(HttpServletRequest request, HttpServletResponse response){
		Result result = new Result();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if (customer == null) {
			return ResultUtils.returnError("请登录后再进行操作!");
		}
		Long id = customer.getId();
		Doctors doctor = doctorService.findByCid(id);
		Integer deleted = doctor.getDeleted();
		if( deleted == 0){
			result.setCode(1);
			result.setMsg("状态可用");
			return result;
		}
		result.setCode(0);
		result.setMsg("此医生已被禁用");
		return result;
	}

	/**
	 * 
	 * @Title: findDoctorImageByDoctorId
	 * @Description: 根据医生id查找图文介绍
	 * @author 腾卉
	 * @return String 返回类型
	 */
	@RequestMapping("doctorimage-list")
	public String findDoctorImageByDoctorId(Model model, HttpServletRequest request, HttpServletResponse response) {
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if (customer == null) {
			return "/view/customer/login";
		}
		Long id = customer.getId();
		Doctors doctor = doctorService.findByCid(id);
		Long doctorId = doctor.getId();
		// Long doctorId = 1l;
		List<DoctorImage> doctorImageList = rpcDoctorImageService.findById(doctorId);
		model.addAttribute("doctorImageList", doctorImageList);
		model.addAttribute("style", "doctorimage");
		return "after/doctorimage/introduction-list";
	}

	/**
	 * 
	 * @Title: doctorImageAdd
	 * @Description: 添加图文介绍
	 * @author 腾卉
	 * @return Result 返回类型
	 */
	@RequestMapping("add-doctorimage")
	@ResponseBody
	public Result doctorImageAdd(@RequestParam(value = "des") String des, @RequestParam(value = "aids") String aids,
			HttpServletRequest request, HttpServletResponse response) {
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if (customer == null) {
			return ResultUtils.returnError("请登录后再进行操作!");
		}

		Long id = customer.getId();
		Doctors doctor = doctorService.findByCid(id);
		// Long doctorId = 1l;
		Long doctorId = doctor.getId();
		String doctorName = doctor.getName();
		Attachment attachment = attachmentService.getOneById(Long.valueOf(aids));
		DoctorImage doctorImage = new DoctorImage();
		doctorImage.setAttachmentId(attachment.getId());
		doctorImage.setImage(attachment.getAddress());
		doctorImage.setDoctorId(doctorId);
		doctorImage.setDoctorName(doctorName);
		doctorImage.setDes(des);
		Result result = rpcDoctorImageService.saveDoctorImage(doctorImage);
		return result;
	}

	/**
	 * 
	 * @Title: updateDoctorImage
	 * @Description: 修改图文介绍
	 * @author 腾卉
	 * @return String 返回类型
	 */
	@RequestMapping("updateDoctorImage")
	@ResponseBody
	public Result updateDoctorImage(DoctorImage doctorImage) {
		Result updateDoctorImage = rpcDoctorImageService.updateDoctorImage(doctorImage);
		return updateDoctorImage;
	}

	
}

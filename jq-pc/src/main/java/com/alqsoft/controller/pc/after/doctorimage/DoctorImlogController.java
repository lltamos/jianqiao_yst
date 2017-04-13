package com.alqsoft.controller.pc.after.doctorimage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.service.doctor.DoctorService;
import com.alqsoft.service.imlog.ImlogService;

/**
 * 
* @ClassName: DoctorImlogController 
* @Description: 医生个人中心聊天记录  控制层
* @author 腾卉 
* @date 2017年4月5日 下午4:26:16 
*
 */
@Controller
@RequestMapping("/pc/after/doctorimlog")
public class DoctorImlogController {

	@Autowired
	private ImlogService imlogService;
	@Autowired
	private DoctorService doctorService;
	
	/**
	 * 
	* @Title: findImlog 
	* @Description: 医生聊天记录列表 
	* @return String    返回类型 
	* @author 腾卉 
	* @throws
	 */
	@RequestMapping("imlog-list")
	public String findImlog(Model model,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "4") Integer rows,
			HttpServletRequest request) {
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if (customer == null) {
			return "/view/customer/login";
		}
		Long id = customer.getId();
		Doctors doctor = doctorService.findByCid(id);
		Long doctorId = doctor.getId();
		Integer totalPage = 0;
		List<Map<String, Object>> list = imlogService.findAllByDoctorId(doctorId, page, rows);
		totalPage = imlogService.getCountByDoctorId(doctorId);
		double totalPage2 = Math.ceil(totalPage / rows.doubleValue());// 向上取整
		Integer totalPage1 = (new Double(totalPage2)).intValue();
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage1);
		model.addAttribute("list", list);
		model.addAttribute("style", "imlog");
		return "after/imlog/imlog-list";
	}
}

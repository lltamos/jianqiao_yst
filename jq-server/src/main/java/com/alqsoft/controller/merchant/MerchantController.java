package com.alqsoft.controller.merchant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Merchant;
import com.alqsoft.model.Permission;
import com.alqsoft.service.merchant.MerchantService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

/**
 * 总院控制器
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2017
 * @create-time 2017-3-6 下午8:01:14
 * 
 */
@RequestMapping("merchant")
@Controller
public class MerchantController {

	@Autowired
	private MerchantService merchantService;
	
	/**
	 * 跳转总院列表
	 * @return
	 */
	@RequestMapping("merchant-list")
	@Permission(SystemRole.ADMIN)
	public String merchantList() {
		return "merchant/merchant-list";
	}
	
	/**
	 * 总院列表分页
	 * @param model
	 * @param start
	 * @param length
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("merchant-list-data")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<Merchant>> merchantListData(Model model,
			@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request,
			HttpSession session) {
		Integer page = start / length;
		return this.merchantService.getMerchantList(page,length,request);
	}
	
	/**
	 * 跳转总院新增或者修改页面的视图
	 * @return
	 */
	@RequestMapping("merchant-input")
	@Permission(SystemRole.ADMIN)
	public String merchantAddPage(Model model,
			Merchant merchant,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		if(merchant.getId()!=null){
			model.addAttribute("merchant", this.merchantService.get(merchant.getId()));
		}
		return "merchant/merchant-input";
	}
	
	/**
	 * 新增或者修改总院
	 * @param model
	 * @param merchant
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("merchant-save")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result merchantSave(Model model,
			@RequestParam(value="aids") String aids,
			@RequestParam(value="id") Long id,
			@RequestParam(value="name") String name,
			@RequestParam(value="customerPhone") String customerPhone,
			@RequestParam(value="des") String des,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		return this.merchantService.save( aids, id, name, customerPhone, des);
	}
	/**
	 * 恢复/禁用总院
	 * @param model
	 * @param merchant
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("merchant-deleted")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result merchantDeleted(Model model,
			Merchant merchant,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		if(session.getAttribute(SystemRole.ADMIN.getName())==null){
			return SpringMVCUtils.returnError("登录超时，请重新登录！");
		}
		return this.merchantService.deleted(merchant);
	}
	
	/**
	 * 查看总院
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("merchant-info")
	@Permission(SystemRole.ADMIN)
	public String merchantInfo(Model model,
			@RequestParam(required=true,value="id")Long id,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		model.addAttribute("merchant", this.merchantService.get(id));
		return "merchant/merchant-info";
	}
}

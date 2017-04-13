package com.alqsoft.controller.sharemoney;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.shareMoney.ShareMoney;
import com.alqsoft.model.Permission;
import com.alqsoft.service.balance.BalanceService;
import com.alqsoft.service.sharemoney.ShareMoneyService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

/**
 * 分润记录控制器
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2017
 * @create-time 2017-3-6 下午8:01:14
 * 
 */
@RequestMapping("sharemoney")
@Controller
public class ShareMoneyController {

	@Autowired
	private ShareMoneyService shareMoneyService;
	@Autowired
	private BalanceService balanceService;
	/**
	 * 跳转总院分润记录列表
	 * @return
	 */
	@RequestMapping("merchant-list")
	@Permission(SystemRole.HOSPITAL)
	public String merchantList(Model model,HttpServletRequest request) {
		Merchant dbMerchant=(Merchant) request.getSession().getAttribute(SystemRole.HOSPITAL.getName());
		model.addAttribute("balance", balanceService.getByCustomerIdAndType(dbMerchant.getCustomerId(),2));
		return "sharemoney/merchant-list";
	}
	/**
	 * 跳转医生分润记录列表
	 * @return
	 */
	@RequestMapping("doctor-list")
	@Permission(SystemRole.DOCTOR)
	public String doctorList(Model model,HttpServletRequest request) {
		Doctors dbDoctor=(Doctors) request.getSession().getAttribute(SystemRole.DOCTOR.getName());
		model.addAttribute("balance", balanceService.getByCustomerIdAndType(dbDoctor.getCustomerId(),1));
		return "sharemoney/doctor-list";
	}
	/**
	 * 跳转推荐人分润记录列表
	 * @return
	 */
	@RequestMapping("recommender-list")
	@Permission(SystemRole.RECOMMENDER)
	public String recommenderList(Model model,HttpServletRequest request) {
		Customer dbCustomer=(Customer) request.getSession().getAttribute(SystemRole.RECOMMENDER.getName());
		model.addAttribute("balance", balanceService.getByCustomerIdAndType(dbCustomer.getId(),3));
		return "sharemoney/recommender-list";
	}
	
	/**
	 * 总院分润记录列表分页
	 * @param model
	 * @param start
	 * @param length
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("merchant-list-data")
	@ResponseBody
	@Permission(SystemRole.HOSPITAL)
	public BootStrapResult<List<ShareMoney>> merchantListData(Model model,
			@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request,
			HttpSession session) {
		Integer page = start / length;
		return this.shareMoneyService.getMerchantList(page,length,request);
	}
	
	/**
	 * 医生分润记录列表分页
	 * @param model
	 * @param start
	 * @param length
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("doctor-list-data")
	@ResponseBody
	@Permission(SystemRole.DOCTOR)
	public BootStrapResult<List<ShareMoney>> doctorListData(Model model,
			@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request,
			HttpSession session) {
		Integer page = start / length;
		return this.shareMoneyService.getDoctorList(page,length,request);
	}
	
	/**
	 * 推荐人分润记录列表分页
	 * @param model
	 * @param start
	 * @param length
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("recommender-list-data")
	@ResponseBody
	@Permission(SystemRole.RECOMMENDER)
	public BootStrapResult<List<ShareMoney>> recommenderListData(Model model,
			@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request,
			HttpSession session) {
		Integer page = start / length;
		return this.shareMoneyService.getRecommenderList(page,length,request);
	}
}

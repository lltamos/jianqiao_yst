package com.alqsoft.controller.pc.after.order;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.rpc.RpcDoctorOrderService;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.service.doctor.DoctorService;
import com.alqsoft.service.order.DoctorOrderService;
import com.alqsoft.service.order.ProductOrderService;
import com.alqsoft.utils.CustomerUtils;

/**
 * 
 * @Description: TODO
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月12日 下午1:50:33 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@RequestMapping("/pc/after/order")
@Controller
public class OrderController {

	@Autowired
	private ProductOrderService productOrderService;
	@Autowired
	private DoctorOrderService doctorOrderService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private RpcDoctorOrderService rpcDocorOrder;

	/**
	 * 根据传入页码,type，查询在线订单
	 * 
	 * @param model
	 * @param cid
	 * @param type
	 *            0 在线咨询，1疑难杂症 ordertype 0 全部订单，1，已支付，2，待支付
	 */

	@RequestMapping("findOrdersList.do")
	public String findDoctorOrderList(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "4") Integer rows,
			@RequestParam(value = "type", defaultValue = "0") int type, HttpSession session,
			HttpServletRequest request) {

		List<Map<String, Object>> list = null;
		Long id = CustomerUtils.getCustomerId(session);

		if (id == null) {
			return "redirect:/pc/view/customer/to-login";
		}
		int totalPage = 0;

		if (type == 0) {
			list = doctorOrderService.getDoctorOrderAllList(page, rows, id);
			totalPage = doctorOrderService.getCountFormOrder(id);

			model.addAttribute("type", 0);
		} else if (type == 1) {
			list = productOrderService.getProductOrderAllList(page, rows, id);
			totalPage = productOrderService.getCountFormOrder(id);
			model.addAttribute("type", 1);
		}

		double totalPage2 = Math.ceil(totalPage / rows.doubleValue());// 向上取整
		Integer totalPage1 = (new Double(totalPage2)).intValue();
		System.out.println("totalPage:" + totalPage + "totalPage2" + totalPage2);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage1);

		model.addAttribute("ordertype", 0);

		model.addAttribute("list", list);
		model.addAttribute("modeltype", 0);

		session.setAttribute("lefttype", 0);

		return "after/usercenter/order/allorder";

	}

	/**
	 * 根据传入页码,type，查询 已支付订单
	 * 
	 * @param model
	 * @param cid
	 * @param type
	 *            0 在线咨询，1疑难杂症
	 */

	@RequestMapping("findPaidOrderList.do")
	public String findPaidOrderList(Model model,

			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "4") Integer rows,
			@RequestParam(value = "type", defaultValue = "0") int type, HttpSession session, HttpServletRequest request

	) {

		List<Map<String, Object>> list = null;
		int totalPage = 0;
		Long id = CustomerUtils.getCustomerId(session);

		if (id == null) {
			return "redirect:/pc/view/customer/to-login";
		}
		if (type == 0) {
			list = doctorOrderService.findDoctorOrderByStatusAndCId(page, rows, 1, id);
			totalPage = doctorOrderService.getCountFormOrderByStatus(id, 1);
			model.addAttribute("type", 0);
		} else if (type == 1) {
			list = productOrderService.findProductOrderByStatusAndCId(page, rows, 2, id);
			totalPage = productOrderService.getCountFormOrderByStatus(id, 2);
			model.addAttribute("type", 1);

		}

		double totalPage2 = Math.ceil(totalPage / rows.doubleValue());// 向上取整
		Integer totalPage1 = (new Double(totalPage2)).intValue();
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage1);
		model.addAttribute("ordertype", 1);

		model.addAttribute("modeltype", 0);

		model.addAttribute("list", list);
		session.setAttribute("lefttype", 0);
		return "after/usercenter/order/allorder";

	}

	/**
	 * 根据传入页码,type，查询 未支付订单
	 * 
	 * @param model
	 * @param cid
	 */

	@RequestMapping("findNoPaidOrderList.do")
	public String findNoPaidOrderList(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "4") Integer rows,
			@RequestParam(value = "type", defaultValue = "0") int type, HttpSession session

	) {

		List<Map<String, Object>> list = null;

		int totalPage = 0;
		Long id = CustomerUtils.getCustomerId(session);

		if (id == null) {
			return "redirect:/pc/view/customer/to-login";
		}
		if (type == 0) {
			list = doctorOrderService.findDoctorOrderByStatusAndCId(page, rows, 0, id);
			totalPage = doctorOrderService.getCountFormOrderByStatus(id, 0);
			model.addAttribute("type", 0);
		} else if (type == 1) {
			list = productOrderService.findProductOrderByStatusAndCId(page, rows, 0, id);
			totalPage = doctorOrderService.getCountFormOrderByStatus(id, 0);
			model.addAttribute("type", 1);

		}
		Customer customerById = customerService.getCustomerById(id);
		model.addAttribute("image", customerById.getImage());
		double totalPage2 = Math.ceil(totalPage / rows.doubleValue());// 向上取整
		Integer totalPage1 = (new Double(totalPage2)).intValue();
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage1);
		model.addAttribute("ordertype", 2);

		model.addAttribute("list", list);
		model.addAttribute("modeltype", 0);

		session.setAttribute("lefttype", 0);
		return "after/usercenter/order/allorder";

	}

	/**
	 * 
	 * @Title: findDoctorOrderList
	 * @Description: 医生个人中心订单查询 0在线 1服务包
	 * @author 腾卉
	 * @return String 返回类型
	 */
	@RequestMapping("findAllDoctorOrderList")
	public String findAllDoctorOrderList(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "4") Integer rows,
			@RequestParam(value = "type", defaultValue = "0") int type, HttpServletRequest request) {

		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if (customer == null) {
			return "/view/customer/login";
		}
		Long id = customer.getId();
		Customer customerById = customerService.getCustomerById(id);
		Doctors doctor = doctorService.findByCid(id);
		Long doctor_id = doctor.getId();
		// Long doctor_id = 1l;
		List<Map<String, Object>> list = null;
		Integer totalPage = 0;
		if (type == 0) {
			list = doctorOrderService.getDoctorOrderListByDoctorId(doctor_id, page, rows);
			totalPage = doctorOrderService.getOrderCountByDoctorId(doctor_id);
			model.addAttribute("type", 0);
		} else if (type == 1) {
			list = productOrderService.findProductOrderListByDoctorId(doctor_id, page, rows);
			totalPage = productOrderService.getCountOrderByDoctorId(doctor_id);
			model.addAttribute("type", 1);

		}
		double totalPage2 = Math.ceil(totalPage / rows.doubleValue());// 向上取整
		Integer totalPage1 = (new Double(totalPage2)).intValue();
		model.addAttribute("image", customerById.getImage());
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage1);
		model.addAttribute("list", list);
		model.addAttribute("style", "order");
		return "after/doctorimage/order-list";
	}

	@RequestMapping("delete")
	@ResponseBody
	public org.alqframework.result.Result deleteOrderByid(@RequestParam(value = "id") Long id) {

		if (rpcDocorOrder.delete(id)) {
			
			return ResultUtils.returnSuccess("");

		}

		return ResultUtils.returnError("删除失败");
	}

}

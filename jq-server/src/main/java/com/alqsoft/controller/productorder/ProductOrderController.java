package com.alqsoft.controller.productorder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.webmvc.springmvc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.UserTable;
import com.alqsoft.model.Permission;
import com.alqsoft.service.productorder.ProductOrderService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

/**
 * 服务包订单控制器
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2017
 * @create-time 2017-3-6 下午8:01:14
 * 
 */
@RequestMapping("productorder")
@Controller
public class ProductOrderController {

	@Autowired
	private ProductOrderService productOrderService;
	
	/**
	 * 跳转服务包订单列表
	 * @return
	 */
	@RequestMapping("productorder-list")
	@Permission(SystemRole.ADMIN)
	public String productorderList() {
		return "productorder/productorder-list";
	}
	
	/**
	 * 服务包订单列表分页
	 * @param model
	 * @param start
	 * @param length
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("productorder-list-data")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<ProductOrder>> productOrderListData(
			@RequestParam(value="payRelativeId", required=false)String payRelativeId,
			@RequestParam(value="customerPhone", required=false)String customerPhone,
			@RequestParam(value="recommPhone", required=false)String recommPhone,
			@RequestParam(value="productRecommPhone", required=false)String productRecommPhone,
			@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length
			) {
		Integer page = start / length;
		return this.productOrderService.getProductOrderList(payRelativeId, customerPhone, recommPhone, productRecommPhone, page, length);
	}
	
	/**
	 * 保存服务包订单
	 * @param model
	 * @param productorder
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("productorder-save")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result productOrderSave(Model model,
			ProductOrder productorder,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		UserTable dbUser=(UserTable) session.getAttribute(SystemRole.ADMIN.getName());
		return this.productOrderService.save(productorder,dbUser);
	}
	
	
	/**
	 * 跳转查看服务包订单
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("productorder-info")
	@Permission(SystemRole.ADMIN)
	public String productOrderInfo(Model model,
			@RequestParam(required=true,value="id")Long id,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		model.addAttribute("productOrder", this.productOrderService.get(id));
		return "productorder/productorder-info";
	}
}

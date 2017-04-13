package com.yst.web.controller.main.after;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.alqframework.webmvc.servlet.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.model.DoctorServiceOrder;
import com.yst.web.model.ProductOrder;
import com.yst.web.service.ProductOrderService;
import com.yst.web.utils.BootStrapResult;

/**
 * 
*   
* 项目名称：yst_web  
* 类名称：ProductOrderController  
* 类描述：订单  
* 创建人：whl  
* 创建时间：2016年2月22日 下午5:11:31  
* 修改人：whl  
* 修改时间：2016年2月22日 下午5:11:31  
* 修改备注：  
* @version   
*
 */
@RequestMapping("ydmvc/main/after")
@Controller
public class ProductOrderController {
	
	@Autowired
	private ProductOrderService productOrderService;

	
	@RequestMapping("getUpdateProductOrderStatus.do")
	@ResponseBody
	public Result getUpdateProductOrder(Model model,ProductOrder po,HttpServletRequest request,HttpServletResponse response){
		Result result=this.productOrderService.updateProductOrderStatus(po,request);;
		return result;
	}
	/**
	 * 跳转订单详情
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("viewProductOrderPage.do")
	public String getViewProductOrderPage(Model model,Integer id,HttpServletRequest request,HttpServletResponse response){
		ProductOrder po =this.productOrderService.findById(id);
		model.addAttribute("result", po);
		return "productOrder/view";
	}
	
	/**
	 * 跳转医生疑难杂症订单分润列表
	 * @return
	 */
	@RequestMapping("toDoctorShareMoneyPage.do")
	public String toDoctorShareMoneyPage(){
		
		return "doctor/productorder/productorderlist";
	}
	
	/**
	 * 跳转推广人分润列表
	 * @return
	 */
	@RequestMapping("toPushShareMoneyPage.do")
	public String toPushShareMoneyPage(){
		return "push/shareMoney/pushList";
	}
	
	@RequestMapping("findPushShareMoneyPage.do")
	public BootStrapResult<List<ProductOrder>> findPushShareMoneyPage(
			Model model,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer pageSize,
			@RequestParam(value="search",required=false) String search,
			HttpServletRequest request
			){
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		BootStrapResult<List<ProductOrder>> bootStrapResult = this.productOrderService.findPushShareMoneyPage(searchParams, start, pageSize,request);
		return bootStrapResult;
	}
	
}

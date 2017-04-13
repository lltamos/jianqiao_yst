package com.yst.web.controller.shareMoney;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.webmvc.servlet.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.entity.shareMoney.ShareMoney;
import com.yst.web.model.AppResult;
import com.yst.web.service.PatientOrderService;
import com.yst.web.service.ShareMoneyService;
import com.yst.web.utils.BootStrapResult;
import com.yst.web.utils.ServerParam;

/***
 * 分润记录controller
 * @author lgn
 *
 */
@RequestMapping("ydmvc/shareMoney")
@Controller
public class ShareMoneyController {
	
	private AppResult appResult = new AppResult();
	
	@Autowired
	private ShareMoneyService shareMoneyService;
	private ServerParam serverParam;
	
	@Autowired
	private PatientOrderService patientOrderService;
	public ServerParam getServerParam() {
		return serverParam;
	}
	public void setServerParam(ServerParam serverParam) {
		this.serverParam = serverParam;
	}
	/***
	 * 分润记录列表页面--管理员
	 * @return
	 */
	@RequestMapping("shareMoneyPage.do")
	public String getCashReceiverSationPage()
	{
		return "shareMoney/list";
	}
	/***
	 * 分润记录列表页面--商户
	 * @return
	 */
	@RequestMapping("MerchantsShareMoneyPage.do")
	public String MerchantsShareMoneyPage()
	{
		return "shareMoney/merchantsList";
	}
	/***
	 * 分润记录列表页面--普通用户
	 * @return
	 */
	@RequestMapping("CustomerShareMoneyPage.do")
	public String CustomerShareMoneyPage()
	{
		return "shareMoney/CustomerList";
	}
	
	/***
	 * 分润记录列表页面--推广人
	 * @return
	 */
	@RequestMapping("PushShareMoneyPage.do")
	public String customerShareMoneyPage()
	{
		return "shareMoney/pushList";
	}
	
	/***
	 * 分润记录列表--管理员
	 * @param model
	 * @param length
	 * @param start
	 * @return
	 */
	@RequestMapping("shareMoney.do")
	@ResponseBody
	public BootStrapResult getShareMoneyList(Model model,
			@RequestParam(value="start",defaultValue="1") Integer start,@RequestParam(value="length",defaultValue="5") Integer length,
			@RequestParam(value="search",required=false) String search,HttpServletRequest request)
	{
		Integer page=start/length;
		//request.setAttribute("search_LIKE_name", search);
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		BootStrapResult<List<ShareMoney>> bootStrapResult=shareMoneyService.getShareMoneyPage(searchParams, page, length);
		return bootStrapResult;
	}
	
	/***
	 * 订单详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("productOrderDetails.do")
	public String getCashReceiverSatationShow(Model model,@RequestParam("id") Long id)
	{
		return "cashReceiveStation/show";
	}
	
	/**
	 * 跳转患者订单详情
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toPatientOrderPage.do")
	public String getViewPatientPage(Model model,Long id,Long type,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> resultMap =this.patientOrderService.findByID(id);
		model.addAttribute("result", resultMap);
		request.setAttribute("type", type);
		return "shareMoney/toPatientOrder";
	}
	
}
package com.alqsoft.controller.pc.after.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.DoctorService;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.rpc.RpcDoctorService;
import com.alqsoft.rpc.RpcDoctorServiceOrderService;
import com.alqsoft.rpc.RpcDoctorServicesService;
import com.alqsoft.rpc.RpcProductOrderService;
import com.alqsoft.rpc.pay.RpcWeChatPayService;
import com.alqsoft.service.doctorserver.DoctorServerService;
import com.alqsoft.service.payservice.PayServerService;
import com.alqsoft.service.productdetails.ProductDetailsService;
import com.google.zxing.WriterException;

@RequestMapping("service")
@Controller
public class ServiceContorller {
	
	
	@Autowired
	private RpcDoctorService rpcDoctorService;
	@Autowired
	private ProductDetailsService productDetailsService;
	@Autowired
	private PayServerService parServerService;
	@Autowired
	private RpcDoctorServicesService rpcDoctorServicesService;
	
	private static Log logger = LogFactory.getLog(ServiceContorller.class);

	/**
	 * 跳转到服务包详情页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("to-package")
	public String toServicePackage(Model model,
			@RequestParam Long id,
			HttpServletRequest request){
		//获取服务包详情
		Map<String,Object> product = productDetailsService.getProductDetailsById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", id);
		Object productTypeId = product.get("service_type_id");
		map.put("serviceTypeId", productTypeId);
		Map<String, Object> serviceName = productDetailsService.getServiceNameByServiceTypeId(map);
		//服务包类型对应的医生
		List<Map<String, Object>> doctor = productDetailsService.getDoctorByProductTypeId(id);
		//服务包类型对应的患者日记
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("productTypeId", productTypeId);
		param.put("rows", 2);
		List<Map<String,Object>> diary = productDetailsService.getDiaryByProductTypeId(param);

		model.addAttribute("product", product);
		model.addAttribute("doctor", doctor);
		model.addAttribute("serviceName", serviceName);
		model.addAttribute("diary", diary);
		return "after/servicepackage/service";
	}

	/**
	 * 跳转到服务包支付页面
	 * @param model
	 * @param doctorid
	 * @param productid
	 * @param hospitalid
	 * @param productname
	 * @param doctorname
	 * @param depositeprice
	 * @param request
	 * @return
	 */
	@RequestMapping("to-pay")
	public String toServicePay(Model model,
			@RequestParam Long doctorid,
			HttpServletRequest request){
		
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(customer ==null){
			return "view/customer/login";
		}
		
		Doctors doctors = rpcDoctorService.getDoctorById(doctorid);
		logger.info("医生实体:"+doctors);
		//服务类型id
		Integer productTypeId = doctors.getProductTypeId();
		logger.info("服务类型id"+productTypeId);
		//医生名称
		String name = doctors.getName();
		logger.info("医生名称"+name);
		//医院id
		Long merchantId = doctors.getMerchantId();
		//服务包详情
		Map<String,Object> product = productDetailsService.getProductDetailsById(Long.valueOf(productTypeId+""));
		logger.info("服务包详情"+product);
		//服务包名称
		String productname = product.get("name")+"";
		//服务包价钱
		String string = product.get("deposite_price").toString();
		
		model.addAttribute("doctorid", doctorid);
		model.addAttribute("productid", productTypeId);
		model.addAttribute("hospitalid", merchantId);
		model.addAttribute("productname", productname);
		model.addAttribute("doctorname", name);
		model.addAttribute("price", string);
		
		return "after/servicepackage/servicepay";
	}
	/**
	 * 跳转到咨询订单页面
	 * @param model
	 * @param doctorid
	 * @param productid
	 * @param hospitalid
	 * @param productname
	 * @param doctorname
	 * @param depositeprice
	 * @param request
	 * @return
	 */
	@RequestMapping("to-serverpay")
	public String toServerServicePay(Model model,
			@RequestParam Long doctorid,
			HttpServletRequest request){
		Long price = null;
	/*	Map<String, Object> doctorServer = doctorServerService.getDoctorServicePrice(doctorid);*/
		DoctorService wwwmmm = rpcDoctorServicesService.getPriceByDoctorId(Integer.valueOf(doctorid+""));
		if(wwwmmm ==null){
			price =0L;
		}else{
			//在线咨询价钱
			Integer serverPrice = wwwmmm.getService_online_time_price();
			if(serverPrice == null || "".equals(serverPrice)){
				price =0L;
			}else{
				price = Long.valueOf(serverPrice);
			}
		}
		
		Doctors doctors = rpcDoctorService.getDoctorById(doctorid);
		//服务类型id
		Integer productTypeId = doctors.getProductTypeId();
		//医生名称
		String name = doctors.getName();
		//医院id
		Long merchantId = doctors.getMerchantId();
		//服务包详情
		Map<String,Object> product = productDetailsService.getProductDetailsById(Long.valueOf(productTypeId+""));
		//服务包名称
		String productname = product.get("name")+"";
	
		
		model.addAttribute("doctorid", doctorid);
		model.addAttribute("productid", productTypeId);
		model.addAttribute("hospitalid", merchantId);
		model.addAttribute("productname", productname);
		model.addAttribute("doctorname", name);
		model.addAttribute("price", price);
		return "after/servicepackage/servicepay2";
	}

	
	@RequestMapping("goto-pay")
	@ResponseBody
	public Result toPayService(Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam Long doctorid,
			@RequestParam Integer type,
			@RequestParam String tjr
			) throws Exception{
		Result result = new Result();

		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(customer == null){
			result.setCode(2);//code=2表示还没有登陆，跳转到登陆页面
			result.setContent("您还没有登陆，请登录！");
			return result;
		}
		
		Result result2 = parServerService.payServer(doctorid, type, customer,request,tjr);
		return result2;
	}
	


	@SuppressWarnings("null")
	@Test
	public void test1() throws WriterException, IOException{

		HttpServletRequest request = null;
		String realPath = request.getSession().getServletContext().getRealPath("/");
		File f = null;
		f = File.createTempFile("tmp", ".jpg", new File(realPath));



	}
	/**
	 * 生成不重复随机字符串包括字母数字
	 *
	 * @param len
	 * @return
	 */
	public static String generateRandomStr(int len) {
		//字符源，可以根据需要删减
		String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz";
		String rtnStr = "";
		for (int i = 0; i < len; i++) {
			//循环随机获得当次字符，并移走选出的字符
			String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
			rtnStr += nowStr;
			generateSource = generateSource.replaceAll(nowStr, "");
		}
		return rtnStr;
	}

	public static void main(String[] args) {
		/*  for (int i = 0; i < 10; i++) {
	        ///System.out.println(generateRandomStr(8));

	    }*/
		String a = generateRandomStr(8);
		System.out.println(a);
	}
}


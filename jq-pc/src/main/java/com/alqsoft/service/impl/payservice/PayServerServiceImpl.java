package com.alqsoft.service.impl.payservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.customer.CustomerDao;
import com.alqsoft.dao.merchant.MerchantDao;
import com.alqsoft.dao.order.DoctorOrderDao;
import com.alqsoft.dao.order.ProductOrderDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.DoctorService;
import com.alqsoft.entity.DoctorServiceOrder;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.Product;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.rpc.RpcDoctorOrderService;
import com.alqsoft.rpc.RpcDoctorService;
import com.alqsoft.rpc.RpcDoctorServiceOrderService;
import com.alqsoft.rpc.RpcDoctorServicesService;
import com.alqsoft.rpc.RpcProductOrderService;
import com.alqsoft.rpc.RpcProductService;
import com.alqsoft.rpc.pay.RpcWeChatPayService;
import com.alqsoft.service.doctorserver.DoctorServerService;
import com.alqsoft.service.payservice.PayServerService;
import com.alqsoft.service.productdetails.ProductDetailsService;
import com.alqsoft.utils.QrCode;
import com.google.zxing.WriterException;

@Service
@Transactional
public class PayServerServiceImpl implements PayServerService {

	@Autowired
	private RpcDoctorService rpcDoctorService;
	@Autowired
	private ProductDetailsService productDetailsService;
	@Autowired
	private RpcProductOrderService rpcProductOrderService;
	@Autowired
	private RpcDoctorServiceOrderService rpcDoctorServiceOrderService;

	@Autowired
	private RpcDoctorOrderService rpcDoctorOrderService;

	@Autowired
	private RpcWeChatPayService rpcWeChatPayService;
	@Autowired
	private DoctorServerService doctorServerService;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private RpcProductService rpcProductService;
	@Autowired
	private RpcDoctorServicesService rpcDoctorServicesService;

	@Autowired
	private ProductOrderDao productOrderDao;

	@Autowired
	private DoctorOrderDao doctorOrderDao;

	private static Log logger = LogFactory.getLog(PayServerServiceImpl.class);

	@Override
	public Result payServer(Long doctorId, Integer type, Customer customer, HttpServletRequest request, String tjr)
			throws Exception {
		// TODO Auto-generated method stub
		// /HttpServletRequest request = null;
		String price = null;
		String orderBianh = null;

		Result result = new Result();

		Date date = new Date();

		// 获取医生详情
		Doctors doctors = rpcDoctorService.getDoctorById(doctorId);
		Long customerId2 = doctors.getCustomerId();
		Customer customerById = customerDao.getCustomerById(customerId2);
		String doctorPhone = customerById.getPhone();
		// 获取医生的服务类型ID
		Integer productTypeId = doctors.getProductTypeId();
		// 获取服务包详情
		Map<String, Object> product = productDetailsService.getProductDetailsById(Long.valueOf(productTypeId + ""));
		if (product == null) {
			return ResultUtils.returnError("当前操作无效!");
		}
		String productname = product.get("name") + "";
		if (productname == null || productname.equals("")) {
			return ResultUtils.returnError("当前服务无效,不能购买,请联系客服人员!");
		}

		if (type == null || type.equals("")) {
			return ResultUtils.returnError("当前操作无效!");
		}
		// type=1为在线咨询订单,2为在线预约订单
		if (type == 1) {
			// 获取在线咨询详情
			/*
			 * Map<String, Object> doctorServer =
			 * doctorServerService.getDoctorServicePrice(doctorId);
			 * if(doctorServer ==null){ return
			 * ResultUtils.returnError("当前服务无效,不能购买,请联系客服人员!"); } String
			 * serverPrice = doctorServer.get("service_online_time_price")+"";
			 * if(serverPrice ==null || serverPrice.equals("")){ return
			 * ResultUtils.returnError("当前服务无效,不能购买,请联系客服人员!"); }
			 */
			DoctorService wwwmmm = rpcDoctorServicesService.getPriceByDoctorId(Integer.valueOf(doctorId + ""));
			if (wwwmmm == null) {
				// price ="1";
				return ResultUtils.returnError("当前服务无效,不能购买,请联系客服人员!");
			} else {
				// 在线咨询价钱
				Integer serverPrice = wwwmmm.getService_online_time_price();
				if (serverPrice == null || "".equals(serverPrice)) {
					// price ="1";
					return ResultUtils.returnError("当前服务无效,不能购买,请联系客服人员!");
				} else {
					price = serverPrice + "";
				}
			}
			DoctorServiceOrder doctorServiceOrder = new DoctorServiceOrder();

			// 生成订单编号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String str = sdf.format(date);
			Integer i = (int) ((Math.random() * 9 + 1) * 1000);
			String n = i.toString();
			orderBianh = str + n;

			doctorServiceOrder.setCreatedTime(new Date());
			doctorServiceOrder.setDoctorPhone(doctorPhone);
			doctorServiceOrder.setOrderId(orderBianh);
			doctorServiceOrder.setCustomerId(Integer.valueOf(customer.getId() + ""));
			doctorServiceOrder.setDoctorId(Integer.valueOf(doctorId + ""));
			doctorServiceOrder.setOrderDate(new Date());
			doctorServiceOrder.setPrice(Integer.valueOf(price));
			doctorServiceOrder.setExecuted(0);
			doctorServiceOrder.setDeleted(0);
			doctorServiceOrder.setPayStatus(0);
			doctorServiceOrder.setPay_type(3);
			doctorServiceOrder.setStr_customer_name(customer.getName());
			doctorServiceOrder.setCustomerPhone(customer.getPhone());
			doctorServiceOrder.setStr_service_type_name(productname);
			DoctorServiceOrder saveAndModify = rpcDoctorServiceOrderService.saveAndModify(doctorServiceOrder);

			if (saveAndModify == null) {
				return ResultUtils.returnSuccess("操作失败!");
			}
			rpcDoctorOrderService.lazyCensorOrder(saveAndModify.getId());
		} else if (type == 2) {

			String tjrphone = null;
			String tjrname = null;
			String trjtel = null;
			String tjraddress = null;
			Long tjrid = null;
			// 判断推荐人是否存在
			if (tjr != null) {
				if (tjr.equals("")) {
					tjrphone = "";
				} else {
					Customer customer2 = customerDao.getCustomerByPhone(tjr);
					if (customer2 == null) {
						result.setCode(9);
						result.setMsg("推荐人不存在,请确认后填写或不填!");
						return result;
					} else {
						tjrname = customer2.getName();
						trjtel = customer2.getPhone();
						tjraddress = customer2.getAddress();
						tjrid = customer2.getId();
					}
				}

			}

			// 服务包的价钱
			/*
			 * String string = product.get("deposite_price").toString();
			 * if(string ==null || string.equals("")){ return
			 * ResultUtils.returnError("当前服务无效,不能购买,请联系客服人员!"); }
			 */
			// 获取到当前的服务包详情
			Product productById = rpcProductService.getProductById(Long.valueOf(productTypeId));

			Long depositePrice = productById.getDepositePrice();
			if (depositePrice == null || depositePrice.equals("")) {
				return ResultUtils.returnError("当前服务无效,不能购买,请联系客服人员!");
			}
			price = depositePrice + "";

			ProductOrder productOrder = new ProductOrder();
			// 获取总院电话
			Long merchantId = (Long) product.get("merchant_id");
			Merchant merchant = merchantDao.getMerchantById(merchantId);
			productOrder.setMerchantPhone(merchant.getCustomerPhone());
			productOrder.setProductRecommName(productById.getCustomerName());
			productOrder.setProductRecommPhone(productById.getCustomerPhone());
			productOrder.setDivide(productById.getDivide());
			productOrder.setMerchantName(product.get("merchant_name") + "");
			productOrder.setTotalPrice(productById.getTotalPrice());
			productOrder.setDoctorId(doctorId);
			productOrder.setProductId(Long.valueOf(productTypeId));
			productOrder.setMerchantId(doctors.getMerchantId());
			productOrder.setProductName(product.get("name") + "");
			productOrder.setDepositePrice(depositePrice);
			productOrder.setCreatedTime(new Date());
			productOrder.setCount(1);
			productOrder.setPayStatus(0);
			productOrder.setRecommName(tjrname);
			productOrder.setRecommPhone(trjtel);
			productOrder.setRecommAddress(tjraddress);
			productOrder.setCreatedTime(new Date());
			productOrder.setMarketPrice(productById.getMarketPrice());
			productOrder.setPayType(3);
			productOrder.setFee1(productById.getFee1());
			productOrder.setFee2(productById.getFee2());
			productOrder.setFee3(productById.getFee3());
			productOrder.setFee4(productById.getFee4());
			productOrder.setFee5(productById.getFee5());
			productOrder.setFeePayStatus1(0);
			productOrder.setFeePayStatus2(0);
			productOrder.setFeePayStatus3(0);
			productOrder.setFeePayStatus4(0);
			productOrder.setFeePayStatus5(0);
			productOrder.setFeeUpdateTime1(new Date());
			productOrder.setFeeUpdateTime2(new Date());
			productOrder.setFeeUpdateTime3(new Date());
			productOrder.setFeeUpdateTime4(new Date());
			productOrder.setFeeUpdateTime5(new Date());
			productOrder.setZipCode("");
			productOrder.setPatientName(customer.getName());
			productOrder.setPatientPhone(customer.getPhone());
			productOrder.setPatientAddress(customer.getAddress());
			Merchant merchantById = merchantDao.getMerchantById(productById.getMerchantId());
			productOrder.setMerchantAddress("");
			// productOrder.setProductName(productById.getProductTypeName());
			productOrder.setDoctorPhone(doctorPhone);
			productOrder.setRecommId(tjrid);
			productOrder.setProductRecommId(productById.getCustomerId());

			// 生成订单编号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String str = sdf.format(date);
			Integer i = (int) ((Math.random() * 9 + 1) * 1000);
			String n = i.toString();
			orderBianh = str + n;

			// 订单编号
			productOrder.setPayRelativeId(orderBianh);
			Long customerid = customer.getId();
			productOrder.setCustomerId(customerid);
			String customername = customer.getName();
			productOrder.setCustomerName(customername);
			String phone = customer.getPhone();
			productOrder.setCustomerPhone(phone);
			ProductOrder addProductOrder = rpcProductOrderService.addProductOrder(productOrder);

			if (addProductOrder == null) {
				return ResultUtils.returnSuccess("操作失败!");
			}

			rpcProductOrderService.lazyCensorOrder(addProductOrder.getId());
		} else {
			return ResultUtils.returnSuccess("操作失败!");
		}

		String url = rpcWeChatPayService.sendCodeUnifiedOrder(orderBianh, productname, price, "NATIVE", "127.0.0.1",
				type);

		InputStream qrCodeUtil = QrCode.qrCodeUtil(url);
		String realPath = request.getSession().getServletContext().getRealPath("/") + "\\img" + "\\erweim";
		File f = null;
		f = File.createTempFile("tmp", ".jpg", new File(realPath));
		String name = f.getName();
		String a = "/erweim/";
		String url1 = "/img/erweim/" + name;
		// String path = f.getAbsolutePath();
		FileOutputStream out = new FileOutputStream(f);
		int b = 0;
		byte[] buffer = new byte[1024];
		while ((b = qrCodeUtil.read(buffer)) != -1) {
			// 4.写到输出流(out)中
			out.write(buffer, 0, b);
		}
		// D:\workSpace\jq-pc\src\main\webapp\erweim\tmp8269968046324758412.jpg

		result.setCode(5);
		result.setMsg(url1);
		return result;

	}

	@Override
	public Result getErWeiMa(Long doctorId, Integer type, Customer customer, HttpServletRequest request, String tjr)
			throws Exception {
		// TODO Auto-generated method stub
		// /HttpServletRequest request = null;
		String price = null;
		String orderBianh = null;

		Result result = new Result();

		Date date = new Date();

		// 获取医生详情
		Doctors doctors = rpcDoctorService.getDoctorById(doctorId);
		Long customerId2 = doctors.getCustomerId();
		Customer customerById = customerDao.getCustomerById(customerId2);
		String doctorPhone = customerById.getPhone();
		// 获取医生的服务类型ID
		Integer productTypeId = doctors.getProductTypeId();
		// 获取服务包详情
		Map<String, Object> product = productDetailsService.getProductDetailsById(Long.valueOf(productTypeId + ""));
		if (product == null) {
			return ResultUtils.returnError("当前操作无效!");
		}
		String productname = product.get("name") + "";
		if (productname == null || productname.equals("")) {
			return ResultUtils.returnError("当前服务无效,不能购买,请联系客服人员!");
		}

		if (type == null || type.equals("")) {
			return ResultUtils.returnError("当前操作无效!");
		}
		// type=1为在线咨询订单,2为在线预约订单
		if (type == 1) {
			DoctorService wwwmmm = rpcDoctorServicesService.getPriceByDoctorId(Integer.valueOf(doctorId + ""));
			if (wwwmmm == null) {
				// price ="1";
				return ResultUtils.returnError("当前服务无效,不能购买,请联系客服人员!");
			} else {
				// 在线咨询价钱
				Integer serverPrice = wwwmmm.getService_online_time_price();
				if (serverPrice == null || "".equals(serverPrice)) {
					// price ="1";
					return ResultUtils.returnError("当前服务无效,不能购买,请联系客服人员!");
				} else {
					price = serverPrice + "";
				}
			}

			// 根据map中的属性获取获取订单
			Map<String, Object> map = new HashMap<>();
			map.put("customerId", customer.getId());
			map.put("productName", productname);
			map.put("doctorId", doctors.getId());
			DoctorServiceOrder doctorServiceOrder2 = doctorOrderDao.getDoctorOrder(map);
			if (doctorServiceOrder2 != null) {
				return erWeiMa(type, request, doctorServiceOrder2.getPrice() + "", doctorServiceOrder2.getOrderId(),
						result, productname);
			}
			DoctorServiceOrder doctorServiceOrder = new DoctorServiceOrder();
			// 生成订单编号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String str = sdf.format(date);
			Integer i = (int) ((Math.random() * 9 + 1) * 1000);
			String n = i.toString();
			orderBianh = str + n;

			doctorServiceOrder.setCreatedTime(new Date());
			doctorServiceOrder.setDoctorPhone(doctorPhone);
			doctorServiceOrder.setOrderId(orderBianh);
			doctorServiceOrder.setCustomerId(Integer.valueOf(customer.getId() + ""));
			doctorServiceOrder.setDoctorId(Integer.valueOf(doctorId + ""));
			doctorServiceOrder.setOrderDate(new Date());
			doctorServiceOrder.setPrice(Integer.valueOf(price));
			doctorServiceOrder.setExecuted(0);
			doctorServiceOrder.setDeleted(0);
			doctorServiceOrder.setPayStatus(0);
			doctorServiceOrder.setPay_type(3);
			doctorServiceOrder.setStr_customer_name(customer.getName());
			doctorServiceOrder.setCustomerPhone(customer.getPhone());
			doctorServiceOrder.setStr_service_type_name(productname);
			DoctorServiceOrder saveAndModify = rpcDoctorServiceOrderService.saveAndModify(doctorServiceOrder);

			if (saveAndModify == null) {
				return ResultUtils.returnSuccess("操作失败!");
			}

			rpcDoctorOrderService.lazyCensorOrder(saveAndModify.getId());
		} else if (type == 2) {

			String tjrphone = null;
			String tjrname = null;
			String trjtel = null;
			String tjraddress = null;
			Long tjrid = null;
			// 判断推荐人是否存在
			if (tjr != null) {
				if (tjr.equals("")) {
					tjrphone = "";
				} else {
					Customer customer2 = customerDao.getCustomerByPhone(tjr);
					if (customer2 == null) {
						result.setCode(9);
						result.setMsg("推荐人不存在,请确认后填写或不填!");
						return result;
					} else {
						tjrname = customer2.getName();
						trjtel = customer2.getPhone();
						tjraddress = customer2.getAddress();
						tjrid = customer2.getId();
					}
				}

			}

			// 服务包的价钱
			/*
			 * String string = product.get("deposite_price").toString();
			 * if(string ==null || string.equals("")){ return
			 * ResultUtils.returnError("当前服务无效,不能购买,请联系客服人员!"); }
			 */
			// 获取到当前的服务包详情
			Product productById = rpcProductService.getProductById(Long.valueOf(productTypeId));
			Map<String, Object> params = new HashMap<>();
			params.put("doctorId", doctors.getId());
			params.put("customerId", customer.getId());
			params.put("productName", productname);
			params.put("productId", productTypeId);
			ProductOrder productOrder2 = productOrderDao.getProductOrder(params);
			if (null != productOrder2) {
				return erWeiMa(type, request, productOrder2.getDepositePrice() + "", productOrder2.getPayRelativeId(),
						result, productname);
			}

			Long depositePrice = productById.getDepositePrice();
			if (depositePrice == null || depositePrice.equals("")) {
				return ResultUtils.returnError("当前服务无效,不能购买,请联系客服人员!");
			}
			price = depositePrice + "";

			ProductOrder productOrder = new ProductOrder();
			// 获取总院电话
			Long merchantId = (Long) product.get("merchant_id");
			Merchant merchant = merchantDao.getMerchantById(merchantId);
			productOrder.setMerchantPhone(merchant.getCustomerPhone());
			productOrder.setProductRecommName(productById.getCustomerName());
			productOrder.setProductRecommPhone(productById.getCustomerPhone());
			productOrder.setDivide(productById.getDivide());
			productOrder.setMerchantName(product.get("merchant_name") + "");
			productOrder.setTotalPrice(productById.getTotalPrice());
			productOrder.setDoctorId(doctorId);
			productOrder.setProductId(Long.valueOf(productTypeId));
			productOrder.setMerchantId(doctors.getMerchantId());
			productOrder.setProductName(product.get("name") + "");
			productOrder.setDepositePrice(depositePrice);
			productOrder.setCreatedTime(new Date());
			productOrder.setCount(1);
			productOrder.setPayStatus(0);
			productOrder.setRecommName(tjrname);
			productOrder.setRecommPhone(trjtel);
			productOrder.setRecommAddress(tjraddress);
			productOrder.setCreatedTime(new Date());
			productOrder.setMarketPrice(productById.getMarketPrice());
			productOrder.setPayType(3);
			productOrder.setFee1(productById.getFee1());
			productOrder.setFee2(productById.getFee2());
			productOrder.setFee3(productById.getFee3());
			productOrder.setFee4(productById.getFee4());
			productOrder.setFee5(productById.getFee5());
			productOrder.setFeePayStatus1(0);
			productOrder.setFeePayStatus2(0);
			productOrder.setFeePayStatus3(0);
			productOrder.setFeePayStatus4(0);
			productOrder.setFeePayStatus5(0);
			productOrder.setFeeUpdateTime1(new Date());
			productOrder.setFeeUpdateTime2(new Date());
			productOrder.setFeeUpdateTime3(new Date());
			productOrder.setFeeUpdateTime4(new Date());
			productOrder.setFeeUpdateTime5(new Date());
			productOrder.setZipCode("");
			productOrder.setPatientName(customer.getName());
			productOrder.setPatientPhone(customer.getPhone());
			productOrder.setPatientAddress(customer.getAddress());
			Merchant merchantById = merchantDao.getMerchantById(productById.getMerchantId());
			productOrder.setMerchantAddress("");
			// productOrder.setProductName(productById.getProductTypeName());
			productOrder.setDoctorPhone(doctorPhone);
			productOrder.setRecommId(tjrid);
			productOrder.setProductRecommId(productById.getCustomerId());

			// 生成订单编号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String str = sdf.format(date);
			Integer i = (int) ((Math.random() * 9 + 1) * 1000);
			String n = i.toString();
			orderBianh = str + n;

			// 订单编号
			productOrder.setPayRelativeId(orderBianh);
			Long customerid = customer.getId();
			productOrder.setCustomerId(customerid);
			String customername = customer.getName();
			productOrder.setCustomerName(customername);
			String phone = customer.getPhone();
			productOrder.setCustomerPhone(phone);
			ProductOrder addProductOrder = rpcProductOrderService.addProductOrder(productOrder);
			if (addProductOrder == null) {
				return ResultUtils.returnSuccess("操作失败!");
			}
			rpcProductOrderService.lazyCensorOrder(addProductOrder.getId());
		} else {
			return ResultUtils.returnSuccess("操作失败!");
		}

		return erWeiMa(type, request, price, orderBianh, result, productname);

	}

	private Result erWeiMa(Integer type, HttpServletRequest request, String price, String orderBianh, Result result,
			String productname) throws WriterException, IOException, FileNotFoundException {
		String url = rpcWeChatPayService.sendCodeUnifiedOrder(orderBianh, productname, price, "NATIVE", "127.0.0.1",
				type);
		if(null == url ||"".equals(url)){
			return ResultUtils.returnError("当前操作过于频繁,请稍后操作!");
		}
		InputStream qrCodeUtil = QrCode.qrCodeUtil(url);
		String realPath = request.getSession().getServletContext().getRealPath("/") + "\\img" + "\\erweim";
		File f = null;
		f = File.createTempFile("tmp", ".jpg", new File(realPath));
		String name = f.getName();
		String a = "/erweim/";
		String url1 = "/img/erweim/" + name;
		String path = f.getAbsolutePath();
		FileOutputStream out = new FileOutputStream(f);
		int b = 0;
		byte[] buffer = new byte[1024];
		while ((b = qrCodeUtil.read(buffer)) != -1) {
			// 4.写到输出流(out)中
			out.write(buffer, 0, b);
		}
		// D:\workSpace\jq-pc\src\main\webapp\erweim\tmp8269968046324758412.jpg
		result.setCode(5);
		result.setMsg(url1);
		result.setContent(orderBianh);
		return result;
	}

	@Override
	public Result getDoctorService(Long doctorId) {
		Result result = new Result();
		DoctorService doctorService = rpcDoctorServicesService
				.getPriceByDoctorId(Integer.parseInt(doctorId.toString()));
		if (null != doctorService) {
			Integer onoff = doctorService.getService_online_time_onoff();
			if (null == onoff) {
				result.setCode(3);
				return result;
			} else if (onoff == 0) {
				result.setCode(4);
				return result;
			} else {
				result.setCode(3);
				return result;
			}
		} else {
			result.setCode(3);
			return result;
		}
	}
}

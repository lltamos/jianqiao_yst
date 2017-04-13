package com.yst.web.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.alipay.config.AlipayConfig;
import com.yst.web.alipay.util.AlipayCore;
import com.yst.web.alipay.util.AlipayNotify;
import com.yst.web.alipay.util.AlipaySubmit;
import com.yst.web.alipay.util.UtilDate;
import com.yst.web.dao.AlipayDao;
import com.yst.web.dao.systemalipayrefund.SystemAlipayRefundDao;
import com.yst.web.entity.systemalipayrefund.SystemAlipayRefund;
import com.yst.web.model.Alipay;
import com.yst.web.model.AppResult;
import com.yst.web.service.AlipayService;
import com.yst.web.service.BalanceTransService;
import com.yst.web.service.DoctorServiceOrderService;
import com.yst.web.service.ProductOrderService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.ServerParam;

@Service("alipayService")
@Transactional
public class AlipayServiceImpl implements AlipayService {
	private static Log logger = LogFactory.getLog(AlipayServiceImpl.class);
	@Resource(name = "balanceTransService")
	private BalanceTransService balanceTransService;
	@Resource(name = "productOrderService")
	private ProductOrderService productOrderService;
	@Resource(name = "doctorServiceOrderService")
	private DoctorServiceOrderService doctorServiceOrderService;
	@Resource(name = "alipayDao")
	private AlipayDao alipayDao;
	@Autowired
	private SystemAlipayRefundDao systemAlipayRefundDao;

	/*
	 * * 功能：支付宝服务器异步通知页面 版本：3.3 日期：2012-08-17 说明：
	 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
	 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	 * 
	 * //***********页面功能说明*********** 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
	 * 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
	 * 该页面调试工具请使用写文本函数logResult，该函数在com.alipay.util文件夹的AlipayNotify.java类文件中
	 * 如果没有收到该页面返回的 success 信息，支付宝会在24小时内按一定的时间策略重发通知
	 * //********************************
	 */
	@Override
	public void alipayNotify(Alipay alipay, HttpServletRequest request, HttpServletResponse response, Map session)
			throws Exception {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号

		String out_trade_no = alipay.getOut_trade_no();

		// 支付宝交易号

		String trade_no = alipay.getTrade_no();

		// 交易状态
		String trade_status = alipay.getTrade_status();

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if (AlipayNotify.verify(params)) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				logger.debug("异步支付宝回调_TRADE_FINISHED_返回回来的参数：" + AlipayCore.createLinkString(params));
				// 注意：
				// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				// 返回数据保存数据库
				Alipay dbAlipay = this.alipayDao.findByColumnValue(Alipay.class, "send_out_trade_no", out_trade_no);
				if (dbAlipay == null) {
					logger.error("订单号：" + out_trade_no + "不存在,支付宝流水号：" + alipay.getTrade_no() + ",支付金额："
							+ alipay.getTotal_fee() + ",支付宝账号：" + alipay.getBuyer_email());
				} else {
					// 保存回调参数到数据库
					BeanUtils.copy(alipay, dbAlipay);
					this.alipayDao.saveOrUpdate(dbAlipay);
					// 余额充值 = 0,
					// 服务包付款 = 1,
					// 医生服务付款 = 2,
					// 健康中心付款 = 3,
					// 业务逻辑
					logger.debug("支付类型:" + dbAlipay.getAlipay_act() + "，订单号:" + out_trade_no + ",支付成功时间："
							+ alipay.getGmt_payment());
					if (dbAlipay.getAlipay_act() == 0) {// 余额充值
						this.balanceTransService.alipayNotify(out_trade_no, alipay.getGmt_payment(), 1);
					} else if (dbAlipay.getAlipay_act() == 1) {// 服务包付款
						this.productOrderService.alipayNotify(out_trade_no, alipay.getGmt_payment(), 1);
					} else if (dbAlipay.getAlipay_act() == 2) {// 医生服务付款
						this.doctorServiceOrderService.alipayNotify(out_trade_no, alipay.getGmt_payment(), 1);
					} else if (dbAlipay.getAlipay_act() == 3) {// 健康中心付款
					}

				}
				// 注意：
				// 付款完成后，支付宝系统发送该交易状态通知
			}
			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			logger.debug("异步支付宝回调验证成功");
			response.getOutputStream().println("success"); // 请不要修改或删除

			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
			// Alipay dbAlipay = this.alipayDao.findByColumnValue(
			// Alipay.class, "send_out_trade_no", out_trade_no);
			// if (dbAlipay == null) {
			// logger.error("订单号：" + out_trade_no + "不存在,支付宝流水号："
			// + alipay.getTrade_no() + ",支付金额："
			// + alipay.getTotal_fee() + ",支付宝账号："
			// + alipay.getBuyer_email());
			// } else {
			// logger.debug("支付类型:" + dbAlipay.getAlipay_act() + "，订单号:"
			// + out_trade_no + ",支付成功时间："
			// + alipay.getGmt_payment());
			// if (dbAlipay.getAlipay_act() == 0) {// 余额充值
			// this.balanceTransService.alipayNotify(out_trade_no,
			// alipay.getGmt_payment());
			// }
			// }
			logger.debug("异步支付宝回调验证失败");
			response.getOutputStream().println("fail");
		}
	}

	@Override
	public void alipayReturn(Alipay alipay, HttpServletRequest request, HttpServletResponse response, Map session)
			throws Exception {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号

		String out_trade_no = alipay.getOut_trade_no();

		// 支付宝交易号

		String trade_no = alipay.getTrade_no();

		// 交易状态
		String trade_status = alipay.getTrade_status();

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if (AlipayNotify.verify(params)) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				// 返回数据保存数据库
				Alipay dbAlipay = this.alipayDao.findByColumnValue(Alipay.class, "send_out_trade_no", out_trade_no);
				if (dbAlipay == null) {
					logger.error("订单号：" + out_trade_no + "不存在,支付宝流水号：" + alipay.getTrade_no() + ",支付金额："
							+ alipay.getTotal_fee() + ",支付宝账号：" + alipay.getBuyer_email());
				} else {
					// 保存回调参数到数据库
					BeanUtils.copy(alipay, dbAlipay);
					this.alipayDao.saveOrUpdate(dbAlipay);
					// 余额充值 = 0,
					// 服务包付款 = 1,
					// 医生服务付款 = 2,
					// 健康中心付款 = 3,
					// 业务逻辑
					logger.debug("支付类型:" + dbAlipay.getAlipay_act() + "，订单号:" + out_trade_no + ",支付成功时间："
							+ alipay.getGmt_payment());
					if (dbAlipay.getAlipay_act() == 0) {// 余额充值
						this.balanceTransService.alipayNotify(out_trade_no, alipay.getGmt_payment(), 1);
					} else if (dbAlipay.getAlipay_act() == 1) {// 服务包付款
						this.productOrderService.alipayNotify(out_trade_no, alipay.getGmt_payment(), 1);
					} else if (dbAlipay.getAlipay_act() == 2) {// 医生服务付款
						this.doctorServiceOrderService.alipayNotify(out_trade_no, alipay.getGmt_payment(), 1);
					} else if (dbAlipay.getAlipay_act() == 3) {// 健康中心付款
					}
				}
				// 注意：
				// 付款完成后，支付宝系统发送该交易状态通知
			}

			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			logger.debug("同步支付宝回调验证成功");
			response.getOutputStream().println("success"); // 请不要修改或删除

			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
			logger.debug("同步支付宝回调验证失败");
			response.getOutputStream().println("fail");
		}
	}

	public AppResult addAlipayNO(AppResult appResult, String out_trade_no, String body, Integer total_fee,
			Integer alipay_act, HttpServletRequest request) {
		// 支付类型
		String payment_type = "1";
		// 必填，不能修改
		// 服务器异步通知页面路径
		String notify_url = ServerParam.DOMAIN + "/yst_web/alipay!alipayNotify";
		// 需http://格式的完整路径，不能加?id=123这类自定义参数

		// 客户端的IP地址
		String exter_invoke_ip = CommUtils.getIpAddr(request);
		// 发送信息保存数据库
		Alipay alipay = new Alipay();
		alipay.setSend_out_trade_no(out_trade_no);
		alipay.setAlipay_act(alipay_act);
		// 订单号
		alipay.setSend_total_fee(total_fee);
		alipay.setSend_subject("健桥_" + ServerParam.ALIPAY_ACT[alipay_act]);// 订单名称
		alipay.setSend_body(body);// 订单描述
		alipay.setSend_payment_type(payment_type);
		alipay.setExter_invoke_ip(exter_invoke_ip);
		alipay.setCreate_time(new Date(System.currentTimeMillis()));
		this.alipayDao.saveOrUpdate(alipay);
		appResult.setError_info("保存成功，等待付款");
		appResult.setData(alipay);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	/*
	 * * 功能：支付宝服务器异步通知页面 版本：3.3 日期：2012-08-17 说明：
	 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
	 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	 * 
	 * //***********页面功能说明*********** 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
	 * 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
	 * 该页面调试工具请使用写文本函数logResult，该函数在com.alipay.util文件夹的AlipayNotify.java类文件中
	 * 如果没有收到该页面返回的 success 信息，支付宝会在24小时内按一定的时间策略重发通知
	 * //********************************
	 */
	public void alipayRefundNotify(Alipay alipay, HttpServletRequest request, HttpServletResponse response)
			{
		// 获取支付宝POST过来反馈信息
		try {
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 批次号

			String batch_no = new String(request.getParameter("batch_no").getBytes("ISO-8859-1"), "UTF-8");

			// 批量退款数据中转账成功的笔数

			String success_num = new String(request.getParameter("success_num").getBytes("ISO-8859-1"), "UTF-8");

			// 批量退款数据中的详细信息
			/*
			 * 退手续费结果返回格式：交 易号^退款金额^处理结果 $退费账号^退费账户 ID^退 费金额^处理结果； z 不退手续费结果返回格式：
			 * 交易号^退款金额^处理结果
			 */
			String result_details = new String(request.getParameter("result_details").getBytes("ISO-8859-1"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

			if (AlipayNotify.verify(params)) {// 验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

				// 判断是否在商户网站中已经做过了这次通知返回的处理
				// 如果没有做过处理，那么执行商户的业务程序
				// 如果有做过处理，那么不执行商户的业务程序
				logger.debug("退款异步支付宝回调验证成功");
				response.getOutputStream().println("success"); // 请不要修改或删除

				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				SystemAlipayRefund sar = this.systemAlipayRefundDao.getRefundByBatchNo(batch_no);
				if (sar == null) {
					logger.error("批次号：" + batch_no + "，成功笔数：" + success_num + "，退款结果：" + result_details + "，批次号不存在");
				}
				sar.setSuccessNum(success_num);
				sar.setResultDetails(result_details);
				if(success_num.equals("1")){
					sar.setStatus("1");
					this.productOrderService.alipayRefundNotify(result_details);
				}else{
					sar.setStatus("0");
					logger.error("批次号：" + batch_no + "，成功笔数：" + success_num + "，退款结果：" + result_details + "，退款失败");
				}
				this.systemAlipayRefundDao.save(sar);
				//////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				logger.error("批次号：" + batch_no + "，成功笔数：" + success_num + "，退款结果：" + result_details + "，退款异步支付宝回调验证失败");
				response.getOutputStream().println("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("退款异步支付宝回调验证失败");
			try {
				response.getOutputStream().println("fail");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/*
	 * * 功能：即时到账批量退款有密接口接入页 版本：3.3 日期：2012-08-14 说明：
	 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
	 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	 ************************* 
	 * 注意***************** 如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
	 * 1、商户服务中心（https://b.alipay.com/support/helperApply.htm?action=
	 * consultationApply），提交申请集成协助，我们会有专业的技术工程师主动联系您协助解决
	 * 2、商户帮助中心（http://help.alipay.com/support/232511-16307/0-16307.htm?sh=Y&
	 * info_type=9） 3、支付宝论坛（http://club.alipay.com/read-htm-tid-8681712.html）
	 * 如果不想使用扩展功能请把扩展功能参数赋空值。
	 **********************************************
	 */
	public Result addRefundNo(String order_num, Double money) {
		//////////////////////////////////// 请求参数//////////////////////////////////////
		// 建立请求
		String sHtmlText = "";
		try {
			// 服务器异步通知页面路径
			String notify_url = ServerParam.BASE_PATH + "alipay!alipayRefundNotify";
			// 需http://格式的完整路径，不允许加?id=123这类自定义参数

			// 卖家支付宝帐户
			String seller_email = AlipayConfig.seller_email;
			// 必填

			// 退款当天日期
			String refund_date = UtilDate.getDateFormatter();
			// 必填，格式：年[4位]-月[2位]-日[2位] 小时[2位 24小时制]:分[2位]:秒[2位]，如：2007-10-01
			// 13:13:13

			// 批次号
			String batch_no = UtilDate.getOrderNum() + UtilDate.getThree();
			// 必填，格式：当天日期[8位]+序列号[3至24位]，如：201008010000001

			// 退款笔数
			String batch_num = "1";// 默认1笔
			// 必填，参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的数量999个）

			// 退款详细数据 单笔数据集格式为：第一笔交易退款数据集#第二笔交易退款数据集#第三笔交易退款数据集…#第 N 笔交易退款数据集
			// 交易退款数据集的格式为：原付款支付宝交易号^退款总金额^退款理由
			String detail_data = order_num + "^" + money + "协商退款";
			// 必填，具体格式请参见接口技术文档

			//////////////////////////////////////////////////////////////////////////////////

			// 把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("seller_email", seller_email);
			sParaTemp.put("refund_date", refund_date);
			sParaTemp.put("batch_no", batch_no);
			sParaTemp.put("batch_num", batch_num);
			sParaTemp.put("detail_data", detail_data);
			SystemAlipayRefund sar = new SystemAlipayRefund();
			sar.setService("refund_fastpay_by_platform_pwd");
			sar.setPartner(AlipayConfig.partner);
			sar.setInputCharset(AlipayConfig.input_charset);
			sar.setNotifyUrl(notify_url);
			sar.setSellerEmail(seller_email);
			sar.setRefunDate(refund_date);
			sar.setBatchNo(batch_no);
			sar.setBatchNum(batch_num);
			sar.setDetailData(detail_data);
			this.systemAlipayRefundDao.save(sar);
			sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResultUtils.returnSuccess("申请退款失败,"+e.getMessage());
		}
		logger.info(sHtmlText);
		return ResultUtils.returnSuccess("申请退款成功");
	}
}

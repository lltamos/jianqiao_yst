package com.yst.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.AlipayDao;
import com.yst.web.dao.BalanceDao;
import com.yst.web.dao.BalanceTransDao;
import com.yst.web.dao.CustomerDao;
import com.yst.web.model.Alipay;
import com.yst.web.model.AppResult;
import com.yst.web.model.Balance;
import com.yst.web.model.BalanceTrans;
import com.yst.web.model.Customer;
import com.yst.web.model.PageModel;
import com.yst.web.service.AlipayService;
import com.yst.web.service.BalanceTransService;
import com.yst.web.service.DoctorServiceOrderService;
import com.yst.web.service.ProductOrderService;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;
import com.yst.web.utils.UtilDate;

@Service("balanceTransService")
@Transactional
public class BalanceTransServiceImpl implements BalanceTransService {
	private static Log logger = LogFactory
			.getLog(BalanceTransServiceImpl.class);
	@Resource(name = "customerDao")
	private CustomerDao customerDao;
	@Resource(name = "balanceDao")
	private BalanceDao balanceDao;
	@Resource(name = "balanceTransDao")
	private BalanceTransDao balanceTransDao;
	@Resource(name = "alipayDao")
	private AlipayDao alipayDao;
	@Resource(name = "alipayService")
	private AlipayService alipayService;
	@Resource(name = "productOrderService")
	private ProductOrderService productOrderService;
	@Resource(name = "doctorServiceOrderService")
	private DoctorServiceOrderService doctorServiceOrderService;
	@Override
	public BalanceTrans findById(int id) {
		return this.balanceTransDao.get(BalanceTrans.class, id);
	}

	@Override
	public List<BalanceTrans> selectAll() {
		return this.balanceTransDao.query(BalanceTrans.class);
	}

	@Override
	public void deleteById(int id) {
		this.balanceTransDao.delete(BalanceTrans.class, id);
	}

	@Override
	public void update(BalanceTrans balanceTrans) {
		this.balanceTransDao.update(balanceTrans);
	}

	@Override
	public void add(BalanceTrans balanceTrans) {
		this.balanceTransDao.save(balanceTrans);
	}

	@Override
	public AppResult updateBalance(BalanceTrans balanceTrans,
			HttpServletResponse response, HttpServletRequest request) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer customer_id = balanceTrans.getCustomer_id();
		if (customer_id == null || customer_id.equals("")) {
			appResult.setError_info("用户id不能为空");
			return appResult;
		}
		Customer dbCustomer = this.customerDao.get(Customer.class,
				balanceTrans.getCustomer_id());
		Integer balance = balanceTrans.getBalance();
		if (balance == null || balance.equals("")) {
			appResult.setError_info("金额为空或错误");
			return appResult;
		}
		if (dbCustomer == null) {
			appResult.setError_info("用户id不存在或错误");
		} else {
			Balance dbBalance = this.balanceDao.findByColumnValue(
					Balance.class, "customer.customer_id",
					balanceTrans.getCustomer_id());
			if (dbBalance == null) {
				appResult.setError_info("数据异常");
			} else {
				Integer remain = dbBalance.getBalance_remain();
				balanceTrans.setCreate_by(0);// system身份
				balanceTrans.setBalance_remain(remain + balance);
				balanceTrans.setCustomer(dbCustomer);
				balanceTrans.setCreate_time(new Date());
				// 调用支付宝接口
				String out_trade_no = UtilDate.getOrderNum()
						+ UtilDate.getThree();// 生成订单号
				balanceTrans.setPay_relative_id(out_trade_no);
				this.balanceTransDao.saveOrUpdate(balanceTrans);
				appResult = this.alipayService.addAlipayNO(appResult,
						out_trade_no, balanceTrans.getReason(), balance, 0,
						request);
			}
		}
		return appResult;
	}

	@Override
	public AppResult getBalanceList(BalanceTrans balanceTrans) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer customer_id = balanceTrans.getCustomer_id();
		if (customer_id == null || customer_id.equals("")) {
			appResult.setError_info("用户id不能为空");
			return appResult;
		}
		Customer dbCustomer = this.customerDao.get(Customer.class, customer_id);
		if (dbCustomer == null) {
			appResult.setError_info("用户id不存在或错误");
			return appResult;
		} else {
			String hql = "from BalanceTrans as o where o.customer.customer_id=?";
			PageModel pm = PageModelContext.getPageModel();
			List list = this.balanceTransDao.query(hql, pm, customer_id);
			if (list.size() == 0) {
				appResult.setError_info("用户无充值记录");
			} else {
				appResult.setPage_model(pm);
				appResult.setData(list);
				appResult.setError_info("获取列表成功");
				appResult.setResult(AppResult.SUCCESS);
			}
		}
		return appResult;
	}

	@Override
	public AppResult getBalance(BalanceTrans balanceTrans) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer customer_id = balanceTrans.getCustomer_id();
		if (customer_id == null || customer_id.equals("")) {
			appResult.setError_info("用户id不能为空");
			return appResult;
		}
		Customer dbCustomer = this.customerDao.get(Customer.class, customer_id);
		if (dbCustomer == null) {
			appResult.setError_info("用户id不存在或错误");
			return appResult;
		} else {
			Balance balance = this.balanceDao.findByColumnValue(Balance.class,
					"customer.customer_id", customer_id);
			appResult.setData(balance);
			appResult.setError_info("获取成功");
			appResult.setResult(AppResult.SUCCESS);
		}
		return appResult;
	}

	@Override
	public void alipayNotify(String out_trade_no, Date gmt_payment,Integer pay_type) {
		// 修改订单状态
		BalanceTrans balanceTrans = this.balanceTransDao.findByColumnValue(
				BalanceTrans.class, "pay_relative_id", out_trade_no);
		if (balanceTrans == null) {
			logger.error("订单号：" + out_trade_no + ",订单不存在,时间：" + new Date());
		} else if (balanceTrans.getPay_status() == 1) {
			logger.debug("订单号：" + out_trade_no + ",订单已处理过,时间：" + new Date());
		} else {
			balanceTrans.setPay_status(1);// 设置状态为已支付
			balanceTrans.setPay_type(1);// 类型：支付宝
			balanceTrans.setPay_time(gmt_payment);// 支付时间
			// 修改用户余额
			Balance dbBalance = this.balanceTransDao.findByColumnValue(
					Balance.class, "customer.customer_id", balanceTrans
							.getCustomer().getCustomer_id());
			dbBalance.setBalance_remain(dbBalance.getBalance_remain()
					+ balanceTrans.getBalance());
			dbBalance.setLast_update_time(new Date());
			this.balanceTransDao.saveOrUpdate(balanceTrans);
			this.balanceTransDao.saveOrUpdate(dbBalance);
			logger.debug("订单号：" + out_trade_no + ",充值完成,时间：" + new Date());
		}
	}

	@Override
	public AppResult updateBalancePay(BalanceTrans balanceTrans) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer customer_id = balanceTrans.getCustomer_id();
		String pay_password = balanceTrans.getPay_password();
		String out_trade_no =  balanceTrans.getPay_relative_id();
		if (customer_id == null || customer_id.equals("")) {
			appResult.setError_info("用户id不能为空");
			return appResult;
		}
		if (pay_password == null || pay_password.equals("") ) {
			appResult.setError_info("支付密码不能为空");
			return appResult;
		}
		if (out_trade_no == null || out_trade_no.equals("") ) {
			appResult.setError_info("订单号不能为空");
			return appResult;
		}
		Customer dbCustomer = this.customerDao.get(Customer.class,
				balanceTrans.getCustomer_id());
		if (dbCustomer == null) {
			appResult.setError_info("用户id不存在或错误");
			return appResult;
		}
		Alipay alipay = this.alipayDao.findByColumnValue(Alipay.class, "send_out_trade_no", out_trade_no);
		if(alipay==null){
			appResult.setError_info("订单号不存在或错误");
			return appResult;
		}
		Integer alipay_act =alipay.getAlipay_act();
		Integer price = alipay.getSend_total_fee();
		String dbPay_password =dbCustomer.getPay_password();
		if(dbPay_password==null || dbPay_password.equals("")){
			appResult.setError_info("用户未设置支付密码");
			return appResult;
		}
		if(!dbPay_password.equals(pay_password)){
			appResult.setError_info("用户支付密码错误");
			return appResult;
		}
		Balance dbBalance = this.balanceDao.findByColumnValue(Balance.class,
				"customer.customer_id", balanceTrans.getCustomer_id());
		if (dbBalance == null) {
			appResult.setError_info("数据异常");
			return appResult;
		}
		Integer remain = dbBalance.getBalance_remain();
		if (remain < price) {
			appResult.setError_info("用户余额不足，请充值");
			return appResult;
		}
		// 余额充值 = 0,
		// 服务包付款 = 1,
		// 医生服务付款 = 2,
		// 健康中心付款 = 3,
		// 业务逻辑
		logger.debug("支付类型:" + alipay_act + "，订单号:"
				+ out_trade_no + ",支付成功时间："
				+ new Date());
		Integer pay_type=0;//支付类型
		BalanceTrans dbBalanceTrans = new BalanceTrans();
		dbBalanceTrans.setBalance((price*-1));
		dbBalanceTrans.setBalance_remain(remain+(price*-1));
		dbBalanceTrans.setCustomer(dbCustomer);
		dbBalanceTrans.setCreate_by(0);
		dbBalanceTrans.setCreate_time(new Date());
		dbBalanceTrans.setPay_status(1);
		dbBalanceTrans.setPay_type(pay_type);
		dbBalanceTrans.setPay_relative_id(out_trade_no);
		dbBalanceTrans.setPay_time(new Date());
		dbBalanceTrans.setReason(ServerParam.ALIPAY_ACT[alipay_act]);
		dbBalance.setBalance_remain(remain+(price*-1));
		dbBalance.setLast_update_time(new Date());
		dbBalance.setLast_update_user(0);
		this.balanceTransDao.saveOrUpdate(dbBalanceTrans);
		this.balanceTransDao.saveOrUpdate(dbBalance);
		if (alipay_act == 1) {// 服务包付款
			this.productOrderService.alipayNotify(out_trade_no,new Date(),pay_type);
		} else if (alipay_act == 2) {// 医生服务付款
			this.doctorServiceOrderService.alipayNotify(
					out_trade_no, new Date(),pay_type);
		} else if (alipay_act == 3) {// 健康中心付款
			
		}
		appResult.setError_info("支付成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
}

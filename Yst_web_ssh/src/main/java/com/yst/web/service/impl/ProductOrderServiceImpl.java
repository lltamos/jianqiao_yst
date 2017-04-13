package com.yst.web.service.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.yst.web.dao.ProductOrderDao;
import com.yst.web.dao.ShareMoneyDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Balance;
import com.yst.web.model.BalanceTrans;
import com.yst.web.model.Customer;
import com.yst.web.model.CustomerAddress;
import com.yst.web.model.Doctor;
import com.yst.web.model.ExpressOrderInfo;
import com.yst.web.model.Merchant;
import com.yst.web.model.PageModel;
import com.yst.web.model.Product;
import com.yst.web.model.ProductOrder;
import com.yst.web.model.ProductStat;
import com.yst.web.model.User;
import com.yst.web.service.AlipayService;
import com.yst.web.service.ProductOrderService;
import com.yst.web.service.ShareMoneyService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.BootStrapResult;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;
import com.yst.web.utils.UtilDate;

@Service("productOrderService")
@Transactional
public class ProductOrderServiceImpl implements ProductOrderService {
	private static Log logger = LogFactory.getLog(ProductOrderServiceImpl.class);

	@Resource(name = "productOrderDao")
	private ProductOrderDao productOrderDao;
	@Resource(name = "alipayService")
	private AlipayService alipayService;
	@Autowired
	private ShareMoneyService moneyService;// 分润service方法
	@PersistenceContext
	private EntityManager entityManager;
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private ShareMoneyDao shareMoneyDao;

	@Override
	public ProductOrder findById(int id) {
		return this.productOrderDao.get(ProductOrder.class, id);
	}

	@Override
	public List<ProductOrder> selectAll() {
		return this.productOrderDao.query(ProductOrder.class);
	}

	@Override
	public void deleteById(int id) {
		this.productOrderDao.delete(ProductOrder.class, id);
	}

	@Override
	public void update(ProductOrder productOrder) {
		ProductOrder dbProductOrder = this.productOrderDao.get(ProductOrder.class, productOrder.getProduct_order_id());
		BeanUtils.copy(productOrder, dbProductOrder);
		this.productOrderDao.update(dbProductOrder);
	}

	@Override
	public void add(ProductOrder productOrder) {
		this.productOrderDao.save(productOrder);
	}

	@Override
	public Result getProdecOrderFeeStatus() {
		try {
			Long time =56000L;
			String sql="select * from product_order as o where (o.fee1_pay_status=2 and TIMESTAMPDIFF(SECOND,o.fee1_update_time,now())>=?)"
				+ " or (o.fee2_pay_status=2 and TIMESTAMPDIFF(SECOND,o.fee2_update_time,now())>=?)"
				+ " or (o.fee3_pay_status=2 and TIMESTAMPDIFF(SECOND,o.fee3_update_time,now())>=?)"
				+ " or (o.fee4_pay_status=2 and TIMESTAMPDIFF(SECOND,o.fee4_update_time,now())>=?)"
				+ " or (o.fee5_pay_status=2 and TIMESTAMPDIFF(SECOND,o.fee5_update_time,now())>=?)"
				+ " order by o.create_date asc limit 10";
			List<ProductOrder> list=this.productOrderDao.selectSql(ProductOrder.class,sql,time,time,time,time,time);
			if(list.size()==0){
				return ResultUtils.returnError("没有需要分润的订单了。");
			}
			Result result=new Result();
			for (ProductOrder productOrder : list) {
				result=moneyService.ShareMoneySave(productOrder);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError("分润查询失败。"+e.getMessage());
		}
	}
	@Override
	public Result getRefundProductOrder(ProductOrder po, Integer type, User user) {
		Integer num = type - 7;// 期数
		try {
			// 退款金额
			Class<?> clazz = po.getClass();
			Method getFee = clazz.getDeclaredMethod("getFee" + num);
			Integer fee = (Integer) getFee.invoke(po);
			if (fee == null || fee == 0) {
				ResultUtils.returnError("退款失败");
			}
			Customer dbCustomer = po.getCustomer();
			Balance dbBalance = this.productOrderDao.findByColumnValue(Balance.class,
					"customer.customer_id", dbCustomer.getCustomer_id());
			Integer remain = dbBalance.getBalance_remain();
			dbBalance.setBalance_remain(remain + fee);
			;
			BalanceTrans dbBalanceTrans = new BalanceTrans();
			dbBalanceTrans.setBalance(fee);
			dbBalanceTrans.setBalance_remain(remain + fee);
			dbBalanceTrans.setCustomer(dbCustomer);
			dbBalanceTrans.setCreate_by(user.getUser_id());
			dbBalanceTrans.setCreate_time(new Date());
			dbBalanceTrans.setPay_status(1);// 1成功
			dbBalanceTrans.setPay_type(0);// 系统内
			dbBalanceTrans.setPay_relative_id(po.getPay_relative_id());
			dbBalanceTrans.setPay_time(new Date());
			dbBalanceTrans.setReason("人工退" + num + "期款成功");
			dbBalance.setBalance_remain(remain + fee);
			dbBalance.setLast_update_time(new Date());
			dbBalance.setLast_update_user(user.getUser_id());
			this.productOrderDao.saveOrUpdate(dbBalanceTrans);
			this.productOrderDao.saveOrUpdate(dbBalance);

			po.setPay_status(type);
			Method setFeeStatus = clazz.getDeclaredMethod("setFee" + num + "_pay_status", Integer.class);
			setFeeStatus.invoke(po,5);
			Method setFeeTime = clazz.getDeclaredMethod("setFee" + num + "_update_time", Date.class);
			setFeeTime.invoke(po, new Date());
			this.productOrderDao.saveOrUpdate(po);
			return ResultUtils.returnSuccess("退" + num + "期款成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return ResultUtils.returnError("退" + num + "期款失败" + e.getMessage());
		}
	}

	@Override
	public Result getConfirmProductOrder(ProductOrder po, Integer type, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alipayRefundNotify(String result_details) {
		try {
			String[] result = result_details.split("^");
			String order_num = result[0];// 订单号
			String money = result[1];// 金额
			String str = result[2];// 处理结果会返回“SUCCESS”
			if (str.equals("SUCCESS")) {
				ProductOrder productOrder = this.productOrderDao.findByColumnValue(ProductOrder.class,
						"pay_relative_id", order_num);
				if (productOrder == null) {
					logger.error("退款处理结果:" + result_details + ",订单不存在,时间：" + new Date());
				} else if (productOrder.getPay_status() >= 8 && productOrder.getPay_status() < 13) {
					logger.error("退款处理结果:" + result_details + ",订单已处理过,时间：" + new Date());
				} else {
					productOrder.setPay_status(productOrder.getPay_status() + 5);// 设置状态为退x期款
					this.productOrderDao.saveOrUpdate(productOrder);
					logger.info("退款处理结果:" + result_details + ",退款回调业务数据处理成功");
				}
			} else {
				logger.info("退款处理结果:" + result_details + ",退款回调业务数据异常");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			logger.error("退款处理结果:" + result_details + ",退款回调业务数据处理失败，" + e.getMessage());
		}
	}

	// 0 待支付定金 1已支付定金 2已支付全款 3确认一期款 4确认二期款 5确认三期款 6确认四期款 7确认五期款 8退一期款 9退二期款
	// 10退三期款 11退四期款 12退五期款 13订单完成
	@Override
	public Result updateProductOrderStatus(ProductOrder po, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("dbUser");
		if (user == null) {
			ResultUtils.returnError("登录超时，请重新登录");
		}
		Integer po_id = po.getProduct_order_id();
		Integer type = po.getType();
		ProductOrder dbPo = productOrderDao.get(ProductOrder.class, po_id);
		if (dbPo == null) {
			ResultUtils.returnError("订单编号错误或不存在");
		}
		if (type >= 8 && type <= 12) {// 退款
			return this.getRefundProductOrder(dbPo, type, user);
		} else if (type == 2) {
			return this.getUpdatePayAllMoney(dbPo, type);
		}else{
			return ResultUtils.returnError("暂未开通");
		}
	}

	private Result getUpdatePayAllMoney(ProductOrder po, Integer type) {
		try {
			Class<?> clazz = po.getClass();
			po.setPay_status(type);
			for (int i = 1; i <= 5; i++) {
				Method setFeeStatus = clazz.getDeclaredMethod("setFee" + i + "_pay_status", Integer.class);
				setFeeStatus.invoke(po, 2);
				Method setFeeTime = clazz.getDeclaredMethod("setFee" + i + "_update_time", Date.class);
				setFeeTime.invoke(po, new Date());
			}
			this.productOrderDao.saveOrUpdate(po);
			return ResultUtils.returnSuccess("支付全款操作成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return ResultUtils.returnError("支付全款操作失败，" + e.getMessage());
		}
	}

	@Override
	public AppResult addOrder(ProductOrder productOrder, HttpServletRequest request) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer merchant_id = productOrder.getMerchant_id();
		Integer customer_id = productOrder.getCustomer_id();
		Integer product_id = productOrder.getProduct_id();
		if (merchant_id == null || merchant_id.equals("")) {
			appResult.setError_info("所属商家id不能为空");
			return appResult;
		} else if (customer_id == null || customer_id.equals("")) {
			appResult.setError_info("所属用户id不能为空");
			return appResult;
		} else if (product_id == null || product_id.equals("")) {
			appResult.setError_info("服务包id不能为空");
			return appResult;
		} else {
			Merchant dbMerchant = this.productOrderDao.get(Merchant.class, merchant_id);
			Customer dbCustomer = this.productOrderDao.get(Customer.class, customer_id);
			Product dbProduct = this.productOrderDao.get(Product.class, product_id);
			if (dbMerchant == null) {
				appResult.setError_info("所属商家id错误或未注册");
				return appResult;
			} else if (dbCustomer == null) {
				appResult.setError_info("所属用户id错误或未注册");
				return appResult;
			} else if (dbProduct == null) {
				appResult.setError_info("服务包id错误或未注册");
				return appResult;
			} else {
				if (dbCustomer.getDeleted() == 1) {
					appResult.setError_info("用户已禁用");
					return appResult;
				} else if (dbMerchant.getDeleted() == 1) {
					appResult.setError_info("商家已禁用");
					return appResult;
				} else if (dbProduct.getDeleted() == 1) {
					appResult.setError_info("服务包已禁用");
					return appResult;
				} else {
					CustomerAddress dbCustomerAddress = null;
					if (dbProduct.getProduct_for() == 1) {
						Integer address_id = productOrder.getAddress_id();
						if (address_id == null || address_id.equals("")) {
							if (dbCustomer.getAddress_id() == null || dbCustomer.getAddress_id().equals("")) {
								appResult.setError_info("用户未保存默认收货地址");
								return appResult;
							}
							dbCustomerAddress = this.productOrderDao.get(CustomerAddress.class,
									dbCustomer.getAddress_id());
						} else {
							dbCustomerAddress = this.productOrderDao.get(CustomerAddress.class, address_id);
						}
						if (dbCustomerAddress == null) {
							appResult.setError_info("地址id错误或不存在");
							return appResult;
						}
					}
					String buyer_des = productOrder.getBuyer_des(); // 买家留言
					Integer delivery_time = productOrder.getDelivery_time();// 送货时间
					Integer is_invoice = productOrder.getIs_invoice();// 是否开发票
					String invoice_name = productOrder.getInvoice_name();// 发票抬头
					Customer rCustomer = dbCustomer.getRecomm_customer();
					if (rCustomer != null) {
						productOrder.setRecomm_address(rCustomer.getAddress());
						productOrder.setRecomm_phone(rCustomer.getPhone());
					}
					productOrder.setPatient_name(dbCustomer.getName());
					productOrder.setPatient_address(dbCustomer.getAddress());
					productOrder.setPatient_phone(dbCustomer.getPhone());
					productOrder.setMerchant_address(dbMerchant.getCustomer().getAddress());
					productOrder.setMerchant_phone(dbMerchant.getCustomer().getPhone());

					productOrder.setMerchant(dbMerchant);
					productOrder.setCustomer(dbCustomer);
					productOrder.setProduct(dbProduct);
					productOrder.setPrice(dbProduct.getPrice());
					productOrder.setDivide(dbProduct.getDivide());
					productOrder.setFee1(dbProduct.getFee1());
					productOrder.setFee2(dbProduct.getFee2());
					productOrder.setFee3(dbProduct.getFee3());
					productOrder.setFee4(dbProduct.getFee4());
					productOrder.setFee5(dbProduct.getFee5());
					productOrder.setCreate_date(new Date());
					productOrder.setIs_real(dbProduct.getIs_real());
					productOrder.setProduct_for(dbProduct.getProduct_for());
					if (dbProduct.getProduct_for() == 1) {
						productOrder.setAddress_id(dbCustomerAddress.getId());
						productOrder.setAddress(dbCustomerAddress.getAddress());
						productOrder.setAddressee(dbCustomerAddress.getAddressee());
						productOrder.setAddressee_phone(dbCustomerAddress.getPhone());
						productOrder.setZip_code(dbCustomerAddress.getZip_code());
					}
					productOrder.setFreight(dbProduct.getFreight());
					productOrder.setBuyer_des(buyer_des);
					productOrder.setIs_invoice(is_invoice);
					productOrder.setInvoice_name(invoice_name);
					productOrder.setDelivery_time(delivery_time);
					String out_trade_no = UtilDate.getOrderNum() + UtilDate.getThree();// 生成订单号
					productOrder.setPay_relative_id(out_trade_no);
					this.productOrderDao.save(productOrder);
					// 调用支付宝接口
					appResult = this.alipayService.addAlipayNO(appResult, out_trade_no, dbProduct.getName(),
							productOrder.getPrice(), 1, request);
				}
			}
		}
		return appResult;
	}

	@Override
	public AppResult getMerchantList(ProductOrder productOrder) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer merchant_id = productOrder.getMerchant_id();
		Integer pay_status = productOrder.getPay_status();
		if (merchant_id == null || merchant_id.equals("")) {
			appResult.setError_info("所属商家id不能为空");
		} else {
			Merchant dbMerchant = this.productOrderDao.get(Merchant.class, merchant_id);
			if (dbMerchant == null) {
				appResult.setError_info("所属商家id错误或未注册");
			} else {
				String hql = "from ProductOrder as po,Product as p where po.deleted<>1 and po.merchant.merchant_id=? and po.product.product_id=p.product_id";
				if (pay_status != null && !pay_status.equals("")) {
					hql += " and po.pay_status=" + pay_status;
				}
				PageModel pm = PageModelContext.getPageModel();
				List list = this.productOrderDao.query(hql, pm, merchant_id);
				if (list.size() == 0) {
					appResult.setError_info("所属商家没有订单");
				} else {
					appResult.setMerchant_id(merchant_id);
					appResult.setPage_model(pm);
					appResult.setData(list);
					appResult.setError_info("获取列表成功");
					appResult.setResult(AppResult.SUCCESS);
				}
			}
		}
		return appResult;
	}

	@Override
	public AppResult getInfo(ProductOrder productOrder) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer product_order_id = productOrder.getProduct_order_id();
		if (product_order_id == null || product_order_id.equals("")) {
			appResult.setError_info("订单id不能为空");
		} else {
			ProductOrder dbProductOrder = this.productOrderDao.get(ProductOrder.class, product_order_id);
			if (dbProductOrder == null) {
				appResult.setError_info("服务包id错误或未注册");
			} else {
				if (dbProductOrder.getDeleted() == 1) {
					appResult.setError_info("订单已禁用");
				} else {
					dbProductOrder.setCustomer_name(dbProductOrder.getCustomer().getName());
					dbProductOrder.setPhone(dbProductOrder.getCustomer().getPhone());
					dbProductOrder.setName(dbProductOrder.getProduct().getName());
					dbProductOrder.setDivide(dbProductOrder.getProduct().getDivide());
					appResult.setData(dbProductOrder);
					appResult.setError_info("获取成功");
					appResult.setResult(AppResult.SUCCESS);
				}
			}
		}
		return appResult;
	}

	@Override
	public void alipayNotify(String out_trade_no, Date gmt_payment, Integer pay_type) {
		// 修改服务包订单状态
		ProductOrder productOrder = this.productOrderDao.findByColumnValue(ProductOrder.class, "pay_relative_id",
				out_trade_no);
		if (productOrder == null) {
			logger.error("订单号：" + out_trade_no + ",订单不存在,时间：" + new Date());
		} else if (productOrder.getPay_status() == 1) {
			logger.debug("订单号：" + out_trade_no + ",订单已处理过,时间：" + new Date());
		} else {
			productOrder.setPay_status(1);// 设置状态为已支付
			productOrder.setPay_type(pay_type);// 类型：支付宝
			productOrder.setPay_time(gmt_payment);// 支付时间
			Product dbProduct = productOrder.getProduct();// 修改服务包销量
			ProductStat dbProductStat = dbProduct.getProductStat();
			dbProductStat.setSale_count(dbProductStat.getSale_count() + 1);
			dbProduct.setProductStat(dbProductStat);
			this.productOrderDao.saveOrUpdate(dbProduct);
			this.productOrderDao.saveOrUpdate(productOrder);
			logger.debug("订单号：" + out_trade_no + ",服务包付款完成,时间：" + new Date());
		}
	}

	@Override
	public AppResult updatePay(ProductOrder productOrder) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer product_order_id = productOrder.getProduct_order_id();
		Integer period = productOrder.getPeriod();
		String pay_password = productOrder.getPay_password();
		if (product_order_id == null || product_order_id.equals("")) {
			appResult.setError_info("订单id不能为空");
		} else if (period == null || period.equals("")) {
			appResult.setError_info("疗程数不能为空");
		} else if (pay_password == null || pay_password.equals("")) {
			appResult.setError_info("支付密码不能为空");
		} else {
			ProductOrder dbProductOrder = this.productOrderDao.get(ProductOrder.class, product_order_id);
			if (dbProductOrder == null) {
				appResult.setError_info("服务包订单id错误");
			} else {
				if (dbProductOrder.getPay_status()<3) {
					appResult.setError_info("订单状态错误");
					return appResult;
				}
				Customer customer = dbProductOrder.getCustomer();
				String dbPay_password = customer.getPay_password();
				if (dbPay_password == null || dbPay_password.equals("")) {
					appResult.setError_info("用户未设置支付密码");
					return appResult;
				}
				if (!dbPay_password.equals(pay_password)) {
					appResult.setError_info("用户支付密码错误");
					return appResult;
				}
				Integer status = 2;// 0未支付 1已支付 2已确认 3已分润 4退款中 5退款成功 6退款失败
				if (period == 1) {
					dbProductOrder.setFee1_pay_status(status);
					productOrder.setFee1_update_time(new Date());
				} else if (period == 2) {
					dbProductOrder.setFee2_pay_status(status);
					productOrder.setFee2_update_time(new Date());
				} else if (period == 3) {
					dbProductOrder.setFee3_pay_status(status);
					productOrder.setFee3_update_time(new Date());
				} else if (period == 4) {
					dbProductOrder.setFee4_pay_status(status);
					productOrder.setFee4_update_time(new Date());
				} else if (period == 5) {
					dbProductOrder.setFee5_pay_status(status);
					productOrder.setFee5_update_time(new Date());
				}
				dbProductOrder.setPay_status(2+period);
				this.productOrderDao.saveOrUpdate(dbProductOrder);
				appResult.setData(dbProductOrder);
				appResult.setError_info("确认支付成功");
				appResult.setResult(AppResult.SUCCESS);
			}
		}
		return appResult;
	}

	@Override
	public List<ProductOrder> selectAllByPage() {
		String hql = "from ProductOrder as o";
		return this.productOrderDao.query(hql, PageModelContext.getPageModel(), ProductOrder.class, null);
	}

	@Override
	public AppResult updateSendGoods(ExpressOrderInfo express) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		String order_id = express.getOrder_id();
		if (order_id == null || order_id.equals("")) {
			appResult.setError_info("订单号不能为空");
			return appResult;
		}
		ProductOrder dbProductOrder = this.productOrderDao.findByColumnValue(ProductOrder.class, "pay_relative_id",
				order_id);
		if (dbProductOrder == null) {
			appResult.setError_info("订单不存在或订单号错误");
			return appResult;
		}
		Integer pay_status = dbProductOrder.getPay_status();
		Integer product_for = dbProductOrder.getProduct_for();
		if (pay_status == 0) {
			appResult.setError_info("订单未支付");
			return appResult;
		}
		if (product_for == 0) {
			appResult.setError_info("订单不属于配送类");
			return appResult;
		}
		this.productOrderDao.saveOrUpdate(express);
		dbProductOrder.setSend_status(1);// 修改发货状态为1
		this.productOrderDao.saveOrUpdate(dbProductOrder);
		appResult.setError_info("发货成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult getExpressInfo(ExpressOrderInfo express) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		String order_id = express.getOrder_id();
		if (order_id == null || order_id.equals("")) {
			appResult.setError_info("订单号不能为空");
			return appResult;
		}
		ExpressOrderInfo dbExpress = this.productOrderDao.findByColumnValue(ExpressOrderInfo.class, "order_id",
				order_id);
		if (dbExpress == null) {
			appResult.setError_info("订单不存在或订单号错误");
			return appResult;
		}
		appResult.setData(dbExpress);
		appResult.setError_info("查询成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public BootStrapResult<List<Map<String, Object>>> findProductOrderList(HttpServletRequest request) {
		AppResult ap = new AppResult();
		ap.setResult(AppResult.FAILED);
		Doctor doctor = (Doctor) request.getSession().getAttribute(ServerParam.DOCTOR_SESSION);
		PageModel pm = PageModelContext.getPageModel();
		StringBuffer sb = new StringBuffer();
		sb.append("select po.*,sm.share_money,sm.created_time as share_time from product_order po ,share_money sm ");
		sb.append("where sm.order_number = po.pay_relative_id and po.doctor_id=" + doctor.getDoctor_id());
		Session session = (Session) entityManager.getDelegate();
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());

		sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		int count = sqlQuery.list().size();
		sqlQuery.setFirstResult(pm.getStartIndex());
		sqlQuery.setMaxResults(pm.getPageSize());
		List<Map<String, Object>> list = sqlQuery.list();

		BootStrapResult<List<Map<String, Object>>> br = new BootStrapResult<List<Map<String, Object>>>();
		br.setResult("获取成功！");
		br.setData(list);
		br.setRecordsFiltered(count);
		br.setRecordsTotal(count);
		entityManager.close();
		return br;
	}

	@Override
	public BootStrapResult<List<ProductOrder>> findPushShareMoneyPage(Map<String, Object> searchParams, Integer start, Integer pageSize,HttpServletRequest request) {
		Customer customer = (Customer) request.getSession().getAttribute(ServerParam.DOCTOR_SESSION);
		Query query = entityManager.createQuery("from ProductOrder where customer_id="+customer.getCustomer_id());
		int count = query.getResultList().size();
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		List<ProductOrder> pushList = query.getResultList();
		BootStrapResult<List<ProductOrder>> push = new BootStrapResult<List<ProductOrder>>();
		push.setResult("获取成功！");
		push.setData(pushList);
		push.setRecordsFiltered(count);
		push.setRecordsTotal(count);
		return push;
	}
}

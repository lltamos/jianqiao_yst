package com.yst.web.service.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.yst.web.dao.IPlatformInfoDao;
import com.yst.web.dao.ProductOrderDao;
import com.yst.web.dao.ShareMoneyDao;
import com.yst.web.entity.platforminfo.PlatformInfo;
import com.yst.web.entity.shareMoney.ShareMoney;
import com.yst.web.model.Balance;
import com.yst.web.model.Customer;
import com.yst.web.model.Doctor;
import com.yst.web.model.ProductOrder;
import com.yst.web.service.BalanceService;
import com.yst.web.service.CustomerService;
import com.yst.web.service.MerchantService;
import com.yst.web.service.PatientService;
import com.yst.web.service.ShareMoneyService;
import com.yst.web.utils.BootStrapResult;
import com.yst.web.utils.BootStrapResultUtils;
import com.yst.web.utils.ServerParam;



/**
 * 分润service层实现
 */
@Service
@Transactional
public class ShareMoneyServiceImpl implements ShareMoneyService {
	private static Log logger = LogFactory.getLog(ShareMoneyServiceImpl.class);
	@Autowired
	private ShareMoneyDao shareMoneyDao;//分润
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BalanceService balanceService;//用户余额
	@Autowired
	private IPlatformInfoDao iPlatformInfoDao;//平台
	@Autowired
	private PatientService patientService;//患者
	@Autowired
	private  ProductOrderDao productOrderDao;//患者订单
	@Autowired
	private MerchantService merchantService;//专家机构
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public boolean delete(Long arg0) {
		shareMoneyDao.delete(arg0);
		return true;
	}

	@Override
	public ShareMoney get(Long arg0) {
		return shareMoneyDao.findOne(arg0);
	}

	@Override
	public ShareMoney saveAndModify(ShareMoney arg0) {
		return shareMoneyDao.save(arg0);
	}

	@Override
	public Result ShareMoneySave(ProductOrder order) {
		Class<?> clazz = order.getClass();
		Integer pay_status = order.getPay_status();
		if (pay_status < 3 || pay_status > 7) {
			logger.error("分润失败，订单号："+order.getPay_relative_id()+",订单状态：" + pay_status + ",订单状态错误");
			return ResultUtils.returnError("分润失败，订单号："+order.getPay_relative_id()+",订单状态：" + pay_status + ",订单状态错误");
		}
		Integer num = pay_status - 2;// 期数
		try {
			Method getFeeStatus = clazz.getDeclaredMethod("getFee" + num + "_pay_status");
			Integer feeStatus=(Integer) getFeeStatus.invoke(order);//分润完成
			if(feeStatus==null || feeStatus!=2){
				logger.error("分润失败，订单号："+order.getPay_relative_id()+"," + num + "期状态错误");
				ResultUtils.returnError("分润失败，订单号："+order.getPay_relative_id()+"," + num + "期状态错误");
			}
			Method getFee = clazz.getDeclaredMethod("getFee" + num);
			Integer fee = (Integer) getFee.invoke(order);// x期分润金额
			if (fee == null || fee == 0) {
				logger.error("分润失败，订单号："+order.getPay_relative_id()+"," + num + "期金额为0");
				ResultUtils.returnError("分润失败，订单号："+order.getPay_relative_id()+"," + num + "期金额为0");
			}
			Long MerchantMoney = 0L;// 专家机构分润的金额
			Long customerMoney = 0L;// 患者推荐人分润的金额
			Long platformMoney = 0L;// 平台分润的金额
			Long productMoney = 0L;// 项目分润的金额

			Customer customer = order.getCustomer();// 患者
			// 专家机构（70%），患者推荐人（介绍患者分润10%），专家推荐人（介绍医院专家分润10%），总部（10%）
			// 生成分润记录
			ShareMoney mShareMoney = new ShareMoney();
			mShareMoney.setOrderMoney(order.getPrice());// 订单金额
			mShareMoney.setOrderNumber(order.getPay_relative_id());// 订单号
			mShareMoney.setOrderStatues(order.getPay_status());// 订单状态
			mShareMoney.setPatientName(order.getPatient_name());// 患者姓名
			mShareMoney.setPatientTel(order.getPatient_phone());// 患者电话
			mShareMoney.setProjectName(order.getProduct().getName());// 项目名称
			MerchantMoney = (long) (fee * 0.7);
			mShareMoney.setShareMoney(MerchantMoney.intValue());
			mShareMoney.setShareStatues(1);// 已分润
			mShareMoney.setShareType(0 + "");// 分润类型 0:专家机构 1:患者推荐人2：平台3：项目推荐人
			mShareMoney.setCustomerId(order.getMerchant().getCustomer().getCustomer_id());
			Balance mBalance = productOrderDao.findByColumnValue(Balance.class, "customer.customer_id",
					order.getMerchant().getCustomer().getCustomer_id());
			mBalance.setFee(mBalance.getFee() + MerchantMoney.intValue());// 修改余额总金额
			mBalance.setDeposit_fee(mBalance.getDeposit_fee() + MerchantMoney.intValue());// 修改余额的可提现金额
			mBalance.setBalance_remain(mBalance.getBalance_remain()+ MerchantMoney.intValue());
			productOrderDao.saveOrUpdate(mBalance);// 修改余额总金额
			shareMoneyDao.save(mShareMoney);
			if (order.getProduct().getRecomm_customer() != null ){//项目推荐人
				// 生成分润记录
				ShareMoney pShareMoney = new ShareMoney();
				pShareMoney.setOrderMoney(order.getPrice());// 订单金额
				pShareMoney.setOrderNumber(order.getPay_relative_id());// 订单号
				pShareMoney.setOrderStatues(order.getPay_status());// 订单状态
				pShareMoney.setPatientName(order.getPatient_name());// 患者姓名
				pShareMoney.setPatientTel(order.getPatient_phone());// 患者电话
				pShareMoney.setProjectName(order.getProduct().getName());// 项目名称
				productMoney = (long) (fee * 0.1);
				pShareMoney.setShareMoney(productMoney.intValue());
				pShareMoney.setShareStatues(1);// 已分润
				pShareMoney.setShareType(3 + "");// 分润类型 0:专家机构 1:患者推荐人2：平台3：项目推荐人
				pShareMoney.setCustomerId(order.getProduct().getRecomm_customer().getCustomer_id());
				shareMoneyDao.save(pShareMoney);
				
				Balance pBalance = productOrderDao.findByColumnValue(Balance.class, "customer.customer_id",
						order.getProduct().getRecomm_customer().getCustomer_id());
				pBalance.setFee(pBalance.getFee() + productMoney.intValue());// 修改余额总金额
				pBalance.setDeposit_fee(pBalance.getDeposit_fee() + productMoney.intValue());// 修改余额的可提现金额
				pBalance.setBalance_remain(pBalance.getBalance_remain()+ productMoney.intValue());//余额
				productOrderDao.saveOrUpdate(pBalance);// 修改余额总金额
			}
			if (customer.getRecomm_customer() != null )// 患者推荐人
			{
				// 生成分润记录
				ShareMoney rShareMoney = new ShareMoney();
				rShareMoney.setOrderMoney(order.getPrice());// 订单金额
				rShareMoney.setOrderNumber(order.getPay_relative_id());// 订单号
				rShareMoney.setOrderStatues(order.getPay_status());// 订单状态
				rShareMoney.setPatientName(order.getPatient_name());// 患者姓名
				rShareMoney.setPatientTel(order.getPatient_phone());// 患者电话
				rShareMoney.setProjectName(order.getProduct().getName());// 项目名称
				customerMoney = (long) (fee * 0.1);
				rShareMoney.setShareMoney(customerMoney.intValue());
				rShareMoney.setShareStatues(1);// 已分润
				rShareMoney.setShareType(3 + "");// 分润类型 0:专家机构 1:患者推荐人2：平台3：项目推荐人
				rShareMoney.setCustomerId(customer.getRecomm_customer().getCustomer_id());
				shareMoneyDao.save(rShareMoney);
				
				Balance rBalance = productOrderDao.findByColumnValue(Balance.class, "customer.customer_id",
						customer.getRecomm_customer().getCustomer_id());
				rBalance.setFee(rBalance.getFee() + customerMoney.intValue());// 修改余额总金额
				rBalance.setDeposit_fee(rBalance.getDeposit_fee() + customerMoney.intValue());// 修改余额的可提现金额
				rBalance.setBalance_remain(rBalance.getBalance_remain()+ customerMoney.intValue());//余额
				productOrderDao.saveOrUpdate(rBalance);// 修改余额总金额
			}
			List<PlatformInfo> listPlatform = iPlatformInfoDao.getPlatforCount();

			platformMoney = fee - MerchantMoney - customerMoney - productMoney;
			if (listPlatform.size()>0) {
				for (PlatformInfo platformInfo : listPlatform) {
					Integer totalFenrun=platformInfo.getTotalAmountFenRun()==null?0:platformInfo.getTotalAmountFenRun();
					Integer pFee =platformInfo.getFee()==null?0:platformInfo.getFee();
					platformInfo.setFee(pFee+fee);
					platformInfo.setTotalAmountFenRun(totalFenrun+platformMoney.intValue());
					iPlatformInfoDao.save(platformInfo);
				}
			} else{
				PlatformInfo pi = new PlatformInfo();
				pi.setFee(fee);
				pi.setTotalAmountFenRun(platformMoney.intValue());
				iPlatformInfoDao.save(pi);
			}
			// 生成平台分润记录
			ShareMoney ishareMoney = new ShareMoney();
			ishareMoney.setOrderMoney(order.getPrice());// 订单金额
			ishareMoney.setOrderNumber(order.getPay_relative_id());// 订单号
			ishareMoney.setOrderStatues(order.getPay_status());// 订单状态
			ishareMoney.setPatientName(order.getPatient_name());// 患者姓名
			ishareMoney.setPatientTel(order.getPatient_phone());// 患者电话
			ishareMoney.setProjectName(order.getProduct().getName());// 项目名称
			ishareMoney.setShareStatues(1);// 已分润
			ishareMoney.setShareType("2");//// 分润类型 0:专家机构 1:患者推荐人2：平台3：专家推荐人
			ishareMoney.setShareMoney(platformMoney.intValue());
			shareMoneyDao.save(ishareMoney);

			Method setFeeStatus = clazz.getDeclaredMethod("setFee" + num + "_pay_status", Integer.class);
			setFeeStatus.invoke(order, 3);//分润完成
			Method setFeeTime = clazz.getDeclaredMethod("setFee" + num + "_update_time", Date.class);
			setFeeTime.invoke(order, new Date());
			this.productOrderDao.saveOrUpdate(order);
			logger.info("分润成功，订单号："+order.getPay_relative_id()+"," + num + "期金额为:"+fee);
			return ResultUtils.returnSuccess("分润成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("分润成功，订单号："+order.getPay_relative_id()+"," + num + "期款分润失败"+e.getMessage());
			e.printStackTrace();
			return ResultUtils.returnError(num + "期款分润失败" + e.getMessage());
		}
	}

	
	
	/***
	 * 提现记录列表（带分页）
	 */
	@Override
	public BootStrapResult<List<ShareMoney>> getShareMoneyPage(
			Map<String, Object> map, Integer page, Integer rows) {
		Map<String, SearchFilter> filter = SearchFilter.parse(map);
		Specification<ShareMoney> specification = DynamicSpecifications.bySearchFilter(filter.values(),ShareMoney.class);
		Page<ShareMoney> accountPage = shareMoneyDao.findAll(specification, new PageRequest(page, rows,new Sort(Direction.DESC, new String[] { "updateTime" })));
		return BootStrapResultUtils.returnPage(ShareMoney.class, accountPage);
	}
	
	@Override
	public BootStrapResult<List<ShareMoney>> findShareMoneyByPage(Map<String, Object> searchParams, Integer start,
			Integer pageSize,HttpServletRequest request) {
		Doctor doctor = (Doctor) request.getSession().getAttribute(ServerParam.DOCTOR_SESSION);
		Query query = entityManager.createQuery("from ShareMoney where doctorId="+doctor.getDoctor_id());
		int count = query.getResultList().size();
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		List<ShareMoney> resultList = query.getResultList();
		BootStrapResult<List<ShareMoney>> br = new BootStrapResult<List<ShareMoney>>();
		br.setResult("获取成功！");
		br.setData(resultList);
		br.setRecordsFiltered(count);
		br.setRecordsTotal(count);
		return br;
	}
}

package com.alqsoft.service.impl.productorder;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.result.ResultUtils;
import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
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

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alqsoft.dao.balance.BalanceDao;
import com.alqsoft.dao.balance.BalanceTransDao;
import com.alqsoft.dao.customer.CustomerDao;
import com.alqsoft.dao.doctor.DoctorDao;
import com.alqsoft.dao.merchant.MerchantDao;
import com.alqsoft.dao.productorder.ProductOrderDao;
import com.alqsoft.dao.sharemoney.ShareMoneyDao;
import com.alqsoft.entity.Balance;
import com.alqsoft.entity.BalanceTrans;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.UserTable;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.shareMoney.ShareMoney;
import com.alqsoft.service.productorder.ProductOrderService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;

/**
 * 
 * @Description: TODO
 * @author 王海龙
 * @e-mail 76217866@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月12日 下午3:48:26
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */

@Service
@Transactional(readOnly = true)
public class ProductOrderServiceImpl implements ProductOrderService {
	private static Log logger = LogFactory
			.getLog(ProductOrderServiceImpl.class);
	@Autowired
	private ProductOrderDao productOrderDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private BalanceDao balanceDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private BalanceTransDao balanceTransDao;
	@Autowired
	private ShareMoneyDao shareMoneyDao;
	@Override
	public boolean delete(Long arg0) {
		try{
			productOrderDao.delete(arg0);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public ProductOrder get(Long arg0) {
		return productOrderDao.findOne(arg0);
	}

	@Override
	@Transactional
	public ProductOrder saveAndModify(ProductOrder arg0) {
		return productOrderDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<ProductOrder>> getProductOrderList(String payRelativeId,String customerPhone,
			String recommPhone,String productRecommPhone,Integer page, Integer length) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_payRelativeId", payRelativeId);
		params.put("EQ_customerPhone", customerPhone);
		params.put("EQ_recommPhone", recommPhone);
		params.put("EQ_productRecommPhone", productRecommPhone);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<ProductOrder> specification = DynamicSpecifications.bySearchFilter(filter.values(),ProductOrder.class);
		Page<ProductOrder> accountPage = productOrderDao.findAll(specification, new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(ProductOrder.class, accountPage);
	}

	@Override
	@Transactional
	public Result save(ProductOrder productorder,UserTable dbUser) {
		Long id = productorder.getId();
		Integer type = productorder.getType();
		ProductOrder dbPo = productOrderDao.findOne(id);
		if (dbPo == null) {
			return SpringMVCUtils.returnError("订单编号错误或不存在");
		}
		if (type >= 8 && type <= 12) {// 退款
			return this.getRefundProductOrder(dbPo, type, dbUser);
		} else if (type == 2) {//支付全款
			return this.getUpdatePayAllMoney(productorder,dbPo, type);
		}else if (type >= 3 && type <= 7) {//确认x期
			return this.getUpdatePayMoney(dbPo, type);
		}else if (type ==13) {//订单完成
			dbPo.setPayStatus(13);
			productOrderDao.save(dbPo);
			return SpringMVCUtils.returnSuccess("保存成功");
		}else{
			return SpringMVCUtils.returnError("提交状态错误");
		}
	}
	
	private Result getUpdatePayMoney(ProductOrder dbPo, Integer type) {
		Integer num = type - 2;// 期数
		try {
			Class<?> clazz = dbPo.getClass();
			dbPo.setPayStatus(type);
			Method setFeeStatus = clazz.getDeclaredMethod("setFeePayStatus" + num, Integer.class);
			setFeeStatus.invoke(dbPo,2);//确认成功
			Method setFeeTime = clazz.getDeclaredMethod("setFeeUpdateTime" + num, Date.class);
			setFeeTime.invoke(dbPo, new Date());
			this.productOrderDao.save(dbPo);
			return SpringMVCUtils.returnSuccess("保存成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return SpringMVCUtils.returnError("支付全款操作失败，" + e.getMessage());
		}
	}

	public Result getRefundProductOrder(ProductOrder po, Integer type, UserTable user) {
		Integer num = type - 7;// 期数
		try {
			Integer payStatus=po.getPayStatus();
			if(payStatus!=num+2){
				return SpringMVCUtils.returnError("订单状态错误！");
			}
			// 退款金额
			Class<?> clazz = po.getClass();
			Method getFee = clazz.getDeclaredMethod("getFee" + num);
			Long fee = (Long) getFee.invoke(po);
			if (fee == null || fee == 0) {
				ResultUtils.returnError("退款失败");
			}
			Customer dbCustomer = this.customerDao.findOne(po.getCustomerId());
			Long customerId =dbCustomer.getId();
			Balance dbBalance = balanceDao.getLockByCustomerId(customerId);
			Long remain = dbBalance.getBalanceRemain();
			dbBalance.setBalanceRemain(remain + fee);
			BalanceTrans dbBalanceTrans = new BalanceTrans();
			dbBalanceTrans.setIncome(fee);
			dbBalanceTrans.setDefray(0L);
			dbBalanceTrans.setBalanceRemain(remain + fee);
			dbBalanceTrans.setCustomerId(customerId);
			dbBalanceTrans.setCreateUser(user.getLoginName());
			dbBalanceTrans.setPayStatus(1);// 1成功
			dbBalanceTrans.setPayType(0);// 系统内
			dbBalanceTrans.setPayRelativeId(po.getPayRelativeId());
			dbBalanceTrans.setPayTime(new Date());
			dbBalanceTrans.setReason("人工退" + num + "期款成功");
			dbBalance.setBalanceRemain(remain + fee);
			dbBalance.setLastUpdateTime(new Date());
			dbBalance.setLastUpdateUser(user.getId().intValue());
			this.balanceTransDao.save(dbBalanceTrans);
			this.balanceDao.save(dbBalance);

			po.setPayStatus(type);
			Method setFeeStatus = clazz.getDeclaredMethod("setFeePayStatus"+num, Integer.class);
			setFeeStatus.invoke(po,5);//退款成功
			Method setFeeTime = clazz.getDeclaredMethod("setFeeUpdateTime"+num, Date.class);
			setFeeTime.invoke(po, new Date());
			this.productOrderDao.save(po);
			return SpringMVCUtils.returnSuccess("退" + num + "期款成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return SpringMVCUtils.returnError("退" + num + "期款失败" + e.getMessage());
		}
	}
	
	private Result getUpdatePayAllMoney(ProductOrder po, ProductOrder dbPo, Integer type) {
		try {
			Integer payStatus=dbPo.getPayStatus();
			Integer divide=dbPo.getDivide();
			Long totalPrice=po.getTotalPrice();
			String patient_name=po.getPatientName();
			String patient_phone = po.getPatientPhone();
			String patient_address = po.getPatientAddress();
			String recomm_name = po.getRecommName();
			String recomm_phone = po.getRecommPhone();
			String recomm_address = po.getRecommAddress();
			String merchant_address = po.getMerchantAddress();
			String merchant_phone = po.getMerchantPhone();
			if(!(payStatus==0 || payStatus==1)){
				return SpringMVCUtils.returnError("订单状态错误！");
			}
			if(totalPrice!=null){
				 dbPo.setTotalPrice(totalPrice);
			}
			if(!StringUtils.isBlank(patient_name)){
				dbPo.setPatientName(patient_name);
			}
			if(!StringUtils.isBlank(patient_phone)){
				dbPo.setPatientPhone(patient_phone);
			}
			if(!StringUtils.isBlank(patient_address)){
				dbPo.setPatientAddress(patient_address);
			}
			if(!StringUtils.isBlank(recomm_name)){
				dbPo.setRecommName(recomm_name);
			}
			if(!StringUtils.isBlank(recomm_phone)){
				dbPo.setRecommPhone(recomm_phone);
			}
			if(!StringUtils.isBlank(recomm_address)){
				dbPo.setRecommAddress(recomm_address);
			}
			if(!StringUtils.isBlank(merchant_phone)){
				dbPo.setMerchantPhone(merchant_phone);
			}
			if(!StringUtils.isBlank(merchant_address)){
				dbPo.setMerchantAddress(merchant_address);
			}
			Class<?> clazz = po.getClass();
			Class<?> dbClazz = dbPo.getClass();
			for (int i = 1; i <= divide; i++) {
				Method getFee = clazz.getDeclaredMethod("getFee" + i);
				Long fee = (Long) getFee.invoke(po);
				if (fee != null) {
					Method setFee = clazz.getDeclaredMethod("setFee" + i,Long.class);
					setFee.invoke(dbPo, fee);
				}
				Method setFeeStatus = dbClazz.getDeclaredMethod("setFeePayStatus" + i, Integer.class);
				setFeeStatus.invoke(dbPo, 1);
				Method setFeeTime = dbClazz.getDeclaredMethod("setFeeUpdateTime" + i, Date.class);
				setFeeTime.invoke(dbPo, new Date());
			}
			dbPo.setPayStatus(type);
			this.productOrderDao.save(dbPo);
			return SpringMVCUtils.returnSuccess("保存成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return SpringMVCUtils.returnError("支付全款操作失败，" + e.getMessage());
		}
	}

	@Override
	@Transactional
	public Result getCheckProductOrderShareMoney() {
		//##分润时间60*60*24*15=1296000
		//long shareTime= 60*10;
		long shareTime= 60*1;
		try{
			List<ProductOrder> reList = this.productOrderDao.getCheckProductOrderShareMoney(shareTime, 10);
			if (reList == null || reList.size() == 0) {
				return SpringMVCUtils.returnError("暂无分润订单！");
			}
			// 开始分润
			for (ProductOrder po : reList) {
				Integer divide = po.getDivide();
				Long recommId=po.getRecommId();
				Long merchantId=po.getMerchantId();
				Long productRecommId =po.getProductRecommId();
				Long doctorId =po.getDoctorId();
				String orderId = po.getPayRelativeId(); 
				double ZYBL =0.6;
				double HZTJRBL = 0.1;
				double XMTJRBL=0.1;
				double YSBL=0.1;
				Class<?> clazz = po.getClass();
				for (int i = 1; i <= divide; i++) {
					Method getFeePayStatus = clazz.getDeclaredMethod("getFeePayStatus" + i);
					Integer feePayStatus= (Integer) getFeePayStatus.invoke(po);
					if(feePayStatus!=2){
						continue;
					}
					Method getFee = clazz.getDeclaredMethod("getFee" + i);
					Long fee = (Long) getFee.invoke(po);
					Long sFr = fee;
					if(fee==null || fee==0){
						continue;
					}else{
						//总院分润开始
						Merchant dbMerchant=this.merchantDao.findOne(merchantId);
						Long mCid=dbMerchant.getCustomerId();
						Balance mB=this.balanceDao.findByCustomerIdAndType(mCid,2);
						Long mFr=Math.round(fee*ZYBL);//总院分润金额
						if(mFr<=0){
							continue;
						}
						Long mRe = mB.getBalanceRemain()==null?0:mB.getBalanceRemain();
						Long mDf=mB.getDepositFee()==null?0:mB.getDepositFee();
						BalanceTrans mBt = new BalanceTrans();
						mBt.setIncome(mFr);
						mBt.setDefray(0L);
						mBt.setBalanceRemain(mRe + mFr);
						mBt.setCreateUser("system");
						mBt.setCustomerId(mCid);
						mBt.setPayStatus(1);// 1成功
						mBt.setPayType(0);// 系统内
						mBt.setPayRelativeId(orderId);
						mBt.setPayTime(new Date());
						mBt.setReason("订单号："+orderId+",分润比例:"+ZYBL+",总院"+i + "期款分润");
						mB.setBalanceRemain(mRe + mFr);
						mB.setDepositFee(mDf + mFr);
						mB.setLastUpdateTime(new Date());
						mB.setLastUpdateUser(0);
						ShareMoney mSm = new ShareMoney();
						mSm.setOrderNumber(orderId);
						mSm.setProductName(po.getProductName());
						mSm.setCustomerPhone(po.getCustomerPhone());
						mSm.setTotalPrice(po.getTotalPrice());
						mSm.setOrderStatues(i);
						mSm.setDivide(divide);
						mSm.setFee(fee);
						mSm.setShareType(0);
						mSm.setCustomerId(mCid);
						mSm.setShareMoney(mFr);
						mSm.setReason("订单号："+orderId+",分润比例:"+ZYBL+",总院"+i + "期款分润");
						this.shareMoneyDao.save(mSm);
						this.balanceTransDao.save(mBt);
						this.balanceDao.save(mB);
						sFr-=mFr;
						//医生分润开始
						Doctors dbDoc=this.doctorDao.findOne(doctorId);
						Long dCid=dbDoc.getCustomerId();
						Long pFr=Math.round(fee*YSBL);//医生分润金额
						if(pFr>0){
							Balance pB=this.balanceDao.findByCustomerIdAndType(dCid,1);
							Long pRe = pB.getBalanceRemain()==null?0:pB.getBalanceRemain();
							Long pDf=pB.getDepositFee()==null?0:pB.getDepositFee();
							BalanceTrans pBt = new BalanceTrans();
							pBt.setIncome(pFr);
							pBt.setDefray(0L);
							pBt.setBalanceRemain(pRe + pFr);
							pBt.setCreateUser("system");
							pBt.setCustomerId(dCid);
							pBt.setPayStatus(1);// 1成功
							pBt.setPayType(0);// 系统内
							pBt.setPayRelativeId(orderId);
							pBt.setPayTime(new Date());
							pBt.setReason("订单号："+orderId+",分润比例:"+YSBL+",医生"+i + "期款分润");
							pB.setBalanceRemain(pRe + pFr);
							pB.setDepositFee(pDf+pFr);
							pB.setLastUpdateTime(new Date());
							pB.setLastUpdateUser(0);//系统
							ShareMoney dSm = new ShareMoney();
							dSm.setOrderNumber(orderId);
							dSm.setProductName(po.getProductName());
							dSm.setCustomerPhone(po.getCustomerPhone());
							dSm.setTotalPrice(po.getTotalPrice());
							dSm.setOrderStatues(i);
							dSm.setDivide(divide);
							dSm.setFee(fee);
							dSm.setShareType(1);
							dSm.setCustomerId(dCid);
							dSm.setShareMoney(pFr);
							dSm.setReason("订单号："+orderId+",分润比例:"+YSBL+",医生"+i + "期款分润");
							this.shareMoneyDao.save(dSm);
							this.balanceTransDao.save(pBt);
							this.balanceDao.save(pB);
							sFr-=pFr;
						}
					}
					if(recommId!=null){
						Customer rC=this.customerDao.findOne(recommId);
						if(rC==null){
							logger.info("订单号："+orderId+",患者推荐人ID:"+recommId+",用户不存在！");
						}else{
							//患者推荐人分润开始
							Long rCid=rC.getId();
							Long rFr=Math.round(fee*HZTJRBL);//患者推荐人分润金额
							if(rFr>0){
								Balance rB=this.balanceDao.findByCustomerIdAndType(rCid,3);
								Long rRe = rB.getBalanceRemain()==null?0:rB.getBalanceRemain();
								Long rDf=rB.getDepositFee()==null?0:rB.getDepositFee();
								BalanceTrans rBt = new BalanceTrans();
								rBt.setIncome(rFr);
								rBt.setDefray(0L);
								rBt.setBalanceRemain(rRe + rFr);
								rBt.setCreateUser("system");
								rBt.setCustomerId(rCid);
								rBt.setPayStatus(1);// 1成功
								rBt.setPayType(0);// 系统内
								rBt.setPayRelativeId(orderId);
								rBt.setPayTime(new Date());
								rBt.setReason("订单号："+orderId+",分润比例:"+HZTJRBL+",患者推荐人"+i + "期款分润");
								rB.setBalanceRemain(rRe + rFr);
								rB.setDepositFee(rDf+rFr);
								rB.setLastUpdateTime(new Date());
								rB.setLastUpdateUser(0);//系统
								ShareMoney dSm = new ShareMoney();
								dSm.setOrderNumber(orderId);
								dSm.setProductName(po.getProductName());
								dSm.setCustomerPhone(po.getCustomerPhone());
								dSm.setTotalPrice(po.getTotalPrice());
								dSm.setOrderStatues(i);
								dSm.setDivide(divide);
								dSm.setFee(fee);
								dSm.setShareType(2);
								dSm.setCustomerId(rCid);
								dSm.setShareMoney(rFr);
								dSm.setReason("订单号："+orderId+",分润比例:"+HZTJRBL+",患者推荐人"+i + "期款分润");
								this.shareMoneyDao.save(dSm);
								this.balanceTransDao.save(rBt);
								this.balanceDao.save(rB);
								sFr-=rFr;
							}
						}
					}
					if(productRecommId!=null){
						Customer pC=this.customerDao.findOne(productRecommId);
						if(pC==null){
							logger.info("订单号："+orderId+",项目推荐人ID:"+productRecommId+",用户不存在");
						}else{
							//项目推荐人分润开始
							Long pCid=pC.getId();
							Long pFr=Math.round(fee*XMTJRBL);//项目推荐人分润金额
							if(pFr>0){
								Balance pB=this.balanceDao.findByCustomerIdAndType(pCid,3);
								Long pRe = pB.getBalanceRemain()==null?0:pB.getBalanceRemain();
								Long pDf=pB.getDepositFee()==null?0:pB.getDepositFee();
								BalanceTrans pBt = new BalanceTrans();
								pBt.setIncome(pFr);
								pBt.setDefray(0L);
								pBt.setBalanceRemain(pRe + pFr);
								pBt.setCreateUser("system");
								pBt.setCustomerId(pCid);
								pBt.setPayStatus(1);// 1成功
								pBt.setPayType(0);// 系统内
								pBt.setPayRelativeId(orderId);
								pBt.setPayTime(new Date());
								pBt.setReason("订单号："+orderId+",分润比例:"+XMTJRBL+",项目推荐人"+i + "期款分润");
								pB.setBalanceRemain(pRe + pFr);
								pB.setDepositFee(pDf+pFr);
								pB.setLastUpdateTime(new Date());
								pB.setLastUpdateUser(0);//系统
								ShareMoney dSm = new ShareMoney();
								dSm.setOrderNumber(orderId);
								dSm.setProductName(po.getProductName());
								dSm.setCustomerPhone(po.getCustomerPhone());
								dSm.setTotalPrice(po.getTotalPrice());
								dSm.setOrderStatues(i);
								dSm.setDivide(divide);
								dSm.setFee(fee);
								dSm.setShareType(3);
								dSm.setCustomerId(pCid);
								dSm.setShareMoney(pFr);
								dSm.setReason("订单号："+orderId+",分润比例:"+XMTJRBL+",项目推荐人"+i + "期款分润");
								this.shareMoneyDao.save(dSm);
								this.balanceTransDao.save(pBt);
								this.balanceDao.save(pB);
								sFr-=pFr;
							}
						}
					}
					ShareMoney dSm = new ShareMoney();
					dSm.setOrderNumber(orderId);
					dSm.setProductName(po.getProductName());
					dSm.setCustomerPhone(po.getCustomerPhone());
					dSm.setTotalPrice(po.getTotalPrice());
					dSm.setOrderStatues(i);
					dSm.setDivide(divide);
					dSm.setFee(fee);
					dSm.setShareType(4);
					dSm.setCustomerId(0L);
					dSm.setShareMoney(sFr);
					dSm.setReason("订单号："+orderId+"分润金额:"+sFr/100+"元,平台"+i + "期款分润");
					this.shareMoneyDao.save(dSm);
					Method setFeeStatus = clazz.getDeclaredMethod("setFeePayStatus" + i, Integer.class);
					setFeeStatus.invoke(po, 3);
					Method setFeeTime = clazz.getDeclaredMethod("setFeeUpdateTime" + i, Date.class);
					setFeeTime.invoke(po, new Date());
					this.productOrderDao.save(po);
					logger.info("订单号："+orderId+","+i + "期款分润成功");
				}
			}
			return SpringMVCUtils.returnSuccess("分润完成!");		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			StackTraceElement[] error1 = e.getStackTrace();
			for (StackTraceElement stackTraceElement : error1) {
				logger.error(stackTraceElement.toString());
			}
			return SpringMVCUtils.returnError("分润失败，" + e.getMessage());
		}
	}
}

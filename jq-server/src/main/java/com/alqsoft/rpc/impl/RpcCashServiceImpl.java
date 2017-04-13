package com.alqsoft.rpc.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.alqframework.utils.DoubleUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alqsoft.entity.cashreceivestation.CashReceiveStation;
import com.alqsoft.entity.payandcash.TakeOut;
import com.alqsoft.rpc.RpcCashGZYLService;
import com.alqsoft.rpc.pay.RpcPayService;
import com.alqsoft.service.gzylcash.GZYLCashService;
import com.alqsoft.utils.PayConfig;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年2月11日 下午1:27:45
 * 
 */
@Service
@Transactional
@SuppressWarnings("unused")
public class RpcCashServiceImpl implements RpcCashGZYLService{

	private static Log logger = LogFactory.getLog(RpcCashServiceImpl.class);
	
//	@Autowired
//	private CardManageRecondService cardManageRecondService;
	
//	@Autowired
//	private UsersService usersService;
	
//	@Autowired
//	private TakeOutService takeOutService;
	
	@Autowired
	private RpcPayService rpcToPayProjectService;
	
	@Autowired
	private GZYLCashService gzylCashService;
	
	@Override
	public void cashGZYLService(TakeOut takeOut, Map<String, Object> requestMap) {
		try{
			logger.debug("提现实现类打印地址："+this.hashCode());
			Long user_id = (Long) requestMap.get("user_id");
			String phone = (String) requestMap.get("phone");
			logger.info("提现方法后台,用户ID:"+user_id);
			
			/*//验证代付信息
			if (takeOut == null) {
				logger.debug("提现方法,用户ID:"+user_id+",提现实体异常");
				return;
			}
			
			if (takeOut.getMoney() == null) {
				logger.debug("提现方法,用户ID:"+user_id+",提现实体异常, 提现金额为空");
				return;
			}
			
			// 判断参数
			double money = takeOut.getMoney();
			if (money < 1000 || money > 5000000){
				logger.info("提现方法,用户ID:"+user_id+"提现金额不在范围内, 金额:"+money);
				return;
			}
			if (money < 0) {
				logger.info("提现方法,用户ID:"+user_id+"提现金额不在范围内, 金额:"+money);
				return;
			}
			
			//获取银行信息
			Result acrdInformation = this.cardManageRecondService.getcardInfoByUserId(user_id);
			CardManageRecond recond = new CardManageRecond();
			if (acrdInformation.getCode() == 1) {
				recond = (CardManageRecond) acrdInformation.getContent();
			} else if (acrdInformation.getCode() == null || acrdInformation.getCode() == 0) {
				logger.info("提现方法,用户ID:"+user_id+",银行信息不存在");
				return;
			}
			
			String cardNum = recond.getCardNum();
			
			// 验证银行卡信息
			if ("".equals(cardNum) || null == cardNum){
				logger.info("提现方法, 用户ID:"+user_id+", 银行卡信息为空, 异常");
			}
			String cardName = recond.getBankName();
			String ownName = recond.getName();
			if (! takeOut.getBankNo().equals(cardNum) || 
				! takeOut.getBank().equals(cardName) || 
				! takeOut.getName().equals(ownName)) {
				logger.info("提现方法, 用户ID:"+user_id+",接收的银行卡信息有误");
				return;
			} else {
				takeOut.setBankNo(cardNum);
				takeOut.setBank(cardName);
				takeOut.setName(ownName);
			}
			Double fee = null; //获取可提现金额
			Users users = this.usersService.getUsersByIdRowLock(user_id);
			fee = users.getFanxAccount();
			String merSeqId = takeOut.getNum();
			takeOut.setNum(merSeqId);
			takeOut.setMoney(money);
			logger.info("提现方法, 用户id:"+user_id+",用户可提现金额为:"+fee);
			if (money <= 0) {
				logger.info("提现方法, 用户id:"+user_id+", 提现金额大于剩余可提现金额");
				takeOut.setState(2);
				takeOut.setSxf((double) 0.0);
				takeOutService.saveAndModify(takeOut);
				return;
			}
			if (DoubleUtils.tworound((double)money)> DoubleUtils.tworound(fee)) {
				logger.info("提现方法, 用户id:"+user_id+", 提现金额大于剩余可提现金额");
				takeOut.setState(2);
				takeOut.setSxf((double) 0.0);
				takeOutService.saveAndModify(takeOut);
				return;
			} else {
				double fee2 = fee - money;
				if (fee2 >= 0) {
					logger.info("提现方法, 用户id:"+user_id+", 提现金额异常");
					takeOut.setState(2);
					takeOut.setSxf((double) 0.0);
					takeOutService.saveAndModify(takeOut);
					return;
				}
				if (fee2 < 0) {
					logger.info("提现方法, 用户id:"+user_id+", 提现金额不足");
					takeOut.setState(2);
					takeOut.setSxf((double) 0.0);
					takeOutService.saveAndModify(takeOut);
					return;
				}
				users.setFanxAccount(fee2);
				usersService.saveAndModify(users);
				takeOutService.saveAndModify(takeOut);*/
			//Users users = this.usersService.getUsersByIdRowLock(user_id);
				CashReceiveStation cash = new CashReceiveStation();
				cash.setCreatedTime(new Date());
				cash.setUpdateTime(new Date());
				cash.setMerSeqId(takeOut.getNum());
				cash.setCardNo(takeOut.getBankNo());
				cash.setBankName(takeOut.getBank());
				cash.setPersonName(takeOut.getName());

				int multiply = (int)DoubleUtils.multiply(takeOut.getMoney(), 100);
				cash.setMoney(multiply);

				//cash.setCssId(Integer.valueOf(takeOut.getId().toString()));
				cash.setSystemRole("5");
				cash.setThirdPayType(PayConfig.GZYLpay);
				cash.setFromSystem("BDBD");
				cash.setFeeIncome(500);
				cash.setCreateTime(getMerDateYYYY());
				cash.setStatus(1);
				cash.setTel(phone);
				//Double fee = DoubleUtils.multiply(users.getFanxAccount(), 100);
				//Long fee1 = (long) Math.floor(fee);
				//cash.setCurrentBalance(fee1);
				cash.setFromSystem("本地报单");
				cash.setDescriber("处理中");
				
				Result result = new Result();//提现返回的数据
				
				Map<String,String> parem = new HashMap<String, String>();
				result = this.rpcToPayProjectService.cashService(cash ,parem);
				/*logger.info("贵州银联提现,申请提现完毕："+"订单号"+result.getContent().toString()+",申请状态:"+result.getMsg()+"卡号："+takeOut.getBankNo()+"发起者："+takeOut.getId()+"");*/
				if ("FALL".equals(result.getMsg())) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return;
				}
			/*}*/
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info("贵州银联提现,发生异常,事务回滚,订单号:"+takeOut.getNum()==null?"暂无":takeOut.getNum()+"卡号："+takeOut.getBankNo()+"发起者："+takeOut.getId()+"");
			logger.error("提现发生异常:"+e.getMessage());
		}
		TransactionAspectSupport.currentTransactionStatus().flush();
	}

	
	private  Date getMerDateYYYY() throws ParseException{
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String merDate = sf1.format(date);
		Date date2 = sf1.parse(merDate);
		return date2;
	}

//	@Override
//	public String validation(HttpServletRequest request,HttpServletResponse response, String encReq) {
//		String transStatusDesc = request.getParameter("transStatusDesc");
//		logger.info("回调方法中返回的状态:"+transStatusDesc+",回调方法第一步.");
//		Result verifySingNotify = this.gzylCashService.validation(request, encReq);
//		if (verifySingNotify.getMsg().equals("statuserror")) {
//			return "error";
//		}
//		logger.info("回调方法最后一步,订单号:"+verifySingNotify.getCode()+",最终状态:"+verifySingNotify.getMsg());
//		String result = verifySingNotify.getMsg();
//		
//		if   (null!=result && result.equals("SUCCESS")) {//成功
//			return "success";
//		}else if (null!=result && result.equals("FAIL")) {//失败
//			return "success";
//		}else if (null!=result && result.equals("WPAR")) {//处理中
//			
//		}
//		return "error";
//	}
}
